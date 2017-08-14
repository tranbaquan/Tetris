package model;

import java.awt.Graphics;

public interface IModel {

	/**
	 * All method call by controller or object container
	 */

	/** start thread */
	public void start();

	/** draw main board */
	public void draw(Graphics g);

	/** spin block */
	public void spin();

	/** drop block */
	public void drop();

	/** block move left */
	public void moveLeft();

	/** block move right */
	public void moveRight();

	/** block move down */
	public void moveDown();

	/** block fast down */
	public void fastDown();

	/** pause model */
	public void pause();

	/** resume model */
	public void resume();
	/** set level game */
	public void setLevel(int level);
}
