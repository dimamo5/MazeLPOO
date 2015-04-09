package maze.logic;

import java.io.Serializable;
import java.util.Random;

import maze.cli.Cli;

@SuppressWarnings("serial")
public class Labirinto implements Serializable {

	private Heroi heroi = new Heroi(0, 0); // criar construtor sem parametros ou
	// passar parametros random
	private Peca espada = new Peca(0, 0, 'E');
	private Peca escudo = new Peca(0, 0, 'P');
	private Peca dardos[];

	private Dragao dragoes[];

	private boolean coincide = false;

	private Tabuleiro tabuleiro;

	// construtor

	public Labirinto() {
	}

	public Labirinto(int quantidadeDragao, char tipoDragao, int mazeType, int mazeSize) {
		IMazeBuilder mazeGenerator;

		if (mazeType == 1) {
			mazeGenerator = new AutoBuilder(mazeSize);
		} else {
			mazeGenerator = new ManualBuilder();
		}

		tabuleiro = mazeGenerator.build();

		dragoes = new Dragao[quantidadeDragao];

		while (quantidadeDragao > 0) {
			dragoes[quantidadeDragao - 1] = new Dragao(0, 0, tipoDragao);
			quantidadeDragao--;

		}

		initTabuleiro();
	}

	public Peca[] getDardos() {
		return dardos;
	}

	public void setDardos(Peca[] dardos) {
		this.dardos = dardos;
	}

	public Heroi getHeroi() {
		return heroi;
	}

	public void setHeroi(Heroi heroi) {
		this.heroi = heroi;
	}

	public Peca getEspada() {
		return espada;
	}

	public void setEspada(Peca espada) {
		this.espada = espada;
	}

	public Dragao[] getDragoes() {
		return dragoes;
	}

