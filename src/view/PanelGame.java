package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.IController;
import model.GameBoard;

public class PanelGame extends JPanel {
	private static final long serialVersionUID = 1L;
	private IController controller;
	private JButton backbt;

	public PanelGame(IController controller) {
		this.controller = controller;
		init();
		drawButton();
	}

	public void drawButton() {
		backbt = new JButton();
		ImageIcon backIcon = new ImageIcon("image/back.png");
		Image backImg = backIcon.getImage();
		Image newsize = backImg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		backIcon = new ImageIcon(newsize);
		backbt.setIcon(backIcon);
		backbt.setBorder(null);
		backbt.setFocusPainted(false);
		backbt.setContentAreaFilled(false);
		setOpaque(false);
		backbt.setBounds(20 + 10 * GameBoard.BLOCK_SIZE, 550, 120, 50);
		addEvent();
		add(backbt);
	}

	public void addEvent() {
		backbt.addMouseListener(buttonEvent());
	}
	
	private MouseListener buttonEvent() {
		return new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				if (e.getSource() == backbt) {
					ImageIcon backIcon = new ImageIcon("image/back.png");
					Image backImg = backIcon.getImage();
					Image newsize = backImg.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
					backIcon = new ImageIcon(newsize);
					backbt.setIcon(backIcon);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				if (e.getSource() == backbt) {
					ImageIcon backIcon = new ImageIcon("image/back.png");
					Image backImg = backIcon.getImage();
					Image newsize = backImg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
					backIcon = new ImageIcon(newsize);
					backbt.setIcon(backIcon);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				controller.pause();
			}
		};
	}

	private void init() {
		setSize(new Dimension(450, 620));
		setLayout(null);
		setBackground(new Color(66, 66, 66, 160));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		controller.draw(g);
	}
}
