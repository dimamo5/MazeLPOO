package maze.cli;

import java.util.Scanner;

import maze.logic.*;

public class Cli {

	private Scanner scan = new Scanner(System.in);

	public Cli() {
		// TODO Auto-generated constructor stub
	}

	public void finalize() {
		scan.close();
	}

	public int CliMazeChoose() {

		System.out.println("1-Labirinto Predefinido");

		System.out.println("2-Labirinto Automatico");

		System.out.print("Opção:");

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

	public char heroiComando() {
		char comando;

		System.out.print("\nPressione uma tecla para mover o heroi (W,A,S,D,F-Dardo):");

		comando = scan.next().charAt(0);

		return comando;
	}

	public char dragaoTipo() {
		char tipoMovimento = 'o';

		// TODO ENUMS
		// TODO verificar letras diferentes
		System.out.print("\nEscolha um tipo de moviemnto para o Dragao:");
		System.out.print("\nP-Parado:");
		System.out.print("\nA-Aleatorio:");
		System.out.print("\nZ-Aleatorio/Dormir:");

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

	public void defeat() {
		System.out.println("==================================");
		System.out.println("|==============DEFEAT============|");
		System.out.println("==================================");
	}

	public void victory() {
		System.out.println("==================================");
		System.out.println("|=============VICTORY============|");
		System.out.println("==================================");
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

	public void imprimeBolaFogo(Tabuleiro tab, Heroi h, Dragao d) {
		for (int i = 1; i <= 3; i++) {
			if (tab.getTab()[d.getPos().getY()][d.getPos().getX() + i] != 'X') {
				tab.setChar(d.getPos().getX() + i, d.getPos().getY(), '*');
			}
			if (tab.getTab()[d.getPos().getY()][d.getPos().getX() - i] != 'X') {
				tab.setChar(d.getPos().getX() - i, d.getPos().getY(), '*');
			}
			if (tab.getTab()[d.getPos().getY() + i][d.getPos().getX()] != 'X') {
				tab.setChar(d.getPos().getX(), d.getPos().getY() + i, '*');
			}
			if (tab.getTab()[d.getPos().getY() + i][d.getPos().getX()] != 'X') {
				tab.setChar(d.getPos().getX(), d.getPos().getY() - i, '*');
			}
			try {
				Thread.sleep(500);                 // 1000 milliseconds is one second.
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}

	public char dardoDir() {
		System.out.println("Direcao dardo (W,A,S,D,C-Cancelar)");
		char comando = scan.next().charAt(0);
		comando = Character.toLowerCase(comando);
		return comando;
	}
}
