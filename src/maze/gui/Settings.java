package maze.gui;

import java.awt.event.KeyEvent;

public class Settings {
	private int mazeSize = 15, numDragons = 3, mazeType = 1;
	private char typeDragons = 'p';
	private int up = KeyEvent.VK_UP, down = KeyEvent.VK_DOWN, left = KeyEvent.VK_LEFT, right = KeyEvent.VK_RIGHT, shootUp = KeyEvent.VK_W,
			shootDown = KeyEvent.VK_S, shootLeft = KeyEvent.VK_A, shootRigth = KeyEvent.VK_D;

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

	public int getShootRigth() {
		return shootRigth;
	}

	public void setShootRigth(int shootRigth) {
		this.shootRigth = shootRigth;
	}

	public int getShootLeft() {
		return shootLeft;
	}

	public void setShootLeft(int shootLeft) {
		this.shootLeft = shootLeft;
	}

	public int getShootDown() {
		return shootDown;
	}

	public void setShootDown(int shootDown) {
		this.shootDown = shootDown;
	}

	public int getShootUp() {
		return shootUp;
	}

	public void setShootUp(int shootUp) {
		this.shootUp = shootUp;
	}

}
