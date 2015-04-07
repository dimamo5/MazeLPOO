package maze.gui;

public class Settings {
	private int mazeSize = 7, numDragons = 2, mazeType = 1;
	private char typeDragons = 'A';

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
