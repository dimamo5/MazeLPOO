package maze.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import maze.logic.Labirinto;

public class MazeIOFile {
	private Labirinto lab;
	private File file;

	public MazeIOFile(File f, Labirinto lab) {
		file = f;
		this.lab = lab;
	}

	public void saveMaze() {
		ObjectOutputStream out;

		try {
			out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(lab);
			out.flush();
			out.close();

		} catch (IOException i) {
			i.printStackTrace();
		}

	}

	public Labirinto loadMaze() {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(file));
			lab = (Labirinto) in.readObject();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return lab;
	}

	public Labirinto getLab() {
		return lab;
	}

	public void setLab(Labirinto lab) {
		this.lab = lab;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
}
