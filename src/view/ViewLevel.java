package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.IController;

public class ViewLevel extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton easybt, normalbt, hardbt;
	private JPanel panel;
	private IController controller;
	public ViewLevel(IController controller) {
		this.controller = controller;
		init();
	}

	public void init() {
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		setSize(200,250);
		getContentPane().setLayout(null);
		addButton();
		mouseEventButton();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void addButton() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1, 5, 5));
		panel.setSize(new Dimension(160, 210));
		easybt = new JButton("Easy");
		normalbt = new JButton("Normal");
		hardbt = new JButton("Hard");
		easybt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
		normalbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
		hardbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
		easybt.setBorder(null);
		normalbt.setBorder(null);
		hardbt.setBorder(null);
		easybt.setFocusPainted(false);
		normalbt.setFocusPainted(false);
		hardbt.setFocusPainted(false);
		easybt.setContentAreaFilled(false);
		normalbt.setContentAreaFilled(false);
		hardbt.setContentAreaFilled(false);
		if(controller.getLevel() == 1){
			easybt.setForeground(Color.RED);
		}else if(controller.getLevel() == 2){
			normalbt.setForeground(Color.RED);
		}else{
			hardbt.setForeground(Color.RED);
		}
		panel.setOpaque(false);
		panel.add(easybt);
		panel.add(normalbt);
		panel.add(hardbt);
		panel.setLocation(20, 0);
		getContentPane().add(panel);
	}

	public void mouseEventButton() {
		easybt.addMouseListener(addEvent());
		normalbt.addMouseListener(addEvent());
		hardbt.addMouseListener(addEvent());
	}

	public MouseListener addEvent() {
		return new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				if (e.getSource() == easybt) {
					easybt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
				}
				if (e.getSource() == normalbt) {
					normalbt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
				}
				if (e.getSource() == hardbt) {
					hardbt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (e.getSource() == easybt) {
					easybt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
				}
				if (e.getSource() == normalbt) {
					normalbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
				}
				if (e.getSource() == hardbt) {
					hardbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
				}
				super.mouseExited(e);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == easybt) {
					controller.setLevel(1);
					dispose();
				}
				if (e.getSource() == normalbt) {
					controller.setLevel(2);
					dispose();
				}
				if (e.getSource() == hardbt) {
					controller.setLevel(3);
					dispose();
				}
				super.mouseClicked(e);
			}

		};
	}
}
