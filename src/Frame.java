import javax.swing.*;
import javax.swing.tree.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * @author Emad
 *
 */
public class Frame extends JFrame {
	
	private Image icon;
	private JPanel content;
	private JPanel header;
	private JMenuBar menuBar;
	private JToolBar toolBar;
	private JTree tree;
	private JScrollPane treePane;
//	= new TreePane(tree);
	
	//List View
	private JScrollPane filePane;
	
	//Grid view
//	private JScrollPane filePane = new FilePane(filePanel);
	private JSplitPane split;
//	new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePane, filePane);
	private Footer footer;
	
	public Frame() {
		super();
		icon = new ImageIcon("img/icon.png").getImage();
		this.setIconImage(icon);
		this.setTitle("JFileManger");
		this.setSize(1024, 720);
		this.setMinimumSize(new Dimension(600, 400));
		this.setLocation(100, 150);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		content = new JPanel();
		header = new JPanel();
		menuBar = new Menu();
		toolBar = new Toolbar();
		filePane = new FilePane(new FileTable(new FileTableModel()));
		tree = new JTree();
		footer = new Footer();
		
		footer.addListListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent eve) {
				footer.getList().setSelected(true);
				footer.getGrid().setSelected(false);
				listView();
			}
		});
		
		footer.addGridListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent eve) {
				footer.getGrid().setSelected(true);
				footer.getList().setSelected(false);
				gridView();
			}
		});
		
		listView();
	}
	
	
	private void listView() {
		header.setLayout(new BorderLayout());
		header.add(menuBar, BorderLayout.NORTH);
		header.add(toolBar, BorderLayout.SOUTH);
		
		content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(header, BorderLayout.NORTH);
	
		tree = new FileExplorerTree().getTree();
		treePane = new TreePane(tree);
		filePane = new FilePane(new FileTable(new FileTableModel()));				
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePane, filePane);
		
		content.add(split, BorderLayout.CENTER);		
		content.add(footer, BorderLayout.SOUTH);
		
		this.setContentPane(content);
	
		this.setVisible(true);
	}
	
	private void gridView() {
		header.setLayout(new BorderLayout());
		header.add(menuBar, BorderLayout.NORTH);
		header.add(toolBar, BorderLayout.SOUTH);
		
		content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(header, BorderLayout.NORTH);
			
		tree = new FileExplorerTree().getTree();
		treePane = new TreePane(tree);
		filePane = new FilePane(new FilePanel());
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePane, filePane);
		content.add(split, BorderLayout.CENTER);
		
		content.add(footer, BorderLayout.SOUTH);
		
		this.setContentPane(content);
		
		this.setVisible(true);
	}
	
}
