package maze.logic;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Heroi extends Peca implements Serializable{

	private int nrDardos = 0;
	private boolean armed = false;
	private boolean shield = false;

	public boolean isShield() {
		return shield;
	}

	public void setShield(boolean shield) {
		this.shield = shield;
	}

	public Heroi(int x, int y) {
		super(x, y, 'H');
	}

	public boolean isArmed() {
		return armed;
	}

	public void setArmed(boolean armed) {
		this.armed = armed;
	}

	public void addDardo() {
		nrDardos++;
	}

	public int getNrDardos() {
		return nrDardos;
	}

	public void removeNrDardos() {
		nrDardos--;
	}

}