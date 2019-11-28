import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Emad
 *
 */
public class ContextMenuPanel extends JPopupMenu {
	
	JMenuItem paste = new JMenuItem("Paste");
	JMenuItem newFile = new JMenuItem("New File");
	JMenuItem newFolder = new JMenuItem("New Folder");
	JMenuItem properties = new JMenuItem("Properties");
	
	
	public ContextMenuPanel() {
		paste.setMnemonic(KeyEvent.VK_P);
		newFile.setMnemonic(KeyEvent.VK_F);
		newFolder.setMnemonic(KeyEvent.VK_N);
		properties.setMnemonic(KeyEvent.VK_R);
		add(paste);
		add(newFile);
		add(newFolder);
		add(properties);
	}
}
