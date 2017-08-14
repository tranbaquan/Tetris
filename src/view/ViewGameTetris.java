package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.IController;

public class ViewGameTetris extends JFrame implements IViewTetris {
	private static final long serialVersionUID = 1L;
	private JPanel panelGame;
	private JPanel panel;
	private IController controller;

	public ViewGameTetris(IController controller) {
		super("Tetris - TBQ");
		this.controller = controller;

	}
	

	@Override
	public void start() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(460, 660);
		panelGame = new PanelGame(controller);
		panel = new JPanel();
		panel.setBackground(new Color(66, 66, 66, 160));
		getContentPane().add(panelGame);
		getContentPane().add(panel);
		setFocusable(true);
		addEvent();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void update() {
		repaint();
	}

	public void addEvent() {
		addKeyListener(keyEvent());
	}

	public KeyListener keyEvent() {
		return new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					controller.ronate();
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					controller.fastDown();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					controller.moveLeft();
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					controller.moveRight();
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					controller.moveDown();
				}
			}
		};
	}

	@Override
	public void pause() {
		setVisible(false);
	}

	@Override
	public void lose() {
		JOptionPane.showMessageDialog(this, "You lose!");
		this.dispose();
	}

	@Override
	public void resume() {
		setVisible(true);
	}


	@Override
	public void hidden() {
		setVisible(false);
	}
}
