package maze.gui;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;

import maze.logic.*;

@SuppressWarnings("serial")
public class MazeGui {

	private JFrame frame;
	private JPanel panel, jogoPanel;
	private JButton newGame;
	private JButton exit;

	private Settings settings=new Settings();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MazeGui window = new MazeGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MazeGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Labirinto");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(125, 5, 231, 35);
		frame.getContentPane().add(panel_1);

		jogoPanel = new BoardGame(settings);
		
		frame.getContentPane().add(jogoPanel);

				
		JButton btnNewButton = new JButton("Play Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Start Game",
						"Start Game?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
					startMaze();
				}

			}
		});
		btnNewButton.setBounds(40, 0, 140, 39);
		panel_1.add(btnNewButton);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(422, 0, 135, 39);
		panel_1.add(btnExit);

		JButton btnSetup = new JButton("Setup");
		btnSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog setupWindow = new JDialog();
				setupWindow.getContentPane().setLayout(new GridLayout(4, 2));
				setupWindow.setBounds(20, 20, 300, 300);
				setupWindow.setModal(true);

				setupWindow.getContentPane().add(new JLabel("Nr Dragons:"));

				JTextField nrDragoes = new JTextField("2");
				setupWindow.getContentPane().add(nrDragoes);

				setupWindow.getContentPane().add(new JLabel("Board Size:"));

				JTextField boardSize = new JTextField("15");
				setupWindow.getContentPane().add(boardSize);

				setupWindow.getContentPane().add(new JLabel("Type Dragon:"));
				ButtonGroup group = new ButtonGroup();
				JRadioButton parado = new JRadioButton("Parado");
				parado.setSelected(true);
				JRadioButton aleatorio = new JRadioButton("Aleatorio");
				JRadioButton aleatorioDormir = new JRadioButton(
						"Aleatorio+Dormir");
				JPanel teste = new JPanel();
				group.add(parado);
				teste.add(parado);
				group.add(aleatorio);
				teste.add(aleatorio);
				group.add(aleatorioDormir);
				teste.add(aleatorioDormir);

				setupWindow.getContentPane().add(teste);

				setupWindow.setVisible(true);

			}
		});
		btnSetup.setBounds(237, -1, 121, 41);
		panel_1.add(btnSetup);
		
	}


	public void startMaze() {
		
		jogoPanel=new BoardGame(settings);
		System.out.println("heroi");
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}
