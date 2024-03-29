package maze.logic;

import java.io.Serializable;

/**
 * Classe auxiliar para facilitar a gestao das posicoes dos diferentes objectos do Labirinto
 * @author Utilizador
 *
 */
@SuppressWarnings("serial")
public class Pos implements Serializable {
	private int x, y;

	/**
	 * Define uma coordenada cartesiana
	 * 
	 * @param x
	 *            Coordenada Horizontal
	 * @param y
	 *            Coordenada Vertical
	 */
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Getter Coordenada Horizontal
	 * 
	 * @return Coordenada Horizontal
	 */
	public int getX() {
		return x;
	}

	/**
	 * Setter Coordenada Horizontal
	 * 
	 * @param x
	 *            Coordenada Horizontal
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Getter Coordenada Vertical
	 * 
	 * @return Coordenada Vertical
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setter Coordenada Vertical
	 * 
	 * @param y
	 *            Coordenada Vertical
	 */
	public void setY(int y) {
		this.y = y;
	}

}
