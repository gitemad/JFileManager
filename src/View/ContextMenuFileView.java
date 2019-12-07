package View;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * @author Emad
 *
 */
public class ContextMenuFileView extends JPopupMenu {
	
	private File file;
	private JMenuItem open;
	private JMenuItem rename;
	private JMenuItem copy;
	private JMenuItem cut;
	private JMenuItem delete;
	private JMenuItem properties;
	
	
	public ContextMenuFileView(File file) {
		this.file = file;
		open = new JMenuItem("Open");
		rename = new JMenuItem("Rename");
		copy = new JMenuItem("Copy");
		cut = new JMenuItem("Cut");
		delete = new JMenuItem("Delete");
		properties = new JMenuItem("Properties");
		
		open.setMnemonic(KeyEvent.VK_O);
		rename.setMnemonic(KeyEvent.VK_M);
		rename.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		copy.setMnemonic(KeyEvent.VK_C);
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		cut.setMnemonic(KeyEvent.VK_T);
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		delete.setMnemonic(KeyEvent.VK_D);
		delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		properties.setMnemonic(KeyEvent.VK_R);
		
		
		add(open);
		add(rename);
		add(copy);
		add(cut);
		add(delete);
		add(properties);
	}


	/**
	 * @return the open
	 */
	public JMenuItem getOpen() {
		return open;
	}


	/**
	 * @return the rename
	 */
	public JMenuItem getRename() {
		return rename;
	}


	/**
	 * @return the copy
	 */
	public JMenuItem getCopy() {
		return copy;
	}


	/**
	 * @return the cut
	 */
	public JMenuItem getCut() {
		return cut;
	}


	/**
	 * @return the delete
	 */
	public JMenuItem getDelete() {
		return delete;
	}


	/**
	 * @return the properties
	 */
	public JMenuItem getProperties() {
		return properties;
	}
	
	/**
	 * 
	 * @return
	 */
	public File getFile() {
		return file;
	}
}
