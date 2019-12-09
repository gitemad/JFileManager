package Controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import Model.FilePanelModel;
import View.FilePanelView;
import View.RectangleDrawerView;

public class FilePanelController {
	
	private FilePanelModel model;
	private FilePanelView view;
	

	/**
	 * Only constructor of class with following parameter requirement
	 * @param view the file panel view
	 */
	public FilePanelController(FilePanelModel model, FilePanelView view) {
		this.model = model;
		this.view = view;
	}
	
	public void setFolder(File folder) {
		model.setFolder(folder);
		view.setPanelData(folder);
	}

	/**
	 * @return the model
	 */
	public FilePanelModel getModel() {
		return model;
	}

	/**
	 * @return the view
	 */
	public FilePanelView getView() {
		return view;
	}
	
	
}
