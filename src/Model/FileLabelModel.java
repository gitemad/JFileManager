package Model;

import java.io.*;

public class FileLabelModel {
	
	private File file;
	private boolean clicked;
	
	public FileLabelModel(File file) {
		
		this.file = file;
		this.clicked = false;
		
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @return the clicked
	 */
	public boolean isClicked() {
		return clicked;
	}

	/**
	 * @param clicked the clicked to set
	 */
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
	
	
}
