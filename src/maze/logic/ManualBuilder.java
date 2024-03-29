package maze.logic;
/**
 * Classe responsavel pela cria��o de do Labirinto Predefinido
 * @author Diogo Moura
 *
 */
public class ManualBuilder implements IMazeBuilder {

	private final int TAMANHO = 10;

	/**
	 * Construtor Vazio
	 */
	public ManualBuilder() {
	}

	@Override
	public Tabuleiro build() {
		Tabuleiro tab = new Tabuleiro(TAMANHO);

		char[][] tabuleiro_layout = {
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'S' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

		tab.setTabuleiro(tabuleiro_layout);
		
		tab.setExit(new Pos(9,5));

		return tab;
	}

}
