package Controller;

import java.awt.event.ActionListener;

import javax.swing.*;

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
	
	public void setAction(Action action) {
		this.view.setAction(action);
	}
	
	public void putInputMap(KeyStroke keyStroke, String key) {
		this.view.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, key);
	}
	
	public void putActionMap(Action action, String key) {
		this.view.getActionMap().put(key, action);
		this.view.setEnabled(this.model.isEnable());
		this.view.setIcon(view.getIcon());
	}
	
	public void setMnemonic(int key) {
		this.view.setMnemonic(key);
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
