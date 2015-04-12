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

	// private boolean coincide = false;

	private Tabuleiro tabuleiro;

	// construtor
	/**
	 * Construtor Vazio
	 */
	public Labirinto() {
	}

	/**
	 * Contrutor principal do Maze
	 * 
	 * @param quantidadeDragao
	 *            Numero de Dragoes do Maze
	 * @param tipoDragao
	 *            Diferentes tipos de dragao: P- Parado Z-Aleatorio/Dormir
	 *            A-Aleatorio
	 * @param mazeType
	 *            1-Labirinto Automatico 0- Predefinido
	 * @param mazeSize
	 *            Tamanho do Labirinto
	 */
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

	/**
	 * Getter Dardos
	 * 
	 * @return
	 */
	public Peca[] getDardos() {
		return dardos;
	}

	/**
	 * Setter Dardos
	 * 
	 * @param dardos
	 */
	public void setDardos(Peca[] dardos) {
		this.dardos = dardos;
	}

	/**
	 * Getter Heroi
	 * 
	 * @return
	 */
	public Heroi getHeroi() {
		return heroi;
	}

	/**
	 * Setter Heroi
	 * 
	 * @param heroi
	 */
	public void setHeroi(Heroi heroi) {
		this.heroi = heroi;
	}

	/**
	 * Getter Espada
	 * 
	 * @return
	 */
	public Peca getEspada() {
		return espada;
	}

	/**
	 * Setter Espada
	 * 
	 * @param espada
	 */
	public void setEspada(Peca espada) {
		this.espada = espada;
	}

	/**
	 * Getter Dragoes
	 * 
	 * @return
	 */
	public Dragao[] getDragoes() {
		return dragoes;
	}

	/**
	 * Setter Dragoes
	 * 
	 * @param dragoes
	 */
	public void setDragoes(Dragao[] dragoes) {
		this.dragoes = dragoes;
	}

	/**
	 * Getter Tabuleiro
	 * 
	 * @return
	 */
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	/**
	 * Setter Tabuleiro
	 * 
	 * @param tabuleiro
	 */
	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	/**
	 * Getter Escudo
	 * 
	 * @return
	 */
	public Peca getEscudo() {
		return escudo;
	}

	/**
	 * Setter Escudo
	 * 
	 * @param escudo
	 */
	public void setEscudo(Peca escudo) {
		this.escudo = escudo;
	}

	// ==========================================================
	// MAIN
	/**
	 * Inicia o Labirinto em modo Consola
	 * 
	 * @param args
	 */
	public static void main(String[] args) { // TODO: PODE ACONTECER O HEROI
		// FAZER SPAWN ADJACENTE AO
		// DRAGAO
		new Cli();
	}

	// ==========================================================
	/**
	 * Actualizaçao dasposições dos diferentes dragoes e possiveis colisoes
	 */
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

	// repinta tabuleiro deixando apenas os X's tudo o resto fica ' '
	// funcao usada na refreshTab

	/**
	 * Repinta o tabuleiro sem os elementos
	 */
	public void repaintBlanks() {

		for (int i = 0; i < tabuleiro.getTamanho(); i++) {
			for (int j = 0; j < tabuleiro.getTamanho(); j++) {

				if (tabuleiro.getTab()[i][j] != 'X') {
					tabuleiro.getTab()[i][j] = ' ';
				}
			}
		}
		tabuleiro.getTab()[tabuleiro.getExit().getY()][tabuleiro.getExit().getX()] = 'S';
	}

	// redesenha elementos no caso de estes terem estado sobrepostos
	/**
	 * Redesenha o Tabuleiro e coloca os elementos
	 */
	public void refreshTabuleiro() {

		this.repaintBlanks();

		tabuleiro.setChar(heroi.getPos().getX(), heroi.getPos().getY(), heroi.getSigla());

		if (tabuleiro.getTab()[escudo.getPos().getY()][escudo.getPos().getX()] == ' ' && escudo.getActive()) {

			tabuleiro.setChar(escudo.getPos().getX(), escudo.getPos().getY(), escudo.getSigla());
		}

		if (tabuleiro.getTab()[espada.getPos().getY()][espada.getPos().getX()] == ' ' && espada.getActive()) {

			tabuleiro.setChar(espada.getPos().getX(), espada.getPos().getY(), espada.getSigla());
		}

		for (Peca d : dardos) {

			if (tabuleiro.getTab()[d.getPos().getY()][d.getPos().getX()] == ' ' && d.getActive()) {

				tabuleiro.setChar(d.getPos().getX(), d.getPos().getY(), d.getSigla());
			}
		}

		for (Dragao d : dragoes) {
			if (d.getActive())
				tabuleiro.setChar(d.getPos().getX(), d.getPos().getY(), d.getSigla());
		}
	}

	/**
	 * Inicializa as pecas do tabuleiro
	 */
	public void initTabuleiro() {

		// boolean posicaoHeroiOK = false;

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
				} else {
					dragonsPassed++;
				}
			}
		}
		refreshTabuleiro();
	}

	/**
	 * Atribui coordenadas a uma peca
	 * 
	 * @param p
	 *            Peca a serem atribuidas as coordenadas
	 */
	public void atribuiCoos(Peca p) {

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

	}

	// =========
	/**
	 * Move o heroi para cima
	 * 
	 * @return 1 - Não conseguiu mover 0 - Moveu
	 */
	public int moveUp() { // heroi.pos(x,y) : heroi.posicao do heroi (H)

		if (heroi.getPos().getY() == 0 || heroi.getPos().getY() >= tabuleiro.getTamanho() - 1) {
			return 1;
		}

		if (tabuleiro.getTab()[heroi.getPos().getY() - 1][heroi.getPos().getX()] == 'X') {
			return 1;
		} else {
			heroi.getPos().setY(heroi.getPos().getY() - 1);
			return 0;
		}
	}

	/**
	 * * Move o heroi para baixo
	 * 
	 * @return 1 - Não conseguiu mover 0 - Moveu
	 */
	public int moveDown() { // heroi.pos(x,y) : heroi.posicao do heroi (H)

		if (heroi.getPos().getY() >= tabuleiro.getTamanho() - 1 || heroi.getPos().getY() < 0) {
			return 1;
		}

		if (tabuleiro.getTab()[heroi.getPos().getY() + 1][heroi.getPos().getX()] == 'X') {
			return 1;
		} else {
			// tabuleiro.setChar(heroi.getPos().getX(), heroi.getPos().getY() +
			// 1, heroi.getSigla());
			// tabuleiro.setChar(heroi.getPos().getX(), heroi.getPos().getY(),
			// ' ');

			heroi.getPos().setY(heroi.getPos().getY() + 1);
			return 0;
		}
	}

	/**
	 * * Move o heroi para esquerda
	 * 
	 * @return 1 - Não conseguiu mover 0 - Moveu
	 */
	public int moveLeft() { // heroi.pos(x,y) : heroi.posicao do heroi (H)

		if (heroi.getPos().getX() >= tabuleiro.getTamanho() - 1 || heroi.getPos().getX() < 0) {
			return 1;
		}

		if (tabuleiro.getTab()[heroi.getPos().getY()][heroi.getPos().getX() - 1] == 'X') {
			return 1;
		} else {
			// tabuleiro.setChar(heroi.getPos().getX() - 1,
			// heroi.getPos().getY(), heroi.getSigla());
			// tabuleiro.setChar(heroi.getPos().getX(), heroi.getPos().getY(),
			// ' ');

			heroi.getPos().setX(heroi.getPos().getX() - 1);
			return 0;
		}
	}

	/**
	 * * Move o heroi para Esquerda
	 * 
	 * @return 1 - Não conseguiu mover 0 - Moveu
	 */
	public int moveRight() { // heroi.pos(x,y) : heroi.posicao do heroi (H)

		if (heroi.getPos().getX() >= tabuleiro.getTamanho() - 1 || heroi.getPos().getX() < 0) {
			return 1;
		}

		if (tabuleiro.getTab()[heroi.getPos().getY()][heroi.getPos().getX() + 1] == 'X') {
			return 1;
		} else {
			heroi.getPos().setX(heroi.getPos().getX() + 1);
			return 0;
		}
	}

	/**
	 * Verifica colisoes entre os diferentes elementos do tabuleiro
	 */
	public void checkCollision() {

		if (heroi.colide(espada) && espada.getActive()) {
			espada.setActive(false);
			heroi.setSigla('A');
			heroi.setArmed(true);
			espada.setActive(false);
		}

		if (heroi.colide(escudo) && escudo.getActive()) {
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
	}

	/**
	 * Analisa interacoes entre o dragao e o heroi
	 * 
	 * @param dragao
	 *            dragao a analisar
	 */
	public void checkDragao(Dragao dragao) {
		if (!dragao.getActive()) {
			return;
		}

		if (dragao.ver(heroi, tabuleiro, 1)) {

			if (heroi.isArmed()) { // mata dragao
				dragao.setActive(false);
				dragao.setSigla(' ');
				return;

			} else if (!dragao.isDormir()) { // perde jogo
				heroi.setActive(false);
			}
		} else if (dragao.ver(heroi, tabuleiro, 3) && !heroi.isShield() && !dragao.isDormir()) {
			heroi.setActive(false);
		}
	}

	/**
	 * Dispara um dardo numa direcao
	 * 
	 * @param comando
	 *            direcao w-cima/s-baixo/a-esquerda/d-direita
	 */

	public void shotDardo(char comando) {

		if (comando == 'c' || heroi.getNrDardos() <= 0) {
			return;
		}

		heroi.removeNrDardos();

		Dragao temp = null;

		for (Dragao d : dragoes) {

			if (!d.getActive())
				continue;

			if (heroi.ver(d, tabuleiro, tabuleiro.getTamanho())) {

				if (temp == null) {
					temp = d;
					// continue;
				}

				int diffX = d.getPos().getX() - heroi.getPos().getX();
				int diffY = d.getPos().getY() - heroi.getPos().getY();

				if (diffX == 0) {
					if (diffY < 0 && comando == 'w') {
						if (d.getPos().getY() > temp.getPos().getY()) {
							temp = d;
						}
					} else if (diffY > 0 && comando == 's') {
						if (d.getPos().getY() < temp.getPos().getY()) {
							temp = d;
						}
					}
				}

				if (diffY == 0) {
					if (diffX < 0 && comando == 'a') {
						if (d.getPos().getX() > temp.getPos().getX()) {
							temp = d;
						}
					} else if (diffX > 0 && comando == 'd') {
						if (d.getPos().getX() < temp.getPos().getX()) {
							temp = d;
						}
					}
				}
			}
		}

		if (temp != null) {
			System.out.println(temp.getPos().getX() + "  " + temp.getPos().getY());
			temp.setActive(false);
		}
	}

	/**
	 * Mover dragao em direcao aleatoria
	 * 
	 * @param dragao
	 *            Dragao a ser movido
	 */
	public void moverDragao(Dragao dragao) {

		Random r = new Random();
		int dir = r.nextInt(4); // 0-direita; 1-baixo; 2-esquerda; 3 - cima

		// int incrementX = 0, incrementY = 0;

		if (dragao.getStatus() == 'p') {
			return;
		}

		if (dir == 0 && tabuleiro.getTab()[dragao.getPos().getY()][dragao.getPos().getX() + 1] != 'X'
				&& tabuleiro.getTab()[dragao.getPos().getY()][dragao.getPos().getX() + 1] != 'D'
				&& tabuleiro.getTab()[dragao.getPos().getY()][dragao.getPos().getX() + 1] != 'S'
				&& tabuleiro.getTab()[dragao.getPos().getY()][dragao.getPos().getX() + 1] != 'Z'
				&& dragao.getPos().getX() + 1 < tabuleiro.getTamanho() - 1) { // dragao

			dragao.getPos().setX(dragao.getPos().getX() + 1);

		} else if (dir == 1 && tabuleiro.getTab()[dragao.getPos().getY() + 1][dragao.getPos().getX()] != 'X'
				&& tabuleiro.getTab()[dragao.getPos().getY() + 1][dragao.getPos().getX()] != 'D'
				&& tabuleiro.getTab()[dragao.getPos().getY() + 1][dragao.getPos().getX()] != 'S'
				&& tabuleiro.getTab()[dragao.getPos().getY() + 1][dragao.getPos().getX()] != 'Z'
				&& dragao.getPos().getY() + 1 < tabuleiro.getTamanho() - 1) {

			dragao.getPos().setY(dragao.getPos().getY() + 1);

		} else if (dir == 2 && tabuleiro.getTab()[dragao.getPos().getY()][dragao.getPos().getX() - 1] != 'X'
				&& tabuleiro.getTab()[dragao.getPos().getY()][dragao.getPos().getX() - 1] != 'D'
				&& tabuleiro.getTab()[dragao.getPos().getY()][dragao.getPos().getX() - 1] != 'S'
				&& tabuleiro.getTab()[dragao.getPos().getY()][dragao.getPos().getX() - 1] != 'Z' && dragao.getPos().getX() - 1 > 0) {

			dragao.getPos().setX(dragao.getPos().getX() - 1);

		} else if (dir == 3 && tabuleiro.getTab()[dragao.getPos().getY() - 1][dragao.getPos().getX()] != 'X'
				&& tabuleiro.getTab()[dragao.getPos().getY() - 1][dragao.getPos().getX()] != 'D'
				&& tabuleiro.getTab()[dragao.getPos().getY() - 1][dragao.getPos().getX()] != 'S'
				&& tabuleiro.getTab()[dragao.getPos().getY() - 1][dragao.getPos().getX()] != 'Z' && dragao.getPos().getY() - 1 > 0) {

			dragao.getPos().setY(dragao.getPos().getY() - 1);

		}
	}

	/**
	 * Verifica se o jogo acabou
	 * 
	 * @return false -Não Acabou true-Acabou
	 */
	public boolean gameOver() {
		for (Dragao d : dragoes) {
			if (d.getActive()) {
				return false;
			}
		}
		return true;
	}

}