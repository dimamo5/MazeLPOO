package maze.cli;

import maze.logic.Tabuleiro;

public class CliTesting implements ICli {
	
	private char drag_tipoMovimento = 'p';  //por defeito
	private int drag_quantidade = 1; 
	
	public CliTesting() {
		
		drag_quantidade = 1;
		drag_tipoMovimento = 'p';
	}	

	public void setDragMovimento(char move){
		drag_tipoMovimento = move;
	}
	
	public void setDragQtd(int qtd){
		drag_quantidade = qtd;
	}
	
	//=======
	@Override
	public int CliMazeChoose() {
		return 0; 		//labirinto predefinido
	}

	@Override
	public int autoMazeSize() {  //nao utilizado
		return 0;
	}

	@Override
	public void imprimeTabuleiro(Tabuleiro tab) {

		for (int i = 0; i < tab.getTamanho(); i++) {

			for (int j = 0; j < tab.getTamanho(); j++) {
				System.out.print(tab.getTab()[i][j] + " ");
			}

			System.out.print("\n");
		}
	}

	@Override
	public char heroiComando() {
		
		return '0';
	}

	@Override
	public char dragaoTipo() {		
		return drag_tipoMovimento;
	}

	@Override
	public int dragaoQuantidade() {		
		return drag_quantidade;
	}

}
