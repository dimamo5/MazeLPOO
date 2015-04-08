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
	private JPanel panelMenu, jogoPanel;

	
	private Labirinto lab;
	private Settings settings = new Settings();
	private MazeIOFile =new MazeIOFile(new File("resources/save.dat"),lab);

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
		frame.setMinimumSize(new Dimension(settings.getMazeSize() * BoardGame.TILESIZE+10, 10+settings.getMazeSize() * BoardGame.TILESIZE));
		frame.setBounds(100, 100,settings.getMazeSize() * BoardGame.TILESIZE+10, 50 + settings.getMazeSize() * BoardGame.TILESIZE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		panelMenu = new JPanel();
		panelMenu.setBounds(0, 0, frame.getWidth(), 50);
		panelMenu.setLayout(new GridLayout(1,3));
		frame.getContentPane().add(panelMenu);

		JButton btnNewButton = new JButton("Play Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Start Game", "Start Game?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
					startMaze();
				}

			}
		});
		
		panelMenu.add(btnNewButton);

		JButton btnExit = new 
				JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		

		panelMenu.add(btnExit);

		JButton btnSetup = new SettingButton("Setup", settings);

		panelMenu.add(btnSetup);

		settings = ((SettingButton) btnSetup).getSettings();


		jogoPanel = new BoardGame(settings);

		jogoPanel.setBounds(0, 50, settings.getMazeSize() * BoardGame.TILESIZE, settings.getMazeSize() * BoardGame.TILESIZE);
		
		frame.getContentPane().add(jogoPanel);
		
		frame.pack();
	}

	public void startMaze() {
		frame.setMinimumSize(new Dimension(10+settings.getMazeSize() * BoardGame.TILESIZE, 90+settings.getMazeSize() * BoardGame.TILESIZE));
		
		jogoPanel = new BoardGame(settings);
		frame.getContentPane().add(jogoPanel);
		
		jogoPanel.setBounds(0, 50, settings.getMazeSize() * BoardGame.TILESIZE, settings.getMazeSize() * BoardGame.TILESIZE);
		panelMenu.setBounds(0,0,frame.getWidth(),50);
		
		jogoPanel.requestFocus();
		jogoPanel.repaint();
		
		
		
		
		frame.pack();
		System.out.println("Novo Jogo");
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
