import javax.swing.*;
import javax.swing.tree.*;

import java.awt.*;

import java.io.*;

/**
 * @author Emad
 *
 */
public class Frame extends JFrame {
	
	private Image icon = new ImageIcon("img/icon.png").getImage();
	private JPanel content = new JPanel();
	private JPanel header = new JPanel();
	private JMenuBar menuBar = new Menu();
	private JToolBar toolBar = new Toolbar();
	
	
	private JTree tree = new JTree();
	
	
	
	private JPanel filePanel = new FilePanel();
	private JScrollPane treePane;
//	= new TreePane(tree);
	
	//List View
	private JScrollPane filePane = new FilePane(new FileTable(new FileTableModel()));
	
	//Grid view
//	private JScrollPane filePane = new FilePane(filePanel);
	private JSplitPane split;
//	new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePane, filePane);
	private JPanel footer = new Footer();
	
	public Frame() {
		super();
		this.setIconImage(icon);
		this.setTitle("JFileManger");
		this.setSize(1024, 720);
		this.setMinimumSize(new Dimension(600, 400));
		this.setLocation(100, 150);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		header.setLayout(new BorderLayout());
		header.add(menuBar, BorderLayout.NORTH);
		header.add(toolBar, BorderLayout.SOUTH);
		
		
		content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(header, BorderLayout.NORTH);
		
	
		tree = new FileExplorerTree().getTree();
		treePane = new TreePane(tree);
//		filePanel.add(new FileTable().getTable());
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePane, filePane);
		content.add(split, BorderLayout.CENTER);
		
		
		content.add(footer, BorderLayout.SOUTH);
		
		
		this.setContentPane(content);
		
		
		this.setVisible(true);
	}
	
}
