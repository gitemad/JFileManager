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
	
	/**
	 * Only constructor of class with following parameters
	 * @param folder folder to show its files
	 */
	public FilePanelModel(File folder) {
		this.folder = folder;
		currentFiles = new ArrayList<File>();
		ctrlDown = false;
	}

	/**
	 * get the current folder
	 * @return the folder which its files shown 
	 */
	public File getFolder() {
		return folder;
	}

	/**
	 * set the folder to show its files
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
	 * add file to current files
	 * @param file file to add
	 */
	public void addCurrentFile(File file) {
		this.currentFiles.add(file);
	}
	
	/**
	 * remove all current files
	 */
	public void removeCurrentFiles() {
		this.currentFiles.removeAll(this.currentFiles);
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
