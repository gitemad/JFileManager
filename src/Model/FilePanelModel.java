package Model;

import java.io.File;
import java.util.ArrayList;

/**
 * 
 * @author Emad
 *
 */
public class FilePanelModel {
	
	private File folder;
	private ArrayList<File> currentFiles;
	private boolean ctrlDown;
	
	public FilePanelModel(File folder) {
		this.folder = folder;
		ctrlDown = false;
	}

	/**
	 * @return the folder
	 */
	public File getFolder() {
		return folder;
	}

	/**
	 * @param folder the folder to set
	 */
	public void setFolder(File folder) {
		this.folder = folder;
	}

	/**
	 * @return the currentFiles
	 */
	public ArrayList<File> getCurrentFiles() {
		return currentFiles;
	}

	/**
	 * @param currentFiles the currentFiles to set
	 */
	public void setCurrentFiles(ArrayList<File> currentFiles) {
		this.currentFiles = currentFiles;
	}

	/**
	 * @return the ctrlDown
	 */
	public boolean isCtrlDown() {
		return ctrlDown;
	}

	/**
	 * @param ctrlDown the ctrlDown to set
	 */
	public void setCtrlDown(boolean ctrlDown) {
		this.ctrlDown = ctrlDown;
	}

}
