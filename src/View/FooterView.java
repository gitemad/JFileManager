package View;
import javax.swing.*;

import Controller.ViewButtonController;
import Model.ViewButtonModel;
import View.ViewButtonView;

import java.awt.*;
import java.awt.event.*;

/**
 * @author Emad
 *
 */
public class FooterView extends JPanel {
	
	//View
	private ViewButtonView listView;
	private ViewButtonView gridView;
	
	//Model
	private ViewButtonModel listModel;
	private ViewButtonModel gridModel;
	
	//Controller
	private ViewButtonController listController;
	private ViewButtonController gridController;

	private FlowLayout layout;
	
	/**
	 * Only constructor of class without any parameter requirement
	 */
	public FooterView() {
		super();
		//list button
		listView = new ViewButtonView(new ImageIcon("img/listshow.png"), true);
		listModel = new ViewButtonModel(true);
		listController = new ViewButtonController(listModel, listView);
		
		//grid button
		gridView = new ViewButtonView(new ImageIcon("img/gridshow.png"), false);
		gridModel = new ViewButtonModel(false);
		gridController = new ViewButtonController(gridModel, gridView);
		
		layout = new FlowLayout(FlowLayout.RIGHT);
		
		this.setLayout(layout);
		this.add(listView);
		this.add(gridView);
	}
	
	/**
	 * add action listener to list view button
	 * @param actionListener action listener you want to add to list view button
	 */
	public void addListListener(ActionListener actionListener) {
		listView.addActionListener(actionListener);
	}
	
	/**
	 * add action listener to grid view button
	 * @param actionListener
	 */
	public void addGridListener(ActionListener actionListener) {
		gridView.addActionListener(actionListener);
	}

	/**
	 * get the view button controller
	 * @return the listController
	 */
	public ViewButtonController getListController() {
		return listController;
	}

	/**
	 * get the view button controller
	 * @return the gridController
	 */
	public ViewButtonController getGridController() {
		return gridController;
	}
	
	
}

