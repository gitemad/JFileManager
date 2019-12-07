package Controller;

import java.awt.Color;

import Model.FileLabelModel;
import View.FileLabelView;

public class FileLabelController {
	
	private FileLabelModel model;
	private FileLabelView view;
	private Color unselect;
	private Color hover;
	private Color select;
	
	/**
	 * @param model
	 * @param view
	 */
	public FileLabelController(FileLabelModel model, FileLabelView view) {
		this.model = model;
		this.view = view;
		unselect = view.getBackground();
		hover = view.getBackground().darker();
		select = view.getBackground().darker().darker();
		
		
	}

	public void selected() {
		model.setClicked(true);
		view.setOpaque(true);
		view.setBackground(select);
	}
	
	public void hover() {
		view.setOpaque(true);
		view.setBackground(hover);
	}
	
	public void unSelected() {
		model.setClicked(false);
		view.setBackground(unselect);
		view.setOpaque(false);
	}
	
	
}
