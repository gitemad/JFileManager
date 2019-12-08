package Controller;

import java.awt.event.ActionListener;

import Model.NavigateButtonModel;
import Model.Originator;
import View.NavigateButtonView;

public class NavigateButtonController {
	
	private NavigateButtonModel model;
	private NavigateButtonView view;
	private Originator originator;
	
	/**
	 * Only constructor of class with following parameter requirement
	 * @param model the navigation button model
	 * @param view the navigation button view
	 */
	public NavigateButtonController(NavigateButtonModel model, NavigateButtonView view) {
		this.model = model;
		this.view = view;
		originator = new Originator();
	}
	
	public void addMemento(String path) {
		originator.set(path);
		model.getSavedStates().push(originator.saveToMemento());
		view.setEnabled(true);
	}
	
	
	public String restoreMemento() {
		originator.restoreFromMemento(model.getSavedStates().pop());
		if (model.getSavedStates().size() == 0) {
			view.setEnabled(false);
		}
		return originator.get();
	}
	
	/**
	 * 
	 * @param listener
	 */
	public void addActionListener(ActionListener listener) {
		this.view.addActionListener(listener);
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
