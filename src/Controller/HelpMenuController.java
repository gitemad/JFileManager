package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.HelpMenuView;
import View.SettingsView;

public class HelpMenuController {
	
	private HelpMenuView view;
	
	/**
	 * Only constructor of class with following parameter requirement
	 * @param view the help menu view
	 */
	public HelpMenuController(HelpMenuView view) {
		this.view = view;
		
		view.getAbout().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent eve) {
				JOptionPane.showMessageDialog(null, "Powerd By: Emad", "About Me", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		view.getSettings().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent eve) {
				SettingsView settings = new SettingsView();
			}
		});
		
		String helpText = "<html>"
				+ "switch between views by clicking on the toggle buttons at footer of program"
				+ "<br/>"
				+ "Navigate files by tree or address bar at header of program"
				+ "<br/>"
				+ "Search files by type it name in the search bar"
				+ "<br/>"
				+ "rename files (F2)"
				+ "<br/>"
				+ "create new file (Ctrl + F) and new folder (Ctrl + N)"
				+ "<br/>"
				+ "copy (Ctrl + C), cut (Ctrl + X) and paste (Ctrl + V) files"
				+ "<br/>"
				+ "Synchronize files between two pcs in edit menu"
				+ "</html>";
		view.getHelp().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent eve) {
				JOptionPane.showMessageDialog(null, helpText, "Help", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
}
