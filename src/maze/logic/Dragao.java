package maze.logic;

import java.util.Random;
/**
 * Subclasse de Peca, representa um objeto do tipo Dragao
 * 
 * @author Sérgio Domingues
 *
 */
@SuppressWarnings("serial")
public class Dragao extends Peca {

	private final static int PROBDORMIR = 7; // Probabilidade de adormecer x10

	private int turnsSleeping = 0;
	boolean dormir = false;

	private final char status; // Dragao: P-Parado A- Aleatorio
								// Z-Aleatorio/dormir

	/**
	 * Construtor do Dragao
	 * @param x
	 * @param y
	 * @param status
	 */
	public Dragao(int x, int y, char status) {
		super(x, y, 'D');
		this.status = status;
	}

	/** Getter turnsSleeping
	 * 
	 * @return turnsSleeping
	 */
	public int getTurnsSleeping() {
		return turnsSleeping;
	}

	/**
	 * Se dragao está a dormir decrementa contador;
	 * Caso contrario coloca-o ou nao a dormir de acordo com um factor probabilistico 	 
	 */
	public void updateSleeping() {
		if (status != 'z') {
			return;
		}
		if (dormir) {
			turnsSleeping--;

			if (turnsSleeping == 0) {
				dormir = false;
				this.setSigla('D');
				return;
			}
		} else {
			Random r = new Random();
			int turns = r.nextInt(10) + 1;

			if (turns > PROBDORMIR) { // Probabilidade de não adormecer de 60%
				turnsSleeping = turns - PROBDORMIR; // Turnos a dormir entre 1 a
													// 3
				dormir = true;
				this.setSigla('Z');
			}
		}
	}

	/** Getter flag dormir
	 * 
	 * @return dormir (flag)
	 */
	public boolean isDormir() {
		return dormir;
	}

	/** Setter flag dormir
	 * 
	 * @param dormir
	 */
	public void setDormir(boolean dormir) {
		this.dormir = dormir;
	}

	/** Getter status flag 
	 * 
	 * @return status 
	 */
	public char getStatus() {
		return status;
	}

	/** Setter do turnsSleeping
	 * 
	 * @param i
	 */
	public void setTurnsSleeping(int i) {
		this.turnsSleeping = i;
	}

}
