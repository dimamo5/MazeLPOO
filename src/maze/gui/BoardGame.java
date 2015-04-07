package maze.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import maze.logic.Labirinto;


@SuppressWarnings("serial")
public class BoardGame extends JPanel implements KeyListener {

	private static final int TILESIZE = 40;

	private Labirinto lab;

	// imagens
	BufferedImage hero, sword, wall, dragon, dart, shield, blank, hero_armed,
			floor, dragon_sleep, exit;

	public BoardGame(Settings settings) {
		this.loadImages();
		this.setLayout(new FlowLayout());
		this.setMinimumSize(new Dimension(500, 450));
		this.setBounds(0, 50, 500, 450);
		this.setVisible(true);
		lab = new Labirinto(settings.getNumDragons(),
				settings.getTypeDragons(), settings.getMazeType(),
				settings.getMazeSize());
		this.addKeyListener(this);

	}

	@Override
	public void paintComponent(Graphics g) {
		System.out.print("lol\n");

		super.paintComponent(g); // limpa fundo ...

		BufferedImage img = wall; // default init

		for (int i = 0; i < lab.getTabuleiro().getTamanho(); i++) {
			for (int j = 0; j < lab.getTabuleiro().getTamanho(); j++) {

				if (lab.getTabuleiro().getTab()[i][j] == 'X') {
					img = wall;
				} else if (lab.getTabuleiro().getTab()[i][j] == 'H') {
					img = hero;
				} else if (lab.getTabuleiro().getTab()[i][j] == 'E') {
					// img = sword;
				} else if (lab.getTabuleiro().getTab()[i][j] == 'D') {
					// img = dragon;
				} else if (lab.getTabuleiro().getTab()[i][j] == 'Z') {
					// img = dragon_sleep;
				} else if (lab.getTabuleiro().getTab()[i][j] == ' ') {
					img = floor;
				} else if (lab.getTabuleiro().getTab()[i][j] == '«') {
					// img = dart;
				} else if (lab.getTabuleiro().getTab()[i][j] == 'P') {
					// img = shield;
				} else if (lab.getTabuleiro().getTab()[i][j] == 'S') {
					// img = exit;
				}
				// caso especial : coincidem drago / espada else
				else if (lab.getTabuleiro().getTab()[i][j] == 'F') {
					// img = wall;
				} else if (lab.getTabuleiro().getTab()[i][j] == 'A') {
					// img = hero_armed;
				}

				int xi, yi, xf, yf;
				xi = j * TILESIZE;
				yi = i * TILESIZE;
				// xf = this.getWidth() / maze_ln;
				// yf = this.getHeight() / maze_ln;

				g.drawImage(img, xi, yi, this);
			}
		}

	}

	public void loadImages() {
		try {
			hero = ImageIO.read(this.getClass().getResource("heroi.jpg"));
			// sword = ImageIO.read(this.getClass().getResource("sword.jpg"));
			wall = ImageIO.read(this.getClass().getResource("wall.png"));
			floor = ImageIO.read(this.getClass().getResource("floor.png"));
			// dragon= ImageIO.read(this.getClass().getResource("dragon.jpg"));
			// dart= ImageIO.read(this.getClass().getResource("dart.jpg"));
			// shield= ImageIO.read(this.getClass().getResource("shield.jpg"));
			// blank = ImageIO.read(this.getClass().getResource("blank.jpg"));
			// hero_armed =
			// ImageIO.read(this.getClass().getResource("heroarmed.jpg"));
			// sword = ImageIO.read(this.getClass().getResource("sword.jpg"));
			// dragon_sleep =
			// ImageIO.read(this.getClass().getResource("dragonsleep.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			System.out.print("cenas");
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
