package maze.logic;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Tabuleiro implements Serializable {

	private char[][] tab;
	Pos exit;
	private int tamanho;

	/**
	 * Construtor Tabuleiro
	 * 
	 * @param n
	 *            Tamanho Tabuleiro
	 */
	public Tabuleiro(int n) {
		tamanho = n;
	}

	/**
	 * Getter Tamanho Tabuleiro
	 * 
	 * @return
	 */
	public int getTamanho() {
		return tamanho;
	}

	/**
	 * Setter Tamanho Tabuleiro
	 * 
	 * @param n
	 *            Tamanho Tabuleiro
	 */
	public void setTamanho(int n) {
		tamanho = n;
	}

	/**
	 * Getter Tabuleiro de Jogo
	 * 
	 * @return Tabuleiro de Jogo
	 */
	public char[][] getTab() {
		return tab;
	}

	/**
	 * Troca a sigla do Tabuleiro numa especifica coordenada
	 * 
	 * @param x
	 *            Coordenada Horizontal
	 * @param y
	 *            Coordenada Vertical
	 * @param c
	 *            Letr para ser modificada
	 */
	public void setChar(int x, int y, char c) {
		tab[y][x] = c;
	}

	/**
	 * Setter Tabuleiro de Jogo
	 * 
	 * @param tab
	 *            Tabuleiro
	 */
	public void setTabuleiro(char[][] tab) {
		this.tab = tab;
	}

	/**
	 * Setter da Posicao de Saida
	 * 
	 * @param exit
	 */
	public void setExit(Pos exit) {
		this.exit = exit;
	}

	/**
	 * Getter Posicao de Saida
	 * 
	 * @return
	 */
	public Pos getExit() {
		return exit;
	}

}
