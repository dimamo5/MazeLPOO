package maze.logic;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Pos implements Serializable{
	private int x,y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
		
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
	
}
