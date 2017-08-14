package model;

import java.awt.*;
import java.util.*;

import controller.IController;

public class GameBoard implements Runnable, IModel {
	public static final int COLS = 10;
	public static final int ROWS = 22;
	public static final int BLOCK_SIZE = 30;
	private int score;
	private int highScore;
	public int level;
	private int[][] matrixBoard;
	private int[][] tempMatrixBoard;
	private int[][] matrixNextBlock;
	private Block currentBlock;
	private Block nextBlock;
	private boolean start;
	private IController controller;

	public GameBoard(IController controller) {
		this.controller = controller;
		init();
	}

	public void init() {
		start = true;
		score = 0;
		highScore = 0;
		level = 1;
		matrixBoard = new int[ROWS][COLS];
		tempMatrixBoard = new int[ROWS][COLS];
		for (int i = 0; i < matrixBoard.length; i++) {
			for (int j = 0; j < matrixBoard[i].length; j++) {
				matrixBoard[i][j] = -1;
				tempMatrixBoard[i][j] = -1;
			}
		}
		setCurrentBlock();
	}

	public void setNextBlock() {
		nextBlock = randomBlock();
		matrixNextBlock = new int[4][4];
		for (int i = 0; i < nextBlock.coord.length; i++) {
			matrixNextBlock[nextBlock.coord[i].getX() + 2][nextBlock.coord[i].getY() + 1] = 2;
		}
	}

	

	public void setCurrentBlock() {
		if (nextBlock == null) {
			setNextBlock();
		}
		currentBlock = nextBlock;
		setNextBlock();
	}

	public Block randomBlock() {
		Random r = new Random();
		Block block = null;
		int blockNumber = r.nextInt(7);
		switch (blockNumber) {
		case 0:
			block = new SBlock(this);
			break;
		case 1:
			block = new ZBlock(this);
			break;
		case 2:
			block = new IBlock(this);
			break;
		case 3:
			block = new LBlock(this);
			break;
		case 4:
			block = new JBlock(this);
			break;
		case 5:
			block = new TBlock(this);
			break;
		case 6:
			block = new OBlock(this);
			break;
		default:
			block = new SBlock(this);
			break;
		}
		return block;
	}

	@Override
	public void start() {
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while (true) {
			if (start) {
				drop();
			}
			sleep();
		}
	}

	private void sleep() {
		int speed = 0;
		if (level == 1) {
			speed = 1000;
		}
		if (level == 2) {
			speed = 600;
		}
		if (level == 3) {
			speed = 300;
		}
		try {
			Thread.sleep(speed);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void spin() {
		currentBlock.performSpin();
	}

	@Override
	public void drop() {
		currentBlock.drop();
	}

	@Override
	public void moveLeft() {
		currentBlock.moveLeft();
	}

	@Override
	public void moveRight() {
		currentBlock.moveRight();
	}

	@Override
	public void moveDown() {
		currentBlock.moveDown();
	}

	public void update() {
		controller.setChange();
	}

	@Override
	public void fastDown() {
		currentBlock.fastDown();
	}

	@Override
	public void pause() {
		start = false;
	}

	@Override
	public void resume() {
		start = true;
	}
	
	@Override
	public void setLevel(int level) {
		this.level = level;
	}


	@Override
	public void draw(Graphics g) {
		drawScore(g);
		drawHighScore(g);
		drawLevel(g);
		drawNextBlock(g);
		drawBoard(g);
	}

	public void drawBoard(Graphics g) {

		for (int i = 2; i < matrixBoard.length; i++) {
			for (int j = 0; j < matrixBoard[i].length; j++) {
				int color = matrixBoard[i][j];
				switch (color) {
				case 0:
					g.setColor(new Color(201, 115, 255));
					break;
				case 1:
					g.setColor(new Color(247, 148, 30));
					break;
				case 2:
					g.setColor(new Color(255, 227, 87));
					break;
				case 3:
					g.setColor(new Color(84, 214, 0));
					break;
				case 4:
					g.setColor(new Color(0, 222, 255));
					break;
				case 5:
					g.setColor(new Color(255, 36, 55));
					break;
				case 6:
					g.setColor(new Color(0, 165, 224));
					break;

				default:
					if ((i + j) % 2 == 0) {
						g.setColor(new Color(66, 66, 66, 100));
					} else {
						g.setColor(new Color(66, 66, 66, 120));
					}
					break;
				}

				g.fillRoundRect(10 + (j) * BLOCK_SIZE, 10 + (i - 2) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, 5, 5);
				g.setColor(new Color(66, 66, 66, 180));
				g.drawRoundRect(10 + (j) * BLOCK_SIZE, 10 + (i - 2) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, 5, 5);
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(6));
				g2.drawRoundRect(5, 5, BLOCK_SIZE * 10 + 10, BLOCK_SIZE * 20 + 10, 5, 5);
				g2.setStroke(new BasicStroke(2));
			}
		}
	}

	public void drawNextBlock(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("SVN-Block", Font.BOLD, 30));
		g2.drawString("Next", 20 + 10 * BLOCK_SIZE, 35);
		for (int i = 0; i < matrixNextBlock.length; i++) {
			for (int j = 0; j < matrixNextBlock[i].length; j++) {
				int position = matrixNextBlock[i][j];
				if (position == 2) {
					g2.setColor(nextBlock.getColor());
				} else {
					g2.setColor(new Color(66, 66, 66, 100));
				}
				g2.fillRoundRect(20 + 10 * BLOCK_SIZE + j * BLOCK_SIZE, 40 + i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, 5,
						5);
				g2.setColor(new Color(66, 66, 66, 180));
				g2.drawRoundRect(20 + 10 * BLOCK_SIZE + j * BLOCK_SIZE, 40 + i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, 5,
						5);
			}
		}
	}

