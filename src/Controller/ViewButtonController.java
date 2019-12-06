package Controller;

import Model.ViewButtonModel;
import View.ViewButton;

public class ViewButtonController {
	
	private ViewButtonModel model;
	private ViewButton view;
	
	public ViewButtonController(ViewButtonModel model, ViewButton view) {
		this.model = model;
		this.view = view;
	}
	
	public void setSelected(boolean selected) {
		model.setSelected(selected);
		updateView();
	}
	
	private void updateView() {
		view.setSelected(model.getSelected());
	}
}
