package maze.logic;

import java.io.Serializable;

/**
 * Classe Mae de onde derivam as outras todas classes com objectos especificos
 * @author Diogo Moura
 *
 */
@SuppressWarnings("serial")
public class Peca implements Serializable {

	private Pos pos;
	private char sigla;
	private Boolean active = true;

	/**
	 * Cria uma Peca
	 * 
	 * @param x
	 *            Coordenada Horizontal
	 * @param y
	 *            Coordenada Vertical
	 * @param sigla
	 *            Silga da Peca
	 */
	public Peca(int x, int y, char sigla) {
		pos = new Pos(x, y);
		this.sigla = sigla;
	}

	/**
	 * Getter da Posicao da Peca
	 * 
	 * @return Pos da Peca
	 */
	public Pos getPos() {
		return pos;
	}

	/**
	 * Setter da Posicao da Peca
	 * 
	 * @param p
	 */
	public void setPos(Pos p) {
		pos = p;
	}

	/**
	 * Move uma Peca para as coordenadas
	 * 
	 * @param x
	 *            Nova Coordenada Horizontal
	 * @param y
	 *            Nova Coordenada vertical
	 */
	public void move(int x, int y) {
		this.pos.setX(x);
		this.pos.setY(y);
	}

	/**
	 * Getter Sigla da Peca
	 * 
	 * @return Sigla da Peca
	 */
	public char getSigla() {
		return sigla;
	}

	/**
	 * Setter da Sigla da Peca
	 * 
	 * @param sigla
	 *            Nova Sigla
	 */
	public void setSigla(char sigla) {
		this.sigla = sigla;
	}

	/**
	 * Getter Peca Activa
	 * 
	 * @return True- Activa False-Inativa
	 */
	public boolean getActive() {
		return active;
	}

	/**
	 * Verifica se um Peca esta na mesma posicao de outra
	 * 
	 * @param p
	 *            Peca a comparar
	 * @return True-Colide False- Não colide
	 */
	public boolean colide(Peca p) {
		if (this.getPos().getX() == p.getPos().getX() && this.getPos().getY() == p.getPos().getY()) {
			return true;
		} else
			return false;
	}

	/**
	 * Setter Active
	 * 
	 * @param active
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * Verifica se um peca tem visao de outra com um determinado alcance
	 * 
	 * @param p
	 *            Peca a ser vista
	 * @param tab
	 *            Tabuleiro de jogo
	 * @param alcance
	 *            Distancia Maxima que pode ser avistada
	 * @return True - Consegue ver False -Não consegue ver
	 */
	public boolean ver(Peca p, Tabuleiro tab, int alcance) {

		int pos;

		if (p.getPos().getY() == this.getPos().getY()) { // Percorre Horizontal
			// se alvo estiver mais perto que o alcance
			if (Math.abs(this.getPos().getX() - p.getPos().getX()) < alcance) {
				alcance = Math.abs(this.getPos().getX() - p.getPos().getX());
			}

			if (this.getPos().getX() < p.getPos().getX()) {
				pos = +1; // frente
			} else {
				pos = -1; // tras
			}

			for (int i = 1; Math.abs(i) <= alcance; i++) {
				if (tab.getTab()[this.getPos().getY()][this.getPos().getX() + (i * pos)] == 'X') {
					return false;
				} else if (p.getPos().getX() == (this.getPos().getX() + (pos * i))) {
					return true;
				}
			}

		} else if (p.getPos().getX() == this.getPos().getX()) { // Percorre
																// Verticalmente

			if (Math.abs(this.getPos().getY() - p.getPos().getY()) < alcance) {
				alcance = Math.abs(this.getPos().getY() - p.getPos().getY());
			}

			if (this.getPos().getY() < p.getPos().getY()) {
				pos = +1; // baixo
			} else {
				pos = -1; // cima
			}

			for (int i = 1; Math.abs(i) <= alcance; i++) {
				if (tab.getTab()[this.getPos().getY() + (i * pos)][this.getPos().getX()] == 'X') {
					return false;
				} else if (p.getPos().getY() == (this.getPos().getY() + (i * pos))) {
					return true;
				}
			}
		}

		return false;
	}
}
