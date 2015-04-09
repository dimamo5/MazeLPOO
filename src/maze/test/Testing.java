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

	//TODO limpar printf
	
	@Before
	public void setUp() throws Exception {

		cliTest = new CliTesting();

		lab.setTabuleiro(new ManualBuilder().build());

		lab.setEspada(new Peca(1, 7, 'E'));

		lab.setDardos(new Peca[0]);

		Dragao Drags[] = { new Dragao(4, 5, 'P') };
		lab.setDragoes(Drags);

		lab.getTabuleiro().setChar(lab.getHeroi().getPos().getX(), lab.getHeroi().getPos().getY(), 'H');

		for (Dragao d : lab.getDragoes()) {
			lab.getTabuleiro().setChar(d.getPos().getX(), d.getPos().getY(), 'D');
		}

		lab.getTabuleiro().setChar(lab.getEspada().getPos().getX(), lab.getEspada().getPos().getY(), 'E');
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMove() {
		lab.setHeroi(new Heroi(1, 1));
		char movs[] = { 's', 's', 's', 's', 's', 's', 'w', 'w', 'w', 'w', 'w', 'w', 'd', 'd', 'd', 'd', 'd' };

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
		lab.setHeroi(new Heroi(1, 1));
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
		lab.setHeroi(new Heroi(1, 1));
		char movs[] = { 's', 's', 's', 's', 'd', 'd' };

		int ret= 0;
		
		for (int i = 0; i < movs.length; i++) {

			if (movs[i] == 'w') {
				ret = lab.moveUp();
			} else if (movs[i] == 's') {
				ret = lab.moveDown();
			} else if (movs[i] == 'a') {
				ret = lab.moveLeft();
			} else if (movs[i] == 'd') {
				ret = lab.moveRight();
			}
			lab.checkCollision();
		}
		cliTest.imprimeTabuleiro(lab.getTabuleiro());
		assertEquals(0,ret);
		assertEquals(lab.getHeroi().getSigla(), lab.getTabuleiro().getTab()[lab.getHeroi().getPos().getY()][lab.getHeroi().getPos().getX()]);

		assertEquals(false, lab.getHeroi().getActive());
	}

	@Test
	public void testKillDrag() {
		lab.setHeroi(new Heroi(1, 1));
		char movs[] = { 's', 's', 'u', 'd', 's', 's', 's', 's', 'w', 'w', 'a', 'd', 'd', 'd' };
		int ret = 0;
		
		for (int i = 0; i < movs.length; i++) {

			if (movs[i] == 'w') {
				ret = lab.moveUp();
			} else if (movs[i] == 's') {
				ret = lab.moveDown();
			} else if (movs[i] == 'a') {
				ret = lab.moveLeft();
			} else if (movs[i] == 'd') {
				ret = lab.moveRight();
			}

			lab.checkCollision();
		}
		cliTest.imprimeTabuleiro(lab.getTabuleiro());
		
		assertEquals(lab.getHeroi().getSigla(), lab.getTabuleiro().getTab()[lab.getHeroi().getPos().getY()][lab.getHeroi().getPos().getX()]);
		assertEquals(0,ret);
		assertEquals(false, lab.getDragoes()[0].getActive());
	}

	@Test
	public void testExitSucces() {
		lab.setHeroi(new Heroi(1, 1));
		char movs[] = { 'd', 'd', 'd', 'd', 'd', 'd', 'd', 's', 's', 's', 's', 'd' };

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
		lab.setHeroi(new Heroi(1, 1));
		char movs[] = { 'd', 'd', 'd', 'd', 'd', 'd', 'd', 's', 's', 's', 's', 'd' };

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

		Dragao Drags[] = { new Dragao(4, 5, 'A'), new Dragao(6, 1, 'A'), new Dragao(6, 8, 'A') };
		lab.setDragoes(Drags);
		//boolean x = false;

		for (int i = 0; i < 1; i++) {
			System.out.println("lol\n");
			for (Dragao d : lab.getDragoes()) {
				lab.moverDragao(d);
			}

		}
		assertEquals(true, (lab.getDragoes()[0].getPos().getX() != 4) || (lab.getDragoes()[0].getPos().getY() != 5)
				|| (lab.getDragoes()[1].getPos().getX() != 6) || (lab.getDragoes()[1].getPos().getY() != 1)
				|| (lab.getDragoes()[2].getPos().getX() != 6) || (lab.getDragoes()[2].getPos().getY() != 9));
	}

	@Test
	public void testDragonSleep() {
		Dragao Drags[] = { new Dragao(4, 5, 'z') };
		lab.setDragoes(Drags);
		for (int i = 0; i < 20; i++) {
			lab.updateDragons();

			if (lab.getDragoes()[0].isDormir()) {
				assertEquals(true, lab.getDragoes()[0].getTurnsSleeping() > 0);
				assertEquals(true, lab.getDragoes()[0].getActive());
				assertEquals('Z',lab.getTabuleiro().getTab()[lab.getDragoes()[0].getPos().getY()][lab.getDragoes()[0].getPos().getX()]);
			}			 
		}
	}

	@Test
	public void testDardosApanhar() {
		lab.setHeroi(new Heroi(1, 1));
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
		assertEquals(true, lab.getDardos() != null);
		assertEquals(true, lab.getHeroi().getNrDardos() == 2);
	}

	@Test
	public void testDardosDisparar() {
		lab.setHeroi(new Heroi(1, 1));
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
		assertEquals(true, lab.getEscudo().getPos().getX() != 0);
		assertEquals(true, lab.getHeroi().getPos().getX() != 0);
		assertEquals(true, lab.getEspada().getPos().getX() != 0);
		assertEquals(true, lab.getTabuleiro().getTamanho() == 15);
		assertEquals(true, lab.getDragoes().length == 2);
		assertEquals(true, lab.getDragoes()[0].getPos().getX() != 0);
		assertEquals(lab.getDragoes()[0].getSigla(), lab.getTabuleiro().getTab()[lab.getDragoes()[0].getPos().getY()][lab.getDragoes()[0].getPos().getX()]);
	}

	@Test
	public void testShield() {
		lab.setHeroi(new Heroi(1, 1));
		Peca escudo = new Peca(2, 1, 'P');
		lab.setEscudo(escudo);
		lab.moveRight();
		lab.checkCollision();

		assertEquals(true, lab.getEscudo() != null);
		assertEquals(true, lab.getHeroi().isShield());
	}

	@Test
	public void testDragConfigs() {

		Dragao d = new Dragao(0, 0, 'P');
		Dragao d2 = new Dragao(0, 0, 'z');
		Dragao d3 = new Dragao(0, 0, 'z');
		Dragao d4 = new Dragao(0, 0, 'z');

		assertEquals('P', d.getStatus());
		assertEquals('z', d2.getStatus());
		assertEquals(0, d.getTurnsSleeping());

		d2.setDormir(true);
		assertEquals(true, d2.isDormir());

		d2.updateSleeping();
		assertEquals(-1, d2.getTurnsSleeping()); // nao acontece no decorrer
													// jogo

		d3.setDormir(true);
		d3.setTurnsSleeping(3);

		d3.updateSleeping();
		assertEquals(2, d3.getTurnsSleeping());

		d4.updateSleeping();
		d4.setDormir(false);
		assertEquals(true, (d4.getTurnsSleeping() < 7));
	}

	@Test
	public void testVer() {

		Peca x1 = new Peca(1, 1, 'p');
		Peca x2 = new Peca(2, 1, 'p');

		assertEquals(true, x1.ver(x2, lab.getTabuleiro(), 3));
		lab.getTabuleiro().setTamanho(15);

		assertEquals(true, lab.getTabuleiro().getTamanho() == 15);
	}

}
