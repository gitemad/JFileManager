import javax.swing.*;
import javax.swing.tree.*;

import View.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * @author Emad
 *
 */
public class Frame extends JFrame {
	
	//View
	private ToolBarView toolBarView;
	
	
	private Image icon;
	private JPanel content;
	private JPanel header;
	private JMenuBar menuBar;
	private JTree tree;
	private JScrollPane treePane;
	private JScrollPane filePane;
	private JSplitPane split;
	private Footer footer;
	private SystemTray tray;
	
	public Frame() {
		super();
		icon = new ImageIcon("img/icon.png").getImage();
		this.setIconImage(icon);
		this.setTitle("JFileManger");
		this.setSize(1024, 720);
		this.setMinimumSize(new Dimension(600, 400));
		this.setLocation(100, 150);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		content = new JPanel();
		header = new JPanel();
		menuBar = new Menu();
		toolBarView = new ToolBarView();
		filePane = new FilePane(new FileTable(new FileTableModel()));
		tree = new JTree();
		footer = new Footer();
		tray = new TrayIconJFM(this).getTray();
		
		
		
		footer.addListListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent eve) {
				footer.getListController().setSelected(true);
				footer.getGridController().setSelected(false);
				listView();
			}
		});
		
		footer.addGridListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent eve) {
				footer.getGridController().setSelected(true);
				footer.getListController().setSelected(false);
				gridView();
			}
		});
		
		listView();
	}
	
	
	private void listView() {
		header.setLayout(new BorderLayout());
		header.add(menuBar, BorderLayout.NORTH);
		header.add(toolBarView, BorderLayout.SOUTH);
		
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
		header.add(toolBarView, BorderLayout.SOUTH);
		
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
