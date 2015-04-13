package maze.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import maze.logic.Dragao;
import maze.logic.Labirinto;
import maze.logic.Peca;
import maze.logic.Pos;
import maze.logic.Tabuleiro;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class BoardGameCustom extends JPanel implements MouseListener, MouseMotionListener {

	private JPanel options;
	private Labirinto lab;
	private Settings set;
	
	private char selected = 'X';

	public static final int TILESIZE = 40;

	BufferedImage hero, sword, wall, dragon, bomb, shield, floor, dragon_sleep, exit;
	private ArrayList<Dragao> dragoes = new ArrayList<Dragao>();
	private ArrayList<Peca> dardos = new ArrayList<Peca>();

	public BoardGameCustom(Labirinto lab, Settings settings) {
		this.addMouseListener(this);
		this.setLayout(null);
		this.addMouseMotionListener(this);
		this.loadImages();
		this.lab = lab;
		this.set = settings;
		this.setVisible(true);
		initCustomMaze();

		options = new JPanel();
		options.setLayout(new GridLayout(8, 1));
		options.setBounds(settings.getMazeSize() * BoardGame.TILESIZE, 0, TILESIZE, 6 * TILESIZE);
		options.setVisible(true);
		options.setEnabled(true);

		JButton heroiBtn = new JButton();
		heroiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selected = 'H';
			}
		});
		heroiBtn.setMaximumSize(new Dimension(TILESIZE, TILESIZE));
		heroiBtn.setIcon(new ImageIcon(hero));
		options.add(heroiBtn);

		JButton exitBtn = new JButton();
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 'S';
			}
		});
		exitBtn.setIcon(new ImageIcon(exit));
		options.add(exitBtn);

		JButton swordBtn = new JButton();
		swordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 'E';
			}
		});
		swordBtn.setIcon(new ImageIcon(sword));
		options.add(swordBtn);

		JButton shieldBtn = new JButton();
		shieldBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 'P';
			}
		});
		shieldBtn.setIcon(new ImageIcon(shield));
		options.add(shieldBtn);

		JButton dragonBtn = new JButton();
		dragonBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 'D';
			}
		});
		dragonBtn.setIcon(new ImageIcon(dragon));
		options.add(dragonBtn);

		JButton dartBtn = new JButton();
		dartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = '«';
			}
		});
		dartBtn.setIcon(new ImageIcon(bomb));
		options.add(dartBtn);

		JButton wallBtn = new JButton();
		wallBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 'X';
			}
		});
		wallBtn.setIcon(new ImageIcon(wall));
		options.add(wallBtn);

		JButton floorBtn = new JButton();
		floorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = ' ';
			}
		});
		floorBtn.setIcon(new ImageIcon(floor));
		options.add(floorBtn);

		this.add(options);
		this.repaint();

	}

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
					img = bomb;
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

				g.drawImage(floor, xi, yi, TILESIZE, TILESIZE, null);
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
			bomb = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\bomb.png"));
			shield = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\shield.png"));
			exit = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\exit.png"));
			dragon_sleep = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\dragonSleep.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void initCustomMaze() {
		char[][] tabTemp = new char[set.getMazeSize()][set.getMazeSize()];

		for (int i = 0; i < set.getMazeSize(); i++) {
			for (int j = 0; j < set.getMazeSize(); j++) {
				if (i == 0 || j == 0) {
					tabTemp[i][j] = 'X';
				} else if (i == set.getMazeSize() - 1 || j == set.getMazeSize() - 1) {
					tabTemp[i][j] = 'X';
				} else {
					tabTemp[i][j] = ' ';
				}
			}
		}

		lab.setTabuleiro(new Tabuleiro(set.getMazeSize()));
		lab.getTabuleiro().setTabuleiro(tabTemp);

		lab.getTabuleiro().setTamanho(set.getMazeSize());

		lab.getTabuleiro().setExit(new Pos((lab.getTabuleiro().getTamanho() - 1) / 2, lab.getTabuleiro().getTamanho() - 1));

		lab.getTabuleiro().setChar(lab.getTabuleiro().getExit().getX(), lab.getTabuleiro().getExit().getY(), 'S');

		lab.getHeroi().setPos(new Pos(1, 1));
		lab.getHeroi().setActive(false);
		lab.getEspada().setPos(new Pos(1, 1));
		lab.getEspada().setActive(false);
		lab.getEscudo().setPos(new Pos(1, 1));
		lab.getEscudo().setActive(false);
	}

	// desactiva peca se a posicao desta for reescrita
	public void checkPosTab(char c, int x, int y) {

		if (c == 'H') {
			lab.getHeroi().setActive(false);
		} else if (c == 'E') {
			lab.getEspada().setActive(false);
		} else if (c == 'P') {
			lab.getEscudo().setActive(false);
		} else if (c == 'D' || c == 'Z') {
			for (Dragao d : dragoes) {
				if (d.getPos().getX() == x && d.getPos().getY() == y) {
					d.setActive(false);
				}
			}
		} else if (c == '«') {
			for (Peca d : dardos) {
				if (d.getPos().getX() == x && d.getPos().getY() == y) {
					d.setActive(false);
				}
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int coordX = e.getX() / TILESIZE;
		int coordY = e.getY() / TILESIZE;

		if (coordX >= lab.getTabuleiro().getTamanho() || coordY >= lab.getTabuleiro().getTamanho()) {
			return;
		}

		if (selected == 'X' || selected == ' ') {
			checkPosTab(lab.getTabuleiro().getTab()[coordY][coordX], coordX, coordY);
			lab.getTabuleiro().setChar(coordX, coordY, selected);
		}

		if (selected == 'S'
				&& !(coordX > 0 && coordX < lab.getTabuleiro().getTamanho() - 1 && coordY > 0 && coordY < lab.getTabuleiro().getTamanho() - 1)) {

			lab.getTabuleiro().setChar(lab.getTabuleiro().getExit().getX(), lab.getTabuleiro().getExit().getY(), 'X');

			lab.getTabuleiro().setExit(new Pos(coordX, coordY));
			lab.getTabuleiro().setChar(coordX, coordY, selected);
			repaint();
		}

		// nao pode meter objectos nos limites do labirinto
		if (!(coordX > 0 && coordX < lab.getTabuleiro().getTamanho() - 1 && coordY > 0 && coordY < lab.getTabuleiro().getTamanho() - 1))
			return;

		if (selected == 'H') {
			checkPosTab(lab.getTabuleiro().getTab()[coordY][coordX], coordX, coordY);

			if (lab.getHeroi().getActive())
				lab.getTabuleiro().setChar(lab.getHeroi().getPos().getX(), lab.getHeroi().getPos().getY(), ' ');

			if (!lab.getHeroi().getActive())
				lab.getHeroi().setActive(true);

			lab.getHeroi().setPos(new Pos(coordX, coordY));
			lab.getTabuleiro().setChar(coordX, coordY, selected);
		} else if (selected == 'P') {
			checkPosTab(lab.getTabuleiro().getTab()[coordY][coordX], coordX, coordY);

			if (lab.getEscudo().getActive())
				lab.getTabuleiro().setChar(lab.getEscudo().getPos().getX(), lab.getEscudo().getPos().getY(), ' ');

			if (!lab.getEscudo().getActive())
				lab.getEscudo().setActive(true);

			lab.getEscudo().setPos(new Pos(coordX, coordY));
			lab.getTabuleiro().setChar(coordX, coordY, selected);
		} else if (selected == 'E') {
			checkPosTab(lab.getTabuleiro().getTab()[coordY][coordX], coordX, coordY);

			if (lab.getEspada().getActive())
				lab.getTabuleiro().setChar(lab.getEspada().getPos().getX(), lab.getEspada().getPos().getY(), ' ');

			if (!lab.getEspada().getActive())
				lab.getEspada().setActive(true);

			lab.getEspada().setPos(new Pos(coordX, coordY));
			lab.getTabuleiro().setChar(coordX, coordY, selected);
		}

		else if (selected == 'D') {
			checkPosTab(lab.getTabuleiro().getTab()[coordY][coordX], coordX, coordY);

			boolean reused = false;
			for (Dragao d : dragoes) {
				if (!d.getActive()) {
					reused = true;
					d.setActive(true);
					d.setPos(new Pos(coordX, coordY));
				}
			}

			if (!reused) {
				Dragao d = new Dragao(coordX, coordY, set.getTypeDragons());
				dragoes.add(d);
			}
			lab.getTabuleiro().setChar(coordX, coordY, selected);
		}

		else if (selected == '«') {
			checkPosTab(lab.getTabuleiro().getTab()[coordY][coordX], coordX, coordY);

			boolean reused = false;
			for (Peca d : dardos) {
				if (!d.getActive()) {
					reused = true;
					d.setActive(true);
					d.setPos(new Pos(coordX, coordY));
				}
			}

			if (!reused) {
				Peca d = new Peca(coordX, coordY, selected);
				dardos.add(d);
			}
			lab.getTabuleiro().setChar(coordX, coordY, selected);
		}
		repaint();
	}

	public boolean mazeEditDone() {
		return lab.getHeroi().getActive();
	}

	public void mazeEditSet() {
		Dragao drags[];
		Peca dards[];

		Iterator<Dragao> iteDrag = dragoes.iterator();

		while (iteDrag.hasNext()) {
			if (iteDrag.next().getActive() == false) {
				iteDrag.remove();
			}
		}

		drags = new Dragao[dragoes.size()];
		dragoes.toArray(drags);
		lab.setDragoes(drags);
		// =====
		Iterator<Peca> iteDard = dardos.iterator();

		while (iteDard.hasNext()) {
			if (iteDard.next().getActive() == false) {
				iteDard.remove();
			}
		}

		dards = new Peca[dardos.size()];
		dardos.toArray(dards);
		lab.setDardos(dards);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}
