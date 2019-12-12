package Controller;

import Model.ViewButtonModel;
import View.ViewButtonView;

public class ViewButtonController {
	
	private ViewButtonModel model;
	private ViewButtonView view;
	
	/**
	 * Only constructor of class with following parameter requirement
	 * @param model the view button model
	 * @param view the view button view
	 */
	public ViewButtonController(ViewButtonModel model, ViewButtonView view) {
		this.model = model;
		this.view = view;
	}
	
	/**
	 * set selected state of button
	 * @param selected
	 */
	public void setSelected(boolean selected) {
		model.setSelected(selected);
		updateView();
	}
	
	/**
	 * detemine if button is selected or not
	 * @return selected
	 */
	public boolean isSelected() {
		return model.getSelected();
	}
	
	//update the button view
	private void updateView() {
		view.setSelected(model.getSelected());
	}
}
