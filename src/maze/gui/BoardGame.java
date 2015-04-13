package maze.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import maze.logic.Dragao;
import maze.logic.Labirinto;

@SuppressWarnings("serial")
public class BoardGame extends JPanel implements KeyListener {

	public static final int TILESIZE = 40;
	private Settings set;
	private Labirinto lab;

	// imagens
	BufferedImage hero, sword, wall, dragon, dart, shield, hero_armed, hero_shield, hero_armed_shield, floor, dragon_sleep, exit,fire;

	JLabel nrDardos;

	public BoardGame(Labirinto lab, Settings settings) {
		addKeyListener(this);
		setFocusable(true);
		this.loadImages();
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		this.lab = lab;
		set = settings;

		nrDardos = new JLabel("Dardos = " + lab.getHeroi().getNrDardos());
		nrDardos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.add(nrDardos);
		nrDardos.setForeground(Color.WHITE);
		nrDardos.setVisible(true);
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g); // limpa fundo ...

		// nrDardos.repaint();

		BufferedImage img = wall; // default init

		drawAnimation(g);
		
		for (int i = 0; i < lab.getTabuleiro().getTamanho(); i++) {
			for (int j = 0; j < lab.getTabuleiro().getTamanho(); j++) {

				if (lab.getTabuleiro().getTab()[i][j] == 'X') {
					img = wall;
				} else if (lab.getTabuleiro().getTab()[i][j] == 'H') {
					if (lab.getHeroi().isShield()) {
						img = hero_shield;
					} else {
						img = hero;
					}
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
				} else if (lab.getTabuleiro().getTab()[i][j] == 'F') {
					img = dragon;
				} else if (lab.getTabuleiro().getTab()[i][j] == 'A') {
					if (lab.getHeroi().isShield()) {
						img = hero_armed_shield;
					} else {
						img = hero_armed;
					}
				}

				int xi, yi;
				xi = j * TILESIZE;
				yi = i * TILESIZE;

				g.drawImage(floor, xi, yi, TILESIZE, TILESIZE, null);
				g.drawImage(img, xi, yi, TILESIZE, TILESIZE, null);
			}
		}
	}

	public void drawAnimation(Graphics g) {
		Thread t = new Thread() {
			public void run() {
				System.out.println("Animação");
				Dragao dragoes[] = lab.getDragoes();
				if (lab.getTabuleiro().getTab()[dragoes[0].getPos().getY()][dragoes[0].getPos().getX() + 1] == ' ') {
					g.drawImage(fire, (dragoes[0].getPos().getX() + 1) * TILESIZE, (dragoes[0].getPos().getX()) * TILESIZE, null);
					repaint();
				}
			}
		};
		t.start();
	}

	public void loadImages() {
		try {
			hero = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\hero.png"));
			hero_armed = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\heroA.png"));
			hero_shield = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\heroS.png"));
			hero_armed_shield = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\heroAS.png"));
			sword = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\sword.png"));
			wall = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\wall.png"));
			floor = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\floor.png"));
			dragon = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\dragon.png"));
			dart = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\dard.png"));
			shield = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\shield.png"));
			exit = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\exit.png"));
			dragon_sleep = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\dragonSleep.png"));
			fire=ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\fire.png"));
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
		} else if (e.getKeyCode() == set.getShootUp()) {
			lab.shotDardo('w');
		} else if (e.getKeyCode() == set.getShootDown()) {
			lab.shotDardo('s');
		} else if (e.getKeyCode() == set.getShootLeft()) {
			lab.shotDardo('a');
		} else if (e.getKeyCode() == set.getShootRigth()) {
			lab.shotDardo('d');
		}

		lab.updateDragons();

		nrDardos.setText("Dardos = " + lab.getHeroi().getNrDardos());

		lab.refreshTabuleiro();

		repaint();

		if (lab.getHeroi().getActive() == false) {
			// lab=null;
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
