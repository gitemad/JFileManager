import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Emad
 *
 */
public class ContextMenuFile extends JPopupMenu {
	
	JMenuItem open;
	JMenuItem rename;
	JMenuItem copy;
	JMenuItem cut;
	JMenuItem delete;
	JMenuItem properties;
	
	
	public ContextMenuFile() {
		open = new JMenuItem("Open");
		rename = new JMenuItem("Rename");
		copy = new JMenuItem("Copy");
		cut = new JMenuItem("Cut");
		delete = new JMenuItem("Delete");
		properties = new JMenuItem("Properties");
		
		open.setMnemonic(KeyEvent.VK_O);
		rename.setMnemonic(KeyEvent.VK_M);
		copy.setMnemonic(KeyEvent.VK_C);
		cut.setMnemonic(KeyEvent.VK_T);
		delete.setMnemonic(KeyEvent.VK_D);
		properties.setMnemonic(KeyEvent.VK_R);
		
		add(open);
		add(rename);
		add(copy);
		add(cut);
		add(delete);
		add(properties);
	}
}
