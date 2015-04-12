package maze.logic;

import java.util.Random;

@SuppressWarnings("serial")
public class Dragao extends Peca {

	private final static int PROBDORMIR = 7; // Probabilidade de adormecer x10

	private int turnsSleeping = 0;
	boolean dormir = false;

	private final char status; // Dragao: P-Parado A- Aleatorio
								// Z-Aleatorio/dormir

	/**
	 * 
	 * @param x
	 * @param y
	 * @param status
	 */
	public Dragao(int x, int y, char status) {
		super(x, y, 'D');
		this.status = status;
	}

	public int getTurnsSleeping() {
		return turnsSleeping;
	}

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

	public boolean isDormir() {
		return dormir;
	}

	public void setDormir(boolean dormir) {
		this.dormir = dormir;
	}

	public char getStatus() {
		return status;
	}

	public void setTurnsSleeping(int i) {
		this.turnsSleeping = i;
	}

}
