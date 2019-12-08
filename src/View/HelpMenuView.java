package View;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * @author Emad
 *
 */
public class HelpMenuView extends JMenu {
	
	private JMenuItem about;
	private JMenuItem settings;
	private JMenuItem help;
		
	/**
	 * Only constructor of class without any parameter requirement
	 */
	public HelpMenuView() {
		super("Help");
		this.setMnemonic(KeyEvent.VK_H);
		
		about = new JMenuItem("About Me");
		about.setMnemonic(KeyEvent.VK_A);
		this.add(about);
	
		
		settings = new JMenuItem("Settings");
		settings.setMnemonic(KeyEvent.VK_S);
		this.add(settings);
		
		help = new JMenuItem("Help");
		help.setMnemonic(KeyEvent.VK_H);
		help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		this.add(help);
		
	}

	/**
	 * get the about menu item
	 * @return the about
	 */
	public JMenuItem getAbout() {
		return about;
	}

	/**
	 * get the settings menu item
	 * @return the settings
	 */
	public JMenuItem getSettings() {
		return settings;
	}

	/**
	 * get the help menu item
	 * @return the help
	 */
	public JMenuItem getHelp() {
		return help;
	}
	
	
}
