package controller;

import java.awt.Graphics;

public interface IController {
	/** start game */
	public void start();

	/** call when Model change ==> View update */
	public void setChange();

	/** block move left */
	public void moveLeft();

	/** block move right */
	public void moveRight();

	/** block move down */
	public void moveDown();

	/** spin block */
	public void ronate();

	/** draw block */
	public void draw(Graphics g);

	/** block fast down */
	public void fastDown();

	/** call when click back button */
	public void pause();

	/** call when is lose */
	public void lose();

	/** call when click resume button */
	public void resume();
	/** call when user select level button */
	public void setLevel(int level);
	public int getLevel();
}
