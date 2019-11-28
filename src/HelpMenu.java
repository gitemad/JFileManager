import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Emad
 *
 */
public class HelpMenu extends JMenu {
	
	private JMenuItem about;
	private JMenuItem settings;
	private JMenuItem help;
	
	public HelpMenu() {
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
		this.add(help);
		
	}
	
}