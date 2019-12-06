package Controller;

import Model.NavigateButtonModel;
import View.NavigateButtonView;

public class NavigateButtonController {
	
	private NavigateButtonModel model;
	private NavigateButtonView view;
	
	public NavigateButtonController(NavigateButtonModel model, NavigateButtonView view) {
		this.model = model;
		this.view = view;
	}
	
	public void setEnable(boolean enable) {
		model.setEnable(enable);
		updateView();
	}
	
	private void updateView() {
		view.setEnabled(model.isEnable());
	}
}
