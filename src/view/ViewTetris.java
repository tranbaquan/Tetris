package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controller.IController;

public class ViewTetris extends JFrame implements IViewTetris {
	private static final long serialVersionUID = 1L;
	private JButton newgamebt, optionbt, quitbt, resumebt, levelbt, highscorebt;
	private IController controller;

	public ViewTetris(IController controller) {
		super("Tetris - TBQ");
		this.controller = controller;
		init();
	}

	public void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		setContentPane(new JLabel(new ImageIcon("image\\tetris.png")));
		getContentPane().setLayout(null);
		addButton();
		mouseEventButton();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void addButton() {
		/** GridLayout */
		JPanel p = new JPanel(new GridLayout(6, 1, 5, 5));
		p.setSize(new Dimension(200, 250));
		/** create button */
		newgamebt = new JButton("New Game");
		resumebt = new JButton("Resume");
		levelbt = new JButton("Level");
		highscorebt = new JButton("High Score");
		optionbt = new JButton("Option");
		quitbt = new JButton("Quit");
		/** set font and size for text in button */
		newgamebt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
		resumebt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
		highscorebt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
		levelbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
		optionbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
		quitbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
		/** set no border for button */
		newgamebt.setBorder(null);
		resumebt.setBorder(null);
		highscorebt.setBorder(null);
		levelbt.setBorder(null);
		optionbt.setBorder(null);
		quitbt.setBorder(null);
		/** hiden background button */
		newgamebt.setFocusPainted(false);
		resumebt.setFocusPainted(false);
		highscorebt.setFocusPainted(false);
		levelbt.setFocusPainted(false);
		optionbt.setFocusPainted(false);
		quitbt.setFocusPainted(false);
		newgamebt.setContentAreaFilled(false);
		resumebt.setContentAreaFilled(false);
		highscorebt.setContentAreaFilled(false);
		levelbt.setContentAreaFilled(false);
		optionbt.setContentAreaFilled(false);
		quitbt.setContentAreaFilled(false);
		/** no bug for hide background jbutton */
		p.setOpaque(false);
		/** add button ==> layout */
		p.add(newgamebt);
		p.add(resumebt);
		p.add(highscorebt);
		p.add(levelbt);
		p.add(optionbt);
		p.add(quitbt);
		p.setLocation(80, 200);
		getContentPane().add(p);
	}

	public void mouseEventButton() {
		newgamebt.addMouseListener(addEvent());
		resumebt.addMouseListener(addEvent());
		highscorebt.addMouseListener(addEvent());
		levelbt.addMouseListener(addEvent());
		optionbt.addMouseListener(addEvent());
		quitbt.addMouseListener(addEvent());
	}

	public MouseListener addEvent() {
		return new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				if (e.getSource() == newgamebt) {
					newgamebt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
				}
				if (e.getSource() == resumebt) {
					resumebt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
				}
				if (e.getSource() == highscorebt) {
					highscorebt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
				}
				if (e.getSource() == levelbt) {
					levelbt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
				}
				if (e.getSource() == optionbt) {
					optionbt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
				}
				if (e.getSource() == quitbt) {
					quitbt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (e.getSource() == newgamebt) {
					newgamebt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
				}
				if (e.getSource() == resumebt) {
					resumebt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
				}
				if (e.getSource() == highscorebt) {
					highscorebt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
				}
				if (e.getSource() == levelbt) {
					levelbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
				}
				if (e.getSource() == optionbt) {
					optionbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
				}
				if (e.getSource() == quitbt) {
					quitbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
				}
				super.mouseExited(e);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == newgamebt) {
					startGame();
				}
				if (e.getSource() == quitbt) {
					closeGame();
				}
				if (e.getSource() == optionbt) {
					// TODO
				}
				if (e.getSource() == resumebt) {
					resume();
				}
				if (e.getSource() == highscorebt) {
					// TODO
				}
				if (e.getSource() == levelbt) {
					showLevel();
				}
				super.mouseClicked(e);
			}

		};
	}

	public void resume() {
			controller.resume();
	}

	public void closeGame() {
		if (JOptionPane.showConfirmDialog(this, "Are you sure to close this game?", "Close Tetris",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public void startGame() {
		setVisible(false);
		controller.start();
	}

	@Override
	public void start() {

	}

	@Override
	public void update() {

	}

	@Override
	public void pause() {
		setVisible(true);
	}

	@Override
	public void lose() {
		setVisible(true);
	}

	public void showLevel() {
		new ViewLevel(controller);
	}

	@Override
	public void hidden() {
		setVisible(false);
	}
}