	public void drawScore(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("SVN-Block", Font.BOLD, 30));
		g2.drawString("Score", 20 + 10 * BLOCK_SIZE, 185);
		g2.setColor(new Color(66, 66, 66, 180));
		g2.drawRoundRect(20 + 10 * BLOCK_SIZE, 190, BLOCK_SIZE * 4, BLOCK_SIZE * 1, 5, 5);
		g2.setFont(new Font("Digital-7", Font.LAYOUT_RIGHT_TO_LEFT, 30));
		g2.setColor(Color.GREEN);
		g2.drawString(score + "", 20 + 10 * BLOCK_SIZE, 215);
	}

	public void drawHighScore(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("SVN-Block", Font.BOLD, 30));
		g2.drawString("High", 20 + 10 * BLOCK_SIZE, 245);
		g2.setColor(new Color(66, 66, 66, 180));
		g2.drawRoundRect(20 + 10 * BLOCK_SIZE, 255, BLOCK_SIZE * 4, BLOCK_SIZE * 1, 5, 5);
		g2.setFont(new Font("Digital-7", Font.LAYOUT_RIGHT_TO_LEFT, 30));
		g2.setColor(Color.GREEN);
		g2.drawString(highScore + "", 20 + 10 * BLOCK_SIZE, 280);
	}

	public void drawLevel(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("SVN-Block", Font.BOLD, 30));
		g2.drawString("Level", 20 + 10 * BLOCK_SIZE, 310);
		g2.setColor(new Color(66, 66, 66, 180));
		g2.drawRoundRect(20 + 10 * BLOCK_SIZE, 315, BLOCK_SIZE * 4, BLOCK_SIZE * 1, 5, 5);
		g2.setFont(new Font("Digital-7", Font.LAYOUT_RIGHT_TO_LEFT, 30));
		g2.setColor(Color.GREEN);
		if (level == 1) {
			g2.drawString("Easy", 20 + 10 * BLOCK_SIZE, 340);
		}
		if (level == 2) {
			g2.drawString("Normal", 20 + 10 * BLOCK_SIZE, 340);
		}
		if (level == 3) {
			g2.drawString("Hard", 20 + 10 * BLOCK_SIZE, 340);
		}
	}

	public void fixedBlockAndSetBlock() {
		clear();
		for (int i = 0; i < matrixBoard.length; i++) {
			for (int j = 0; j < matrixBoard[i].length; j++) {
				tempMatrixBoard[i][j] = matrixBoard[i][j];
			}
		}
		if (isLose()) {
			lose();
		}
		setCurrentBlock();
	}

	public void lose() {
		start = false;
		controller.lose();

	}

	public boolean isLose() {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < matrixBoard[i].length; j++) {
				if (matrixBoard[i][j] != -1) {
					return true;
				}
			}
		}
		return false;
	}
	/** add position to array */
	public void addPosition(Position position, int value) {
		matrixBoard[position.getX()][position.getY()] = value;
	}
	/** check location block is exist or out array */
	public boolean isExistOrOutArray(Position position) {
		if (position.getX() > ROWS - 1) {
			return true;
		}
		if (position.getY() < 0) {
			return true;
		}
		if (position.getY() > COLS - 1) {
			return true;
		}
		if (matrixBoard[position.getX()][position.getY()] != -1) {
			return true;
		}
		return false;
	}
	/** check collistion when spin */
	public boolean isCollistionSpin(Position position) {
		if (position.getX() > ROWS - 1) {
			return true;
		}
		if (position.getX() < 0) {
			return true;
		}
		if (position.getY() < 0) {
			return true;
		}
		if (position.getY() > COLS - 1) {
			return true;
		}
		if (matrixBoard[position.getX()][position.getY()] != -1) {
			return true;
		}
		return false;
	}
	/** revert matrix to previous state */
	public void revertMatrix() {
		for (int i = 0; i < matrixBoard.length; i++) {
			for (int j = 0; j < matrixBoard[i].length; j++) {
				matrixBoard[i][j] = tempMatrixBoard[i][j];
			}
		}
	}
	/** clear line */
	public void clear() {
		for (int i = 0; i < matrixBoard.length; i++) {
			if (isFullRow(i)) {
				delete(i);
				score += 10;
				checkHighScore();
			}
		}
		update();
	}
	/** check high score */ 
	public void checkHighScore() {
		if (highScore < score) {
			highScore = score;
		}

	}
	/** delete line */
	public void delete(int line) {
		int[][] temp = new int[line][COLS];
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < matrixBoard[i].length; j++) {
				temp[i][j] = matrixBoard[i][j];
			}
		}
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				matrixBoard[i + 1][j] = temp[i][j];
			}
		}
	}
	/** check is full row */
	public boolean isFullRow(int line) {
		for (int i = 0; i < matrixBoard[line].length; i++) {
			if (matrixBoard[line][i] == -1) {
				return false;
			}
		}
		return true;
	}

}
