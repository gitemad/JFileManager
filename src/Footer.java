import javax.swing.*;

import Controller.ViewButtonController;
import Model.ViewButtonModel;
import View.ViewButton;

import java.awt.*;
import java.awt.event.*;

/**
 * @author Emad
 *
 */
public class Footer extends JPanel {
	
	//View
	private ViewButton listView;
	private ViewButton gridView;
	
	//Model
	private ViewButtonModel listModel;
	private ViewButtonModel gridModel;
	
	//Controller
	private ViewButtonController listController;
	private ViewButtonController gridController;

	private FlowLayout layout;
	
	public Footer() {
		super();
		//list button
		listView = new ViewButton(new ImageIcon("img/listshow.png"), true);
		listModel = new ViewButtonModel(true);
		listController = new ViewButtonController(listModel, listView);
		
		//grid button
		gridView = new ViewButton(new ImageIcon("img/gridshow.png"), false);
		gridModel = new ViewButtonModel(false);
		gridController = new ViewButtonController(gridModel, gridView);
		
		layout = new FlowLayout(FlowLayout.RIGHT);
		
		this.setLayout(layout);
		this.add(listView);
		this.add(gridView);
	}
	
	public void addListListener(ActionListener actionListener) {
		listView.addActionListener(actionListener);
	}
	
	public void addGridListener(ActionListener actionListener) {
		gridView.addActionListener(actionListener);
	}

	/**
	 * @return the listController
	 */
	public ViewButtonController getListController() {
		return listController;
	}

	/**
	 * @return the gridController
	 */
	public ViewButtonController getGridController() {
		return gridController;
	}
	
	
}
