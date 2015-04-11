package maze.logic;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Peca implements Serializable{

	private Pos pos;
	private char sigla;
	private Boolean active = true;

	public Peca(int x, int y, char sigla) {
		pos = new Pos(x, y);
		this.sigla = sigla;
	}

	public Pos getPos() {
		return pos;
	}

	public void move(int x, int y) {
		this.pos.setX(x);
		this.pos.setY(y);
	}

	public char getSigla() {
		return sigla;
	}

	public void setSigla(char sigla) {
		this.sigla = sigla;
	}

	public boolean getActive() {
		return active;
	}

	public boolean colide(Peca p) {
		if (this.getPos().getX() == p.getPos().getX() && this.getPos().getY() == p.getPos().getY()) {
			return true;
		} else
			return false;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

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

		} else if (p.getPos().getX() == this.getPos().getX()) { // Percorre Verticalmente

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
