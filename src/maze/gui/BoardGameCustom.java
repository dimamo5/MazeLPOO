package maze.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import maze.logic.Labirinto;

@SuppressWarnings("serial")
public class BoardGameCustom extends JPanel implements MouseListener {
	private JPanel options;
	private Labirinto lab;
	private Settings set;

	public static final int TILESIZE = 40;

	BufferedImage hero, sword, wall, dragon, dart, shield, hero_armed, floor, dragon_sleep, exit;

	public BoardGameCustom(Labirinto lab, Settings settings) {
		this.loadImages();
		this.lab = lab;
		this.set = settings;
		this.setVisible(true);

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g); // limpa fundo ...

		// nrDardos.repaint();

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

				int xi, yi;
				xi = j * TILESIZE;
				yi = i * TILESIZE;

				g.drawImage(img, xi, yi, TILESIZE, TILESIZE, null);
			}
		}

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
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX());
		System.out.println(e.getY());

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
