package model;

import java.awt.Color;

public abstract class Block implements Movable {
	private Spinnable spinnable;
	protected Color color;
	protected Position topLeftPosition;
	protected Position[] coord;
	protected Position[] tempCoord;
	protected Position potentialTopLeft;
	protected GameBoard gameBoard;
	public static final int BLOCK_SIZE = 30;

	public Block(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Position getTopLeftPosition() {
		return topLeftPosition;
	}

	public void setTopLeftPosition(Position topLeftPoint) {
		this.topLeftPosition = topLeftPoint;
	}

	public void setSpinBehavior(Spinnable spinnable) {
		this.spinnable = spinnable;
	}

	public void performSpin() {
		gameBoard.revertMatrix();
		if (!isCollisionSpin(topLeftPosition)) {
			spinnable.spin(coord);
			changeCoord();
		} else {
			changeCoord();
			for (int i = 0; i < coord.length; i++) {
				tempCoord[i].setX(coord[i].getX());
				tempCoord[i].setY(coord[i].getY());
			}
		}
	}

	public abstract void init();

	public abstract void changeCoord();

	@Override
	public void drop() {
		potentialTopLeft.setX(topLeftPosition.getX() + 1);
		gameBoard.revertMatrix();
		if (!isCollisionBottom(potentialTopLeft)) {
			setTopLeftPosition(potentialTopLeft);
			changeCoord();
		} else {
			topLeftPosition.setX(potentialTopLeft.getX() - 1);
			changeCoord();
			fixedBlockAndSetBlock();
		}
		gameBoard.update();
	}

	public boolean isCollisionBottom(Position nextPossition) {
		for (int i = 0; i < coord.length; i++) {
			Position location = nextPossition.sum(coord[i]);
			if (gameBoard.isExistOrOutArray(location)) {
				return true;
			}
		}
		return false;
	}

	public boolean isCollisionLeft(Position nextPossition) {

		for (int i = 0; i < coord.length; i++) {
			Position location = nextPossition.sum(coord[i]);
			if (gameBoard.isExistOrOutArray(location)) {
				return true;
			}
		}
		return false;
	}

	public boolean isCollisionRight(Position nextPossition) {
		for (int i = 0; i < coord.length; i++) {
			Position location = nextPossition.sum(coord[i]);
			if (gameBoard.isExistOrOutArray(location)) {
				return true;
			}
		}
		return false;
	}

	public boolean isCollisionSpin(Position nextPossition) {
		spinnable.spin(tempCoord);
		for (int i = 0; i < tempCoord.length; i++) {
			Position location = nextPossition.sum(tempCoord[i]);
			if (gameBoard.isCollistionSpin(location)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void moveLeft() {
		potentialTopLeft.setY(topLeftPosition.getY() - 1);
		potentialTopLeft.setX(topLeftPosition.getX());
		gameBoard.revertMatrix();
		if (!isCollisionLeft(potentialTopLeft)) {
			setTopLeftPosition(potentialTopLeft);
			changeCoord();
		} else {
			topLeftPosition.setY(potentialTopLeft.getY() + 1);
			changeCoord();
		}
	}

	@Override
	public void moveRight() {
		potentialTopLeft.setY(topLeftPosition.getY() + 1);
		potentialTopLeft.setX(topLeftPosition.getX());
		gameBoard.revertMatrix();
		if (!isCollisionRight(potentialTopLeft)) {
			setTopLeftPosition(potentialTopLeft);
			changeCoord();
		} else {
			topLeftPosition.setY(potentialTopLeft.getY() - 1);
			changeCoord();
		}
	}

	@Override
	public void moveDown() {
		potentialTopLeft.setX(topLeftPosition.getX() + 1);
		gameBoard.revertMatrix();
		if (!isCollisionBottom(potentialTopLeft)) {
			setTopLeftPosition(potentialTopLeft);
			changeCoord();
		} else {
			topLeftPosition.setX(potentialTopLeft.getX() - 1);
			changeCoord();
			fixedBlockAndSetBlock();
		}
	}

	public void fixedBlockAndSetBlock() {
		gameBoard.fixedBlockAndSetBlock();
	}

	@Override
	public void fastDown() {
		while (isMoveDown()) {
			moveDown();
		}
		changeCoord();
		fixedBlockAndSetBlock();
	}

	public boolean isMoveDown() {
		Position x = new Position(topLeftPosition.getX() + 1, topLeftPosition.getY());
		gameBoard.revertMatrix();
		if (!isCollisionBottom(x)) {
			return true;
		} else {
			return false;
		}
	}

}
