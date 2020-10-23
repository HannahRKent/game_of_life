package de.orchound.gameoflife;

import de.orchound.gameoflife.game.Board;
import de.orchound.gameoflife.rendering.BoardRenderer;
import processing.core.PApplet;

public class GameOfLifeApplet extends PApplet {

	private final Board board;
	private final BoardRenderer boardRenderer;
	private long updateTimestamp = System.nanoTime();

	public GameOfLifeApplet(Board board) {
		this.board = board;
 		this.boardRenderer = new BoardRenderer(board, this);
	}

	@Override
	public void setup() {
		stroke(255);
	}

	@Override
	public void settings() {
		size(1280, 720);
	}

	@Override
	public void draw() {
		tick();

		background(200);

		pushMatrix();
		scale(20f);
		boardRenderer.render();
		popMatrix();
	}

	private void update() {
		board.update();
	}

	private void tick() {
		long currentTimestamp = System.nanoTime();
		if (currentTimestamp - updateTimestamp > 200000000L) {
			update();
			updateTimestamp = currentTimestamp;
		}
	}
}