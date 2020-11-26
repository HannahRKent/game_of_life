package de.orchound.gameoflife.view;

import de.orchound.gameoflife.model.Game;
import org.joml.Vector2i;
import org.joml.Vector2ic;

import java.util.ArrayList;
import java.util.List;

public class Painter {

	private final Game game;
	private boolean currentlyPainting = false;
	private boolean fillMode = false;
	private boolean paintMode = false;

	public Painter(Game game) {
		this.game = game;
	}

	public void paintAction(Vector2ic cell) {
		if (fillMode) {
			fillCells(cell);
			fillMode = false;
		} else {
			paintOrEraseCell(cell);
		}
	}

	private void paintOrEraseCell(Vector2ic cell) {
		if (!currentlyPainting) {
			paintMode = !game.getCellStatus(cell);
			currentlyPainting = true;
		}

		game.setCell(cell, paintMode);
	}

	private void calculateFillCells(Vector2ic cell) {
		if (game.getCellStatus(cell) != paintMode && game.cellInBoardRange(cell)) {
			game.setCell(cell, paintMode);
			calculateFillCells(new Vector2i(cell.x(), cell.y()+1));
			calculateFillCells(new Vector2i(cell.x(), cell.y()-1));
			calculateFillCells(new Vector2i(cell.x()+1, cell.y()));
			calculateFillCells(new Vector2i(cell.x()-1, cell.y()));
		}
	}

	private void fillCells(Vector2ic cell) {
		paintMode = !game.getCellStatus(cell);
		calculateFillCells(cell);
	}

	public void stopPainting() {
		currentlyPainting = false;
	}

	public void setFillMode() {
		this.fillMode = true;
	}
}