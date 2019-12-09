package Model;

import java.io.*;

public class FileLabelModel {
	
	private File file;
	private boolean selected;
	
	/**
	 * Only constructor of class with following parameter requirement
	 * @param file file you want to create label model of it
	 */
	public FileLabelModel(File file) {
		
		this.file = file;
		this.selected = false;
		
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
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param clicked the selected state to set
	 */
	public void setSelected(boolean clicked) {
		this.selected = clicked;
	}
	
	
}
