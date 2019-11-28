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
		about.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent eve) {
				JOptionPane.showMessageDialog(null, "Powerd By: Emad", "About Me", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		this.add(about);
	
		
		settings = new JMenuItem("Settings");
		settings.setMnemonic(KeyEvent.VK_S);
		settings.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent eve) {
				Settings settings = new Settings();
			}
		});
		this.add(settings);
		
		help = new JMenuItem("Help");
		help.setMnemonic(KeyEvent.VK_H);
		help.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent eve) {
				JOptionPane.showMessageDialog(null, "Let the second phase finished!", "Help", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		this.add(help);
		
	}
	
	
}