	public void setDragoes(Dragao[] dragoes) {
		this.dragoes = dragoes;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public Peca getEscudo() {
		return escudo;
	}

	public void setEscudo(Peca escudo) {
		this.escudo = escudo;
	}

	// ==========================================================
	// MAIN
	public static void main(String[] args) { // TODO: PODE ACONTECER O HEROI
		// FAZER SPAWN ADJACENTE AO
		// DRAGAO
		new Cli();
	}

	// ==========================================================

	public void updateDragons() {
		for (Dragao d : dragoes) {
			if (d.getActive()) {
				d.updateSleeping();
				if (d.isDormir()) {
					tabuleiro.setChar(d.getPos().getX(), d.getPos().getY(), d.getSigla());
				} else {
					moverDragao(d);
				}
			}
		}

		checkCollision();
	}

	// inicializacao
	public void initTabuleiro() {

		boolean posicaoHeroiOK = false;

		atribuiCoos(heroi);
		atribuiCoos(espada);
		atribuiCoos(escudo);

		dardos = new Peca[dragoes.length];

		for (int i = 0; i < dragoes.length; i++) {
			dardos[i] = new Peca(0, 0, '«');
			atribuiCoos(dardos[i]);
		}

		for (Dragao d : dragoes) {
			atribuiCoos(d);
		}

		int dragonsPassed = 0;

		while (dragonsPassed != dragoes.length) {

			for (Dragao d : dragoes) {
				if (heroi.ver(d, tabuleiro, 3)) {
					dragonsPassed = 0;
					atribuiCoos(heroi);
					break;
				}else{
					dragonsPassed++;
				}
			}
		}
	}

	// atribui ï¿½s pecas coordenadas de posicoes aleatorias livres do tabuleiro
	public void atribuiCoos(Peca p) {

		// TODO verificar heroi adjacente dragao

		Random r = new Random();
		int x, y;

		while (true) {

			x = r.nextInt(tabuleiro.getTamanho() - 2) + 1;
			y = r.nextInt(tabuleiro.getTamanho() - 2) + 1;

			if (tabuleiro.getTab()[y][x] == ' ') {

				p.getPos().setX(x);
				p.getPos().setY(y);
				break;
			}
		}

		tabuleiro.setChar(p.getPos().getX(), p.getPos().getY(), p.getSigla());
	}

	// =========
	// comandos de movimento //TODO : MOVIMENTO PARA QQ OBJECTO N APENAS PARA O
	// HEROI, PASSAR OBJECTO POR PARAMETRO

	public int moveUp() { // heroi.pos(x,y) : heroi.posicao do heroi (H)

		if (heroi.getPos().getY() == 0 || heroi.getPos().getY() >= tabuleiro.getTamanho() - 1) {
			return 1;
		}

		if (tabuleiro.getTab()[heroi.getPos().getY() - 1][heroi.getPos().getX()] == 'X') {
			return 1;
		} else {
			tabuleiro.setChar(heroi.getPos().getX(), heroi.getPos().getY() - 1, heroi.getSigla());
			tabuleiro.setChar(heroi.getPos().getX(), heroi.getPos().getY(), ' ');

			heroi.getPos().setY(heroi.getPos().getY() - 1);
			return 0;
		}
	}

	public int moveDown() { // heroi.pos(x,y) : heroi.posicao do heroi (H)

		if (heroi.getPos().getY() >= tabuleiro.getTamanho() - 1 || heroi.getPos().getY() < 0) {
			return 1;
		}

		if (tabuleiro.getTab()[heroi.getPos().getY() + 1][heroi.getPos().getX()] == 'X') {
			return 1;
		} else {
			tabuleiro.setChar(heroi.getPos().getX(), heroi.getPos().getY() + 1, heroi.getSigla());
			tabuleiro.setChar(heroi.getPos().getX(), heroi.getPos().getY(), ' ');

			heroi.getPos().setY(heroi.getPos().getY() + 1);
			return 0;
		}
	}

	public int moveLeft() { // heroi.pos(x,y) : heroi.posicao do heroi (H)

		if (heroi.getPos().getX() >= tabuleiro.getTamanho() - 1 || heroi.getPos().getX() < 0) {
			return 1;
		}

		if (tabuleiro.getTab()[heroi.getPos().getY()][heroi.getPos().getX() - 1] == 'X') {
			return 1;
		} else {
			tabuleiro.setChar(heroi.getPos().getX() - 1, heroi.getPos().getY(), heroi.getSigla());
			tabuleiro.setChar(heroi.getPos().getX(), heroi.getPos().getY(), ' ');

			heroi.getPos().setX(heroi.getPos().getX() - 1);
			return 0;
		}
	}

	public int moveRight() { // heroi.pos(x,y) : heroi.posicao do heroi (H)

		if (heroi.getPos().getX() >= tabuleiro.getTamanho() - 1 || heroi.getPos().getX() < 0) {
			return 1;
		}

		if (tabuleiro.getTab()[heroi.getPos().getY()][heroi.getPos().getX() + 1] == 'X') {
			return 1;
		} else {
			tabuleiro.setChar(heroi.getPos().getX() + 1, heroi.getPos().getY(), heroi.getSigla());
			tabuleiro.setChar(heroi.getPos().getX(), heroi.getPos().getY(), ' ');

			heroi.getPos().setX(heroi.getPos().getX() + 1);
			return 0;
		}
	}

	// =================================0

	// verifica se o dragao e a espada coincidem, se sim troca a sigla para "F"
	public void checkCoincide() {

		for (Dragao d : dragoes) {
			if (espada.colide(d)) {
				coincide = true;
				tabuleiro.setChar(espada.getPos().getX(), espada.getPos().getY(), 'F');
			}
		}
	}

	public void checkCollision() {

		// TODO method colide

		if (heroi.colide(espada)) {

			tabuleiro.setChar(heroi.getPos().getX(), heroi.getPos().getY(), 'A');
			heroi.setSigla('A');
			heroi.setArmed(true);
			espada.setActive(false);
		}

		if (heroi.colide(escudo)) {
			heroi.setShield(true);
			escudo.setActive(false);
		}

		for (Dragao d : dragoes) {
			if (d.getActive()) {
				checkDragao(d);
			}
		}

		for (Peca dardo : dardos) {
			if (dardo.getActive()) {
				if (heroi.colide(dardo)) {
					heroi.addDardo();
					dardo.setActive(false);
				}
			}
		}

		// verifica se sai do labirinto
		if (heroi.getPos().getX() == tabuleiro.getExit().getX() && heroi.getPos().getY() == tabuleiro.getExit().getY()) {
			heroi.setActive(false);
		}
		checkCoincide();
	}

	public void checkDragao(Dragao dragao) {
		if (!dragao.getActive()) {
			return;
		}

		if (dragao.ver(heroi, tabuleiro, 1)) {

			if (heroi.isArmed()) { // mata dragao
				dragao.setActive(false);
				dragao.setSigla(' ');
				tabuleiro.setChar(dragao.getPos().getX(), dragao.getPos().getY(), ' ');
				return;

			} else if (!dragao.isDormir()) { // perde jogo
				heroi.setActive(false);
			}
		} else if (dragao.ver(heroi, tabuleiro, 3) && !heroi.isShield() && !dragao.isDormir()) {
			heroi.setActive(false);
		}
	}

	public void shotDardo(char comando) {

		if (comando == 'c' || heroi.getNrDardos() <= 0) {
			return;
		}

		for (Dragao d : dragoes) {
			if (heroi.ver(d, tabuleiro, tabuleiro.getTamanho())) {
				int diffX = d.getPos().getX() - heroi.getPos().getX();
				int diffY = d.getPos().getY() - heroi.getPos().getY();
				if (diffX == 0) {
					if (diffY < 0 && comando == 'w') {
						d.setActive(false);
						tabuleiro.setChar(d.getPos().getX(), d.getPos().getY(), ' ');
						return;
					} else if (diffY > 0 && comando == 's') {
						d.setActive(false);
						tabuleiro.setChar(d.getPos().getX(), d.getPos().getY(), ' ');
						return;
					}
				}
				if (diffY == 0) {
					if (diffX < 0 && comando == 'a') {
						d.setActive(false);
						tabuleiro.setChar(d.getPos().getX(), d.getPos().getY(), ' ');
						return;
					} else if (diffX > 0 && comando == 'd') {
						d.setActive(false);
						tabuleiro.setChar(d.getPos().getX(), d.getPos().getY(), ' ');
						return;
					}
				}
			}
		}
		heroi.removeNrDardos();
	}

	public void moverDragao(Dragao dragao) {

		Random r = new Random();
		int dir = r.nextInt(4); // 0-direita; 1-baixo; 2-esquerda; 3 - cima

		// int incrementX = 0, incrementY = 0;

		if (dragao.getStatus() == 'p') {
			return;
		}

		if (dir == 0 && tabuleiro.getTab()[dragao.getPos().getY()][dragao.getPos().getX() + 1] != 'X'
				&& dragao.getPos().getX() + 1 < tabuleiro.getTamanho() - 1) { // dragao

			if (coincide) {
				coincide = false;
				tabuleiro.setChar(dragao.getPos().getX(), dragao.getPos().getY(), 'E');
			} else
				tabuleiro.getTab()[dragao.getPos().getY()][dragao.getPos().getX()] = ' ';

			dragao.getPos().setX(dragao.getPos().getX() + 1);
			tabuleiro.setChar(dragao.getPos().getX(), dragao.getPos().getY(), dragao.getSigla());
		} else if (dir == 1 && tabuleiro.getTab()[dragao.getPos().getY() + 1][dragao.getPos().getX()] != 'X'
				&& dragao.getPos().getY() + 1 < tabuleiro.getTamanho() - 1) {

			if (coincide) {
				coincide = false;
				tabuleiro.setChar(dragao.getPos().getX(), dragao.getPos().getY(), 'E');
			} else
				tabuleiro.setChar(dragao.getPos().getX(), dragao.getPos().getY(), ' ');

			dragao.getPos().setY(dragao.getPos().getY() + 1);
			tabuleiro.setChar(dragao.getPos().getX(), dragao.getPos().getY(), dragao.getSigla());

		} else if (dir == 2 && tabuleiro.getTab()[dragao.getPos().getY()][dragao.getPos().getX() - 1] != 'X' && dragao.getPos().getX() - 1 > 0) {

			if (coincide) {
				coincide = false;
				tabuleiro.setChar(dragao.getPos().getX(), dragao.getPos().getY(), dragao.getSigla());
			} else
				tabuleiro.setChar(dragao.getPos().getX(), dragao.getPos().getY(), ' ');

			dragao.getPos().setX(dragao.getPos().getX() - 1);
			tabuleiro.setChar(dragao.getPos().getX(), dragao.getPos().getY(), dragao.getSigla());

		} else if (dir == 3 && tabuleiro.getTab()[dragao.getPos().getY() - 1][dragao.getPos().getX()] != 'X' && dragao.getPos().getY() - 1 > 0) {

			if (coincide) {
				coincide = false;
				tabuleiro.setChar(dragao.getPos().getX(), dragao.getPos().getY(), 'E');
			} else
				tabuleiro.setChar(dragao.getPos().getX(), dragao.getPos().getY(), ' ');

			dragao.getPos().setY(dragao.getPos().getY() - 1);
			tabuleiro.setChar(dragao.getPos().getX(), dragao.getPos().getY(), dragao.getSigla());
		}
	}

	public boolean gameOver() {
		for (Dragao d : dragoes) {
			if (d.getActive()) {
				return false;
			}
		}
		return true;
	}

}