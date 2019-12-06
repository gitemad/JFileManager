package Controller;

import Model.AddressBarModel;
import View.AddressBarView;

public class AddressBarController {
	
	private AddressBarModel model;
	private AddressBarView view;
	
	public AddressBarController(AddressBarModel model, AddressBarView view) {
		this.model = model;
		this.view = view;
	}
	
	public void setPath(String path) {
		model.setPath(path);
		updateView();
	}
	
	private void updateView() {
		view.setText(model.getPath());
	}
}
