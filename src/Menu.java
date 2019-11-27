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
		file = new FileMenu();
		this.add(file);
		
		edit = new EditMenu();
		this.add(edit);

		help = new HelpMenu();
		this.add(help);
		
	}
}