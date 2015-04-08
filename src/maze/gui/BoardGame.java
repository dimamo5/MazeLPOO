package maze.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import maze.logic.Labirinto;

@SuppressWarnings("serial")
public class BoardGame extends JPanel implements KeyListener {

	public static final int TILESIZE = 40;
	private Settings set;
	private Labirinto lab;

	// imagens
	BufferedImage hero, sword, wall, dragon, dart, shield, hero_armed, floor, dragon_sleep, exit;

	public BoardGame(Settings settings) {
		addKeyListener(this);
		setFocusable(true);
		this.loadImages();
		this.setLayout(new FlowLayout());
		//this.setMinimumSize(new Dimension(set.getMazeSize() * TILESIZE, set.getMazeSize() * TILESIZE));
		//this.setBounds(0, 50, set.getMazeSize() * TILESIZE, set.getMazeSize() * TILESIZE);
		this.setVisible(true);
		lab = new Labirinto(settings.getNumDragons(), settings.getTypeDragons(), settings.getMazeType(), settings.getMazeSize());
		set = settings;

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g); // limpa fundo ...

		BufferedImage img = wall; // default init

		for (int i = 0; i < lab.getTabuleiro().getTamanho(); i++) {
			for (int j = 0; j < lab.getTabuleiro().getTamanho(); j++) {

				if (lab.getTabuleiro().getTab()[i][j] == 'X') {
					img = wall;
				} else if (lab.getTabuleiro().getTab()[i][j] == 'H') {
					img = hero;
				} else if (lab.getTabuleiro().getTab()[i][j] == 'E') {
					img = sword;
				} else if (lab.getTabuleiro().getTab()[i][j] == 'D') {
					img = dragon;
				} else if (lab.getTabuleiro().getTab()[i][j] == 'Z') {
					img = dragon_sleep;
				} else if (lab.getTabuleiro().getTab()[i][j] == ' ') {
					img = floor;
				} else if (lab.getTabuleiro().getTab()[i][j] == '«') {
					img = dart;
				} else if (lab.getTabuleiro().getTab()[i][j] == 'P') {
					img = shield;
				} else if (lab.getTabuleiro().getTab()[i][j] == 'S') {
					img = exit;
				}
				// caso especial : coincidem drago / espada else
				else if (lab.getTabuleiro().getTab()[i][j] == 'F') {
					img = dragon;
				} else if (lab.getTabuleiro().getTab()[i][j] == 'A') {
					img = hero;
				}

				int xi, yi, xf, yf;
				xi = j * TILESIZE;
				yi = i * TILESIZE;
				// xf = this.getWidth() / maze_ln;
				// yf = this.getHeight() / maze_ln;

				g.drawImage(img, xi, yi,TILESIZE,TILESIZE, null);
			}
		}
		g.dispose();

	}

	public void loadImages() {
		try {
			hero = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\hero.png"));
			sword = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\sword.png"));
			wall = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\wall.png"));
			floor = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\floor.png"));
			dragon = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\dragon.png"));
			dart = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\dard.png"));
			shield = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\shield.png"));
			exit = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\exit.png"));
			// hero_armed =
			// ImageIO.read(this.getClass().getResource("heroarmed.jpg"));
			dragon_sleep = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\dragonSleep.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == set.getUp()) {
			lab.moveUp();
		} else if (e.getKeyCode() == set.getDown()) {
			lab.moveDown();
		} else if (e.getKeyCode() == set.getLeft()) {
			lab.moveLeft();
		} else if (e.getKeyCode() == set.getRight()) {
			lab.moveRight();
		}
		lab.updateDragons();

		repaint();

		if (lab.getHeroi().getActive() == false) {
			if (lab.gameOver()) {
				JOptionPane.showMessageDialog(null, "Winner", "Winner", JOptionPane.YES_NO_OPTION);
			} else {
				JOptionPane.showMessageDialog(null, "Loser", "Loser", JOptionPane.YES_NO_OPTION);
			}
			this.setEnabled(false);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
