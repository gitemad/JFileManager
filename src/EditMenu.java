import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Emad
 *
 */
public class EditMenu extends JMenu {
	
	private JMenuItem rename;
	private JMenuItem copy;
	private JMenuItem cut;
	private JMenuItem paste;
	private JMenuItem sync;
	
	public EditMenu() {
		super("Edit");
		this.setMnemonic(KeyEvent.VK_E);
		
		rename = new JMenuItem("Rename");
		rename.setMnemonic(KeyEvent.VK_M);
		this.add(rename);
	
		
		copy = new JMenuItem("Copy");
		copy.setMnemonic(KeyEvent.VK_C);
		this.add(copy);
		
		cut = new JMenuItem("Cut");
		cut.setMnemonic(KeyEvent.VK_T);
		this.add(cut);
		
		paste = new JMenuItem("Paste");
		paste.setMnemonic(KeyEvent.VK_P);
		this.add(paste);
		
		sync = new JMenuItem("Synchronize");
		sync.setMnemonic(KeyEvent.VK_S);
		this.add(sync);
	}
	
}
