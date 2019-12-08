package Controller;

import Model.AddressBarModel;
import View.AddressBarView;

/**
 * 
 * @author Emad
 *
 */
public class AddressBarController {
	
	private AddressBarModel model;
	private AddressBarView view;
	
	/**
	 * Only constructor of class with following parameter requirement
	 * @param model model of address bar
	 * @param view view of address bar
	 */
	public AddressBarController(AddressBarModel model, AddressBarView view) {
		this.model = model;
		this.view = view;
	}
	
	/**
	 * set the path to address bar
	 * @param path the path to set
	 */
	public void setPath(String path) {
		model.setPath(path);
		updateView();
	}
	
	private void updateView() {
		view.setText(model.getPath());
	}
}
