package maze.cli;

import maze.logic.Tabuleiro;

public interface ICli {
	
	//TODO adicionar funcs da interface
	
	public int CliMazeChoose();
	
	public int autoMazeSize();
	
	public void imprimeTabuleiro(Tabuleiro tab);
	
	public char heroiComando();
	
	public char dragaoTipo();
	
	public int dragaoQuantidade();	
	
}
