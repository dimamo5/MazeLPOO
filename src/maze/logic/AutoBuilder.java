package maze.logic;

import java.util.Random;
import java.util.Stack;

/**
 * Classe que constroi um tabuleiro aleatorio implementa interface IMazeBuilder e metodos associados
 * @author Diogo Moura
 *
 */
public class AutoBuilder implements IMazeBuilder {

	int tamanho = 11; // tamanho labirinto predefinido

	/**
	 * Construtor do Tabuleiro do Labirinto Aleatorio com m tabmanho especifico
	 * 
	 * @param n
	 *            tamanho do labirinto a ser criado
	 */
	public AutoBuilder(int n) {
		tamanho = n;
	}

	@Override
	public Tabuleiro build() {
		Tabuleiro tab = new Tabuleiro(tamanho);
		mazeGenerator(tab);

		return tab;
	}

	/**
	 * Criador do Tabuleiro
	 * @param tabuleiro Tabuleiro a ser alterado
	 */
	public void mazeGenerator(Tabuleiro tabuleiro) {

		int n = tabuleiro.getTamanho();

		char[][] tab = new char[n][n];

		// preenchimento do tabuleiro com X's e ' ' (espacos livres)
		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {

				if (i % 2 != 0 && j % 2 != 0) {
					tab[i][j] = ' ';
				} else {
					tab[i][j] = 'X';
				}
			}
		}

		// criação da guide cell
		Random r = new Random();

		int visitedCellsDim;
		Pos gcell = new Pos(0, 0);

		visitedCellsDim = (n - 1) / 2;

		gcell.setY(r.nextInt(visitedCellsDim)); // obter a linha (considerando
												// se
												// matriz de free cells)

		if (gcell.getY() == 0 || gcell.getY() == visitedCellsDim - 1) { // 2a
																		// linha
																		// ou
			// penultima
			// linha-> guide
			// cell = qq
			// cell livre
			// dessa linha

			gcell.setY(2 * gcell.getY() + 1); // logica: 2*n + 1 (obter as coos
			// correctas em relacao ao lab)
			gcell.setX(2 * r.nextInt(visitedCellsDim) + 1);

		} else // restantes linhas, guide cell = free cell de um dos extremos
		{
			gcell.setX(r.nextInt(2));
			gcell.setY(2 * gcell.getY() + 1);

			if (gcell.getX() == 1) {

				gcell.setX(n - 2);
			} else
				gcell.setX(1);
		}

		tab[gcell.getY()][gcell.getX()] = '+';

		// criacao da saida, adjacente à guide cell anteriormente criada

		int dir = 0;

		Pos exit = new Pos(0, 0);

		// dir = 0 -> esquerda, 1->baixo, 2->direita, 3->cima !!!!!!!!

		// logica: verificar 2a e penultima linhas, as restantes apenas têm 2
		// casos possiveis = extremos do lab

		if (gcell.getY() == 1) {

			if (gcell.getX() == 1) { // a saida pode ser à esquerda ou acima ->
										// 2
				// casos possiveis

				dir = r.nextInt(2); // (0 ou 1) * 3 para dar o indice correcto
									// da direccao
				dir *= 3;
			} else if (gcell.getX() == n - 2) { // 2 casos posiveis : direita ,
												// cima

				dir = r.nextInt(2) + 2; // rand : 2 ou 3
			} else { // 1 caso possivel : cima
				dir = 3;
			}
		} else if (gcell.getY() == n - 2) {

			if (gcell.getX() == 1) { // a saida pode ser à esquerda ou baixo ->
										// 2
				// casos possiveis

				dir = r.nextInt(2);
			} else if (gcell.getX() == n - 2) { // 2 casos posiveis : baixo ,
												// direita

				dir = r.nextInt(2) + 1; // random : 1 ou 2
			} else { // 1 caso possivel : baixo
				dir = 1;
			}
		} else { // casos possiveis : extremos do lab

			if (gcell.getX() == 1) {

				dir = 0;
			} else {
				dir = 2;
			}
		}

