package Controller;

import Model.NavigateButtonModel;
import View.NavigateButtonView;

public class NavigateButtonController {
	
	private NavigateButtonModel model;
	private NavigateButtonView view;
	
	/**
	 * Only constructor of class with following parameter requirement
	 * @param model the navigation button model
	 * @param view the navigation button view
	 */
	public NavigateButtonController(NavigateButtonModel model, NavigateButtonView view) {
		this.model = model;
		this.view = view;
	}
	
	/**
	 * set button state
	 * @param enable 
	 */
	public void setEnable(boolean enable) {
		model.setEnable(enable);
		updateView();
	}
	
	//update the view
	private void updateView() {
		view.setEnabled(model.isEnable());
	}
}
