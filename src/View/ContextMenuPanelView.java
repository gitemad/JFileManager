package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Emad
 *
 */
public class ContextMenuPanelView extends JPopupMenu {
	
	JMenuItem paste;
	JMenuItem newFile;
	JMenuItem newFolder;
	JMenuItem properties;
	
	/**
	 * Only constructor of class without any parameter requirement
	 */
	public ContextMenuPanelView() {
		paste = new JMenuItem("Paste");
		newFile = new JMenuItem("New File");
		newFolder = new JMenuItem("New Folder");
		properties = new JMenuItem("Properties");
		
		paste.setMnemonic(KeyEvent.VK_P);
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		newFile.setMnemonic(KeyEvent.VK_F);
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		newFolder.setMnemonic(KeyEvent.VK_N);
		newFolder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		properties.setMnemonic(KeyEvent.VK_R);
		
		add(paste);
		add(newFile);
		add(newFolder);
		add(properties);
	}

	/**
	 * @return the paste
	 */
	public JMenuItem getPaste() {
		return paste;
	}

	/**
	 * @return the newFile
	 */
	public JMenuItem getNewFile() {
		return newFile;
	}

	/**
	 * @return the newFolder
	 */
	public JMenuItem getNewFolder() {
		return newFolder;
	}

	/**
	 * @return the properties
	 */
	public JMenuItem getProperties() {
		return properties;
	}
	
	
}
