package maze.logic;

import java.io.Serializable;

/**
 * Subclasse de Peca, representa um objecto do tipo Heroi
 * @author S�rgio Domingues
 *
 */
@SuppressWarnings("serial")
public class Heroi extends Peca implements Serializable{

	private int nrDardos = 0;
	private boolean armed = false;
	private boolean shield = false;

	/** Verifica se tem shield
	 * 
	 * @return true se tiver / false caso contrario
	 */
	public boolean isShield() {
		return shield;
	}

	/** Setter do shield
	 * 
	 * @param shield
	 */
	public void setShield(boolean shield) {
		this.shield = shield;
	}

	/**
	 * Construtor do heroi
	 * @param x
	 * @param y
	 */
	public Heroi(int x, int y) {
		super(x, y, 'H');
	}

	/**
	 * Verifica se o heroi possui uma espada
	 * @return true se tiver espada / false caso contrario
	 */
	public boolean isArmed() {
		return armed;
	}

	/**
	 * Setter da flag armed
	 * @param armed
	 */
	public void setArmed(boolean armed) {
		this.armed = armed;
	}

	/**
	 * Incrementa o n�mero de dardos do her�i
	 */
	public void addDardo() {
		nrDardos++;
	}
	/**Getter do numero de dardos
	 * 
	 * @return numero de dardos
	 */
	public int getNrDardos() {
		return nrDardos;
	}
	/** 
	 * Decrementa o numero de dardos 	
	 */
	public void removeNrDardos() {
		nrDardos--;
	}

}