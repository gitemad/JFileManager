import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * @author Emad
 *
 */
public class ContextMenuFile extends JPopupMenu {
	
	private JMenuItem open;
	private JMenuItem rename;
	private JMenuItem copy;
	private JMenuItem cut;
	private JMenuItem delete;
	private JMenuItem properties;
	
	
	public ContextMenuFile(File file) {
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
		
		properties.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent eve) {
				new Properties(file);
			}
		});
		
		add(open);
		add(rename);
		add(copy);
		add(cut);
		add(delete);
		add(properties);
	}
}
