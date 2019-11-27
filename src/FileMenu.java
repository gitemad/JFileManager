import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Emad
 *
 */
public class FileMenu extends JMenu {
	
	JMenuItem newFile;
	JMenuItem newFolder;
	JMenuItem delete;
	JMenuItem sync;
	
	public FileMenu(String name) {
		super(name);
		this.setMnemonic(KeyEvent.VK_F);
		
		newFile = new JMenuItem("New File");
		newFile.setMnemonic(KeyEvent.VK_F);
		this.add(newFile);
		
		newFolder = new JMenuItem("New Folder");
		newFolder.setMnemonic(KeyEvent.VK_N);
		this.add(newFolder);
		
		delete = new JMenuItem("Delete");
		delete.setMnemonic(KeyEvent.VK_D);
		this.add(delete);
		
		sync = new JMenuItem("Synchronize");
		sync.setMnemonic(KeyEvent.VK_S);
		this.add(sync);
	}
	
}
