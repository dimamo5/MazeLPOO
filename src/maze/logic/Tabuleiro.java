package maze.logic;

public class Tabuleiro {
	
	private char[][] tab;
	Pos exit;
	private int tamanho;

	public Tabuleiro(int n) {
		tamanho = n;
		// TODO BUILDER
	}
	
	public int getTamanho(){
		return tamanho;
	}
	
	public void setTamanho(int n){
		tamanho = n;
	}

	public char[][] getTab() {
		return tab;
	}

	public void setChar(int x, int y, char c) {
		tab[y][x] = c;
	}

	public void setTabuleiro(char[][] tab) {
		this.tab = tab;
	}

	public void setExit(Pos exit) {
		this.exit = exit;
	}

	public Pos getExit() {
		return exit;
	}

}
