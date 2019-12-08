package View;
import javax.swing.*;


import java.awt.*;
import java.awt.event.*;

/**
 * @author Emad
 *
 */
public class EditMenuView extends JMenu {
	
	private JMenuItem rename;
	private JMenuItem copy;
	private JMenuItem cut;
	private JMenuItem paste;
	private JMenuItem sync;
		
	/**
	 * Only constructor of class without any parameter requirement
	 */
	public EditMenuView() {
		super("Edit");
		this.setMnemonic(KeyEvent.VK_E);
		
		rename = new JMenuItem("Rename");
		rename.setMnemonic(KeyEvent.VK_M);
		rename.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		this.add(rename);
	
		
		copy = new JMenuItem("Copy");
		copy.setMnemonic(KeyEvent.VK_C);
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		this.add(copy);
		
		cut = new JMenuItem("Cut");
		cut.setMnemonic(KeyEvent.VK_T);
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		this.add(cut);
		
		paste = new JMenuItem("Paste");
		paste.setMnemonic(KeyEvent.VK_P);
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		this.add(paste);
		
		sync = new JMenuItem("Synchronize");
		sync.setMnemonic(KeyEvent.VK_S);
		
		this.add(sync);
	}

	/**
	 * get the rename menu item
	 * @return the rename
	 */
	public JMenuItem getRename() {
		return rename;
	}

	/**
	 * get the copy menu item
	 * @return the copy
	 */
	public JMenuItem getCopy() {
		return copy;
	}

	/**
	 * get the cut menu item
	 * @return the cut
	 */
	public JMenuItem getCut() {
		return cut;
	}

	/**
	 * get the paste menu item
	 * @return the paste
	 */
	public JMenuItem getPaste() {
		return paste;
	}

	/**
	 * get the sync menu item
	 * @return the sync
	 */
	public JMenuItem getSync() {
		return sync;
	}
	
	
	
}
