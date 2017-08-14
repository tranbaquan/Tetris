package model;

import java.awt.Color;

public class OBlock extends Block{

	public OBlock(GameBoard gameBoard) {
		super(gameBoard);
		init();
	}

	@Override
	public void init() {
		setSpinBehavior(new SpinWithBlock2x2());
		setTopLeftPosition(new Position(1, 4));
		potentialTopLeft = new Position(2, 4);
		color = new Color(0, 165, 224);
		coord = new Position[]{new Position(-1, 0), new Position(-1, 1), new Position(0, 0), new Position(0, 1)};
		tempCoord = new Position[]{new Position(-1, 0), new Position(-1, 1), new Position(0, 0), new Position(0, 1)};
	}

	@Override
	public void changeCoord() {
		gameBoard.addPosition(topLeftPosition.sum(coord[0]), 6);
		gameBoard.addPosition(topLeftPosition.sum(coord[1]), 6);
		gameBoard.addPosition(topLeftPosition.sum(coord[2]), 6);
		gameBoard.addPosition(topLeftPosition.sum(coord[3]), 6);
	}

}
