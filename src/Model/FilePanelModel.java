package Model;

import java.io.File;

/**
 * 
 * @author Emad
 *
 */
public class FilePanelModel {
	
	private File folder;
	private File[] currentFiles;
	
	public FilePanelModel(File folder) {
		this.folder = folder;
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
	public File[] getCurrentFiles() {
		return currentFiles;
	}

	/**
	 * @param currentFiles the currentFiles to set
	 */
	public void setCurrentFiles(File[] currentFiles) {
		this.currentFiles = currentFiles;
	}
	
	
}
