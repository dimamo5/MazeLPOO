package maze.cli;

import java.util.Scanner;

import maze.logic.*;

public class Cli {

	private Scanner scan = new Scanner(System.in);

	private Labirinto labirinto;

	public Cli() {
		labirinto = new Labirinto(dragaoQuantidade(), dragaoTipo(), CliMazeChoose(), autoMazeSize());
		playGame();
	}

	public void playGame() {

		char comando;

		imprimeTabuleiro(labirinto.getTabuleiro());

		while (labirinto.getHeroi().getActive()) {

			recebeComandoMove();

			labirinto.updateDragons();

			imprimeTabuleiro(labirinto.getTabuleiro());
			imprimeEquips(labirinto.getHeroi());

		}
		// rotina de game over
		imprimeTabuleiro(labirinto.getTabuleiro());

		if (labirinto.gameOver()) {
			endGame(1);
		} else {
			endGame(0);
		}
	}

	public int CliMazeChoose() {

		System.out.println("1-Labirinto Predefinido");

		System.out.println("2-Labirinto Automatico");

		System.out.println("Opção:");

		while (true) {
			int opcao = scan.nextInt();
			if (opcao == 1) {
				return 0;
			}

			else if (opcao == 2) {
				return 1;
			}
		}
	}

	public int autoMazeSize() {
		int size;

		while (true) {
			System.out.print("Introduza a dimensao do labirinto(impar,>6):");

			size = scan.nextInt();

			if (size % 2 != 0 && size > 0) {
				break;
			}

			System.out.print("A dimensao do labirinto tem de ser impar & >6\n");
		}

		if (size % 2 == 0) {
			size--;
		}

		return size;
	}

	public void imprimeTabuleiro(Tabuleiro tab) {

		for (int i = 0; i < tab.getTamanho(); i++) {

			for (int j = 0; j < tab.getTamanho(); j++) {
				System.out.print(tab.getTab()[i][j] + " ");
			}

			System.out.print("\n");
		}

	}

	public char dragaoTipo() {
		char tipoMovimento = 'o';

		// TODO ENUMS
		// TODO verificar letras diferentes
		System.out.print("\nEscolha um tipo de moviemnto para o Dragao:");
		System.out.print("\nP-Parado:");
		System.out.print("\nA-Aleatorio:");
		System.out.println("\nZ-Aleatorio/Dormir:");

		tipoMovimento = scan.next().charAt(0);
		tipoMovimento = Character.toLowerCase(tipoMovimento);

		return tipoMovimento;
	}

	public int dragaoQuantidade() {
		int quantidade = 0;

		while (quantidade < 1 || quantidade > 3) {
			System.out.print("\nEscolha o numero de dragao entre 1 e 3:");
			quantidade = scan.nextInt();
		}

		return quantidade;
	}

	public void endGame(int VicDef) {
		if (VicDef == 0) {
			System.out.println("==================================");
			System.out.println("|==============DEFEAT============|");
			System.out.println("==================================");
		} else if (VicDef == 1) {
			System.out.println("==================================");
			System.out.println("|=============VICTORY============|");
			System.out.println("==================================");
		}
	}

	public void imprimeEquips(Heroi h) {
		System.out.println("<<<<<Equipamentos>>>>>");
		if (h.isShield()) {
			System.out.print("Escudo  ");
		}

		if (h.isArmed()) {
			System.out.print("Arma    ");
		}
		if (h.getNrDardos() > 0) {
			System.out.print("Dardos x" + h.getNrDardos());
		}

		System.out.println();
	}

	public char dardoDir() {
		System.out.println("Direcao dardo (W,A,S,D,C-Cancelar)");
		char comando = scan.next().charAt(0);
		comando = Character.toLowerCase(comando);
		return comando;
	}

	public void recebeComandoMove() {

		System.out.print("\nPressione uma tecla para mover o heroi (W,A,S,D,F-Dardo):");

		char comando = scan.next().charAt(0);

		switch (comando) {

		case 'w':
			labirinto.moveUp();
			break;

		case 'a':
			labirinto.moveLeft();
			break;

		case 's':
			labirinto.moveDown();
			break;

		case 'd':
			labirinto.moveRight();
			break;
		case 'f':
			if (labirinto.getHeroi().getNrDardos() > 0) {
				labirinto.shotDardo(dardoDir());
			}
			break;
		default:
			break;
		}
	}
}
