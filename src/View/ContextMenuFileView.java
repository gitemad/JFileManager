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
	
	/**
	 * Only constructor of class with following parameter requirement
	 * @param file the file you want to add context menu to it
	 */
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
	 * get the open menu item
	 * @return the open
	 */
	public JMenuItem getOpen() {
		return open;
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
	 * get the delete menu item
	 * @return the delete
	 */
	public JMenuItem getDelete() {
		return delete;
	}


	/**
	 * get the properties menu item
	 * @return the properties
	 */
	public JMenuItem getProperties() {
		return properties;
	}
	
	/**
	 * get the file
	 * @return the file
	 */
	public File getFile() {
		return file;
	}


	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}
	
	
	
}
