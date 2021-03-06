package View;

import javax.swing.*;

import Controller.EditMenuController;
import Controller.HelpMenuController;

import java.awt.*;
import java.awt.event.*;

/**
 * @author Emad
 *
 */
public class MenuView extends JMenuBar {
	
	//View
	private FileMenuView fileView;
	private EditMenuView editView;
	private HelpMenuView helpView;
	
	//Controller
	private EditMenuController editController;
	private HelpMenuController helpController;
	
	/**
	 * Only constructor of class without any parameter requirement
	 */
	public MenuView() {
		super();
		fileView = new FileMenuView();
		this.add(fileView);
		
		editView = new EditMenuView();
		editController = new EditMenuController(editView);
		this.add(editView);

		helpView = new HelpMenuView();
		helpController = new HelpMenuController(helpView);
		this.add(helpView);
		
	}

	/**
	 * @return the helpView
	 */
	public HelpMenuView getHelpView() {
		return helpView;
	}

	/**
	 * @return the helpController
	 */
	public HelpMenuController getHelpController() {
		return helpController;
	}

	/**
	 * @return the fileView
	 */
	public FileMenuView getFileView() {
		return fileView;
	}

	/**
	 * @return the editView
	 */
	public EditMenuView getEditView() {
		return editView;
	}

	/**
	 * @return the editController
	 */
	public EditMenuController getEditController() {
		return editController;
	}
	
	
	
}