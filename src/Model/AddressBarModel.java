package Model;

import Model.Originator.Memento;

public class AddressBarModel {
	
	private String path;
	
	/**
	 * Only constructor of class with following parameter requirement
	 * @param path the path of directory
	 */
	public AddressBarModel(String path) {
		this.path = path;
	}

	/**
	 * get the path address
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * set the path
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
}
