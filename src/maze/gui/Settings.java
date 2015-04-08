package maze.gui;

import java.awt.event.KeyEvent;

public class Settings {
	private int mazeSize = 15, numDragons = 3, mazeType = 1;
	private char typeDragons = 'p';
	private int up=KeyEvent.VK_UP, down=KeyEvent.VK_DOWN, left=KeyEvent.VK_LEFT,right=KeyEvent.VK_RIGHT,shot=KeyEvent.VK_F;
	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getShot() {
		return shot;
	}

	public void setShot(int shot) {
		this.shot = shot;
	}

	public int getMazeSize() {
		return mazeSize;
	}

	public void setMazeSize(int mazeSize) {
		this.mazeSize = mazeSize;
	}

	public int getNumDragons() {
		return numDragons;
	}

	public void setNumDragons(int numDragons) {
		this.numDragons = numDragons;
	}

	public int getMazeType() {
		return mazeType;
	}

	public void setMazeType(int mazeType) {
		this.mazeType = mazeType;
	}

	public char getTypeDragons() {
		return typeDragons;
	}

	public void setTypeDragons(char typeDragons) {
		this.typeDragons = typeDragons;
	}

}
