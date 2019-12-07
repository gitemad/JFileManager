package View;

import javax.swing.*;

import Controller.EditMenuController;

import java.awt.*;
import java.awt.event.*;

/**
 * @author Emad
 *
 */
public class MenuView extends JMenuBar{
	
	//View
	private FileMenuView fileView;
	private EditMenuView editView;
	private HelpMenuView helpView;
	
	//Controller
	private EditMenuController editController;
	
	public MenuView() {
		super();
		fileView = new FileMenuView();
		this.add(fileView);
		
		editView = new EditMenuView();
		editController = new EditMenuController(editView);
		this.add(editView);

		helpView = new HelpMenuView();
		this.add(helpView);
		
	}
}