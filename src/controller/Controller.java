package controller;

import java.awt.Graphics;

import model.GameBoard;
import model.IModel;
import view.IViewTetris;
import view.ViewGameTetris;
import view.ViewTetris;

public class Controller implements IController {
	private IModel board; // main model
	private IViewTetris viewGameTetris; // view when game is start
	private IViewTetris viewTetris; // view show when run application
	private int level;

	public Controller() {
		level = 1;
		viewTetris = new ViewTetris(this);
	}

	/** call when game is start, it create model and create view */
	public void init() {
		board = new GameBoard(this);
		board.setLevel(level);
		viewGameTetris = new ViewGameTetris(this);
	}

	/** call when view click button new game */
	public void start() {
		init();
		board.start();
		viewGameTetris.start();
	}

	@Override
	public void draw(Graphics g) {
		board.draw(g);
	}

	@Override
	public void setChange() {
		viewGameTetris.update();
	}

	@Override
	public void moveLeft() {
		board.moveLeft();
		setChange();
	}

	@Override
	public void moveRight() {
		board.moveRight();
		setChange();
	}

	@Override
	public void moveDown() {
		board.moveDown();
		setChange();
	}

	@Override
	public void ronate() {
		board.spin();
		setChange();
	}

	@Override
	public void fastDown() {
		board.fastDown();
		setChange();
	}

	@Override
	public void pause() {
		board.pause();
		viewTetris.pause();
		viewGameTetris.pause();
	}

	@Override
	public void lose() {
		viewGameTetris.lose();
		viewTetris.lose();
	}

	@Override
	public void resume() {
		if (board != null && viewGameTetris != null) {
			board.resume();
			viewGameTetris.resume();
			viewTetris.hidden();
		}
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public int getLevel() {
		return level;
	}

}