		// conversao da direccao em coordenadas
		switch (dir) {

		case 0: {
			exit.setX(gcell.getX() - 1);
			exit.setY(gcell.getY());
			break;
		}
		case 1: {
			exit.setX(gcell.getX());
			exit.setY(gcell.getY() + 1);
			break;
		}
		case 2: {
			exit.setX(gcell.getX() + 1);
			exit.setY(gcell.getY());
			break;
		}
		case 3: {
			exit.setX(gcell.getX());
			exit.setY(gcell.getY() - 1);
			break;
		}
		default:
			break;
		}

		tabuleiro.setExit(exit);

		tab[exit.getY()][exit.getX()] = 'S';

		Stack<Pos> pathHistory = new Stack<Pos>();

		char[][] visitedCells = new char[visitedCellsDim][visitedCellsDim];

		for (int i = 0; i < visitedCellsDim; i++) {

			for (int j = 0; j < visitedCellsDim; j++) {

				visitedCells[i][j] = '.';
			}
		}

		gcell.setX((gcell.getX() - 1) / 2);
		gcell.setY((gcell.getY() - 1) / 2);

		visitedCells[gcell.getY()][gcell.getX()] = '+';

		pathHistory.push(gcell);

		boolean doneMaze = false;

		while (!doneMaze) {
			if (neighborFree(gcell, visitedCells, visitedCellsDim)) {
				dir = r.nextInt(4);
				Pos temp;
				if (dir == 0 && gcell.getX() - 1 >= 0) {
					if (visitedCells[gcell.getY()][gcell.getX() - 1] == '.') {
						tab[2 * gcell.getY() + 1][2 * gcell.getX() + 1] = ' ';
						tab[2 * gcell.getY() + 1][2 * gcell.getX()] = ' ';
						gcell.setX(gcell.getX() - 1);
						temp = new Pos(gcell.getX(), gcell.getY());
						pathHistory.push(temp);
					}
				} else if (dir == 1 && gcell.getY() + 1 < visitedCellsDim) {
					if (visitedCells[gcell.getY() + 1][gcell.getX()] == '.') {
						tab[2 * gcell.getY() + 1][2 * gcell.getX() + 1] = ' ';
						tab[2 * gcell.getY() + 2][2 * gcell.getX() + 1] = ' ';
						gcell.setY(gcell.getY() + 1);
						temp = new Pos(gcell.getX(), gcell.getY());
						pathHistory.push(temp);
					}
				} else if (dir == 2 && gcell.getX() + 1 < visitedCellsDim) {
					if (visitedCells[gcell.getY()][gcell.getX() + 1] == '.') {
						tab[2 * gcell.getY() + 1][2 * gcell.getX() + 1] = ' ';
						tab[2 * gcell.getY() + 1][2 * gcell.getX() + 2] = ' ';
						gcell.setX(gcell.getX() + 1);
						temp = new Pos(gcell.getX(), gcell.getY());
						pathHistory.push(temp);
					}
				} else if (dir == 3 && gcell.getY() - 1 >= 0) {
					if (visitedCells[gcell.getY() - 1][gcell.getX()] == '.') {
						tab[2 * gcell.getY() + 1][2 * gcell.getX() + 1] = ' ';
						tab[2 * gcell.getY()][2 * gcell.getX() + 1] = ' ';
						gcell.setY(gcell.getY() - 1);
						temp = new Pos(gcell.getX(), gcell.getY());
						pathHistory.push(temp);
					}
				}
				visitedCells[gcell.getY()][gcell.getX()] = '+';

			} else {
				if (pathHistory.empty()) {
					doneMaze = true;
				} else {
					gcell = pathHistory.pop();
				}
			}

		}
		tabuleiro.setTabuleiro(tab);
	}

	public boolean neighborFree(Pos gcell, char[][] visitedCell, int size) {
		if ((gcell.getX() + 1) < size) {
			if (visitedCell[gcell.getY()][gcell.getX() + 1] == '.') {
				return true;
			}
		}
		if ((gcell.getX() - 1) >= 0) {
			if (visitedCell[gcell.getY()][gcell.getX() - 1] == '.') {
				return true;
			}
		}
		if ((gcell.getY() + 1) < size) {
			if (visitedCell[gcell.getY() + 1][gcell.getX()] == '.') {
				return true;
			}
		}
		if ((gcell.getY() - 1) >= 0) {
			if (visitedCell[gcell.getY() - 1][gcell.getX()] == '.') {
				return true;
			}
		}
		return false;
	}
}