package maze.gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;

import maze.logic.*;

@SuppressWarnings("serial")
public class MazeGui {

	private JFrame frame;
	private JPanel panelMenu, jogoPanel;

	private Labirinto lab;
	private Settings settings = new Settings();
	private MazeIOFile io = new MazeIOFile(new File("resources/save.dat"), lab);

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
		frame.setMinimumSize(new Dimension(settings.getMazeSize() * BoardGame.TILESIZE + 10, 10 + settings.getMazeSize() * BoardGame.TILESIZE));
		frame.setBounds(100, 100, settings.getMazeSize() * BoardGame.TILESIZE + 10, 50 + settings.getMazeSize() * BoardGame.TILESIZE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		panelMenu = new JPanel();
		panelMenu.setBounds(0, 0, frame.getWidth(), 50);
		panelMenu.setLayout(new GridLayout(1, 6));
		frame.getContentPane().add(panelMenu);

		JButton btnNewButton = new JButton("Play Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Start Game", "Start Game?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

					lab = new Labirinto(settings.getNumDragons(), settings.getTypeDragons(), settings.getMazeType(), settings.getMazeSize());
					startMaze();
				}

			}
		});

		panelMenu.add(btnNewButton);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		panelMenu.add(btnExit);

		JButton btnSetup = new SettingButton("Setup", settings);

		panelMenu.add(btnSetup);

		settings = ((SettingButton) btnSetup).getSettings();

		JButton save = new JButton("Save");

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lab == null) {
					JOptionPane.showMessageDialog(null, "Nenhum labirinto para guardar", "Erro", JOptionPane.ERROR_MESSAGE);
				} else {
					System.out.println("Guardou o jogo");
					io.setLab(lab);
					io.saveMaze();
				}
			}
		});

		panelMenu.add(save);

		JButton load = new JButton("Load Teste");

		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (io.getFile().length() == 0) {
					JOptionPane.showMessageDialog(null, "Nenhum labirinto guardado pode ser carregado", "Erro", JOptionPane.ERROR_MESSAGE);
				} else {
					System.out.println("Carregou jogo");
					lab = io.loadMaze();
					startMaze();
				}
			}
		});

		panelMenu.add(load);

		JButton customMaze = new JButton("Custom Maze");

		customMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lab = new Labirinto();
				startCustomMaze();
			}
		});

		panelMenu.add(customMaze);

		frame.pack();
	}

	public void startMaze() {
		frame.setMinimumSize(new Dimension(10 + settings.getMazeSize() * BoardGame.TILESIZE, 90 + settings.getMazeSize() * BoardGame.TILESIZE));

		if (jogoPanel != null) {
			frame.remove(jogoPanel);
			frame.validate();
			frame.repaint();
		}

		jogoPanel = new BoardGame(lab, settings);
		frame.getContentPane().add(jogoPanel);

		jogoPanel.setBounds(0, 50, settings.getMazeSize() * BoardGame.TILESIZE, settings.getMazeSize() * BoardGame.TILESIZE);

		panelMenu.setBounds(0, 0, frame.getWidth(), 50);

		jogoPanel.requestFocus();
		jogoPanel.repaint();
		frame.pack();

	}

	public void startCustomMaze() {
		frame.setMinimumSize(new Dimension(10 + settings.getMazeSize() * BoardGame.TILESIZE + BoardGame.TILESIZE, 90 + settings.getMazeSize()
				* BoardGame.TILESIZE));

		if (jogoPanel != null) {
			frame.remove(jogoPanel);
			frame.validate();
			frame.repaint();
		}

		jogoPanel = new BoardGameCustom(lab, settings);
		jogoPanel.setBounds(0, 50, frame.getWidth(), frame.getHeight());
		jogoPanel.setMinimumSize(new Dimension(settings.getMazeSize() * BoardGame.TILESIZE + BoardGame.TILESIZE, settings.getMazeSize()
				* BoardGame.TILESIZE));

		frame.getContentPane().add(jogoPanel);

		panelMenu.setBounds(0, 0, frame.getWidth(), 50);

		// frame.pack();

		jogoPanel.requestFocus();
		jogoPanel.repaint();

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
