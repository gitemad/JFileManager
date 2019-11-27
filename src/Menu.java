import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Emad
 *
 */
public class Menu extends JMenuBar{
	
	JMenu file;
	JMenu edit;
	JMenu help;
	
	public Menu() {
		super();
		file = new FileMenu("File");
		
		edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);
		
		help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		
		this.add(file);
		this.add(edit);
		this.add(help);
	}
}