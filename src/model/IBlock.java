package model;

import java.awt.Color;

public class IBlock extends Block {

	public IBlock(GameBoard gameBoard) {
		super(gameBoard);
		init();
	}

	@Override
	public void init() {
		setSpinBehavior(new SpinWithBlock4x4());
		setTopLeftPosition(new Position(1, 4));
		potentialTopLeft = new Position(2, 4);
		color = new Color(255, 227, 87);
		coord = new Position[] { new Position(0, -1), new Position(0, 0), new Position(0, 1), new Position(0, 2) };
		tempCoord = new Position[] { new Position(0, -1), new Position(0, 0), new Position(0, 1), new Position(0, 2) };
	}

	@Override
	public void changeCoord() {
		gameBoard.addPosition(topLeftPosition.sum(coord[0]), 2);
		gameBoard.addPosition(topLeftPosition.sum(coord[1]), 2);
		gameBoard.addPosition(topLeftPosition.sum(coord[2]), 2);
		gameBoard.addPosition(topLeftPosition.sum(coord[3]), 2);
	}

}
