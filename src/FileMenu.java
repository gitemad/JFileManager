import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Emad
 *
 */
public class FileMenu extends JMenu {
	
	private JMenuItem newFile;
	private JMenuItem newFolder;
	private JMenuItem delete;
	private JMenuItem sync;
	
	public FileMenu() {
		super("File");
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
