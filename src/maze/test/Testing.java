package maze.test;

import static org.junit.Assert.*;
import maze.cli.CliTesting;
import maze.logic.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Testing {

	Labirinto lab = new Labirinto();
	char instr[][];
	CliTesting cliTest;

	@Before
	public void setUp() throws Exception {

		cliTest = new CliTesting();

		lab.setTabuleiro(new ManualBuilder().build());

		lab.setHeroi(new Heroi(1, 1));
		lab.setEspada(new Peca(1, 7, 'E'));

		lab.setDardos(new Peca[0]);

		Dragao Drags[] = { new Dragao(4, 5, 'P') };
		lab.setDragoes(Drags);

		lab.getTabuleiro().setChar(lab.getHeroi().getPos().getX(),
				lab.getHeroi().getPos().getY(), 'H');

		for (Dragao d : lab.getDragoes()) {
			lab.getTabuleiro().setChar(d.getPos().getX(), d.getPos().getY(),
					'D');
		}

		lab.getTabuleiro().setChar(lab.getEspada().getPos().getX(),
				lab.getEspada().getPos().getY(), 'E');
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMove() {

		char movs[] = { 's', 's', 's', 's', 's', 's', 'w', 'w', 'w', 'w', 'w',
				'w', 'd', 'd', 'd', 'd', 'd' };

		for (int i = 0; i < movs.length; i++) {

			if (movs[i] == 'w') {
				lab.moveUp();
			} else if (movs[i] == 's') {
				lab.moveDown();
			} else if (movs[i] == 'a') {
				lab.moveLeft();
			} else if (movs[i] == 'd') {
				lab.moveRight();
			}
		}

		assertEquals(1, lab.getHeroi().getPos().getY());
		assertEquals(6, lab.getHeroi().getPos().getX());
	}

	@Test
	public void testIsArmed() {

		char movs[] = { 's', 's', 's', 's', 's', 's' };

		for (int i = 0; i < movs.length; i++) {

			if (movs[i] == 'w') {
				lab.moveUp();
			} else if (movs[i] == 's') {
				lab.moveDown();
			} else if (movs[i] == 'a') {
				lab.moveLeft();
			} else if (movs[i] == 'd') {
				lab.moveRight();
			}
			lab.checkCollision();
		}
		cliTest.imprimeTabuleiro(lab.getTabuleiro());

		assertEquals(true, ((Heroi) lab.getHeroi()).isArmed());
		assertEquals('A', lab.getHeroi().getSigla());
	}

	@Test
	public void testDie() {

		char movs[] = { 's', 's', 's', 's', 'd', 'd' };

		for (int i = 0; i < movs.length; i++) {

			if (movs[i] == 'w') {
				lab.moveUp();
			} else if (movs[i] == 's') {
				lab.moveDown();
			} else if (movs[i] == 'a') {
				lab.moveLeft();
			} else if (movs[i] == 'd') {
				lab.moveRight();
			}
			lab.checkCollision();
		}
		cliTest.imprimeTabuleiro(lab.getTabuleiro());

		assertEquals(false, lab.getHeroi().getActive());

	}

	@Test
	public void testKillDrag() {

		char movs[] = { 's', 's', 'u', 'd', 's', 's', 's', 's', 'w', 'w', 'a',
				'd', 'd', 'd' };

		for (int i = 0; i < movs.length; i++) {

			if (movs[i] == 'w') {
				lab.moveUp();
			} else if (movs[i] == 's') {
				lab.moveDown();
			} else if (movs[i] == 'a') {
				lab.moveLeft();
			} else if (movs[i] == 'd') {
				lab.moveRight();
			}

			lab.checkCollision();
		}
		cliTest.imprimeTabuleiro(lab.getTabuleiro());

		assertEquals(false, lab.getDragoes()[0].getActive());
	}

	@Test
	public void testExitSucces() {

		char movs[] = { 'd', 'd', 'd', 'd', 'd', 'd', 'd', 's', 's', 's', 's',
				'd' };

		for (int i = 0; i < movs.length; i++) {

			if (movs[i] == 'w') {
				lab.moveUp();
			} else if (movs[i] == 's') {
				lab.moveDown();
			} else if (movs[i] == 'a') {
				lab.moveLeft();
			} else if (movs[i] == 'd') {
				lab.moveRight();
			}
			lab.checkCollision();
		}
		cliTest.imprimeTabuleiro(lab.getTabuleiro());

		assertEquals(false, lab.getHeroi().getActive());
	}

	@Test
	public void testExitFail() {

		char movs[] = { 'd', 'd', 'd', 'd', 'd', 'd', 'd', 's', 's', 's', 's',
				'd' };

		for (int i = 0; i < movs.length; i++) {

			if (movs[i] == 'w') {
				lab.moveUp();
			} else if (movs[i] == 's') {
				lab.moveDown();
			} else if (movs[i] == 'a') {
				lab.moveLeft();
			} else if (movs[i] == 'd') {
				lab.moveRight();
			}
			lab.checkCollision();
		}
		cliTest.imprimeTabuleiro(lab.getTabuleiro());

		assertEquals(false, lab.getHeroi().getActive());
		assertEquals(false, lab.gameOver());
	}

	// testes + complexos

	@Test
	public void testRandomDragonMoving() {

		Dragao Drags[] = { new Dragao(4, 5, 'A'), new Dragao(6, 1, 'A'),
				new Dragao(6, 9, 'A') };
		lab.setDragoes(Drags);

		System.out.println("l\n");

		for (int i = 0; i < 30; i++) {

			for (Dragao d : lab.getDragoes()) {
				lab.moverDragao(d);
			}
		}

		assertEquals(true,
				(lab.getDragoes()[0].getPos().getX() != 4)
						|| (lab.getDragoes()[0].getPos().getY() != 5));
		assertEquals(true,
				(lab.getDragoes()[1].getPos().getX() != 6)
						|| (lab.getDragoes()[1].getPos().getY() != 1));
		assertEquals(true,
				(lab.getDragoes()[2].getPos().getX() != 6)
						|| (lab.getDragoes()[2].getPos().getY() != 9));
	}

	@Test
	public void testDragonSleep() {
		Dragao Drags[] = { new Dragao(4, 5, 'z') };
		lab.setDragoes(Drags);
		for (int i = 0; i < 20; i++) {
			lab.updateDragons();
			if (lab.getDragoes()[0].isDormir()) {
				assertEquals(true, lab.getDragoes()[0].getTurnsSleeping() > 0);
			}
		}
	}

	@Test
	public void testDardosApanhar() {
		Peca Dardos[] = { new Peca(4, 1, '«'), new Peca(3, 1, '«') };
		lab.setDardos(Dardos);
		char movs[] = { 'd', 'd', 'd' };

		for (int i = 0; i < movs.length; i++) {

			if (movs[i] == 'w') {
				lab.moveUp();
			} else if (movs[i] == 's') {
				lab.moveDown();
			} else if (movs[i] == 'a') {
				lab.moveLeft();
			} else if (movs[i] == 'd') {
				lab.moveRight();
			}

			lab.checkCollision();
		}
		assertEquals(true, lab.getHeroi().getNrDardos() == 2);
	}

	@Test
	public void testDardosDisparar() {
		Peca Dardos[] = { new Peca(4, 1, '«') };
		lab.setDardos(Dardos);
		char movs[] = { 'd', 'd', 'd' };

		for (int i = 0; i < movs.length; i++) {

			if (movs[i] == 'w') {
				lab.moveUp();
			} else if (movs[i] == 's') {
				lab.moveDown();
			} else if (movs[i] == 'a') {
				lab.moveLeft();
			} else if (movs[i] == 'd') {
				lab.moveRight();
			}

			lab.checkCollision();
		}

		lab.shotDardo('s');

		assertEquals(true, lab.gameOver());
	}

	@Test
	public void testMazeBuilder() {
		Labirinto lab = new Labirinto(2, 'p', 1, 15);

		assertEquals(true, lab.getTabuleiro().getTamanho() == 15);
		assertEquals(true, lab.getDragoes().length == 2);
	}

	@Test
	public void testShield() {
		Peca escudo = new Peca(2, 1, 'P');
		lab.setEscudo(escudo);
		lab.moveRight();
		lab.checkCollision();

		assertEquals(true, lab.getHeroi().isShield());
	}

}
