package Controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import View.FilePanelView;
import View.RectangleDrawerView;

public class FilePanelController {
	
	private FilePanelView view;
	

	/**
	 * Only constructor of class with following parameter requirement
	 * @param view the file panel view
	 */
	public FilePanelController(FilePanelView view) {
		this.view = view;
	}
	
}
