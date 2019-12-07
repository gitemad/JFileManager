import javax.swing.*;
import javax.swing.tree.*;

import Controller.FileTreeController;
import Model.*;
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
	
	//Model
	private FileTreeModel treeModel;

	
	//View
	private ToolBarView toolBarView;
	private FooterView footerView;
	private FileTreeView treeView;
	
	//Controller
	private FileTreeController treeController;
	
	
	private Image icon;
	private JPanel content;
	private JPanel header;
	private JMenuBar menuBar;
	private JScrollPane filePane;
	private JSplitPane split;
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
		menuBar = new MenuView();
		toolBarView = new ToolBarView();
		filePane = new FilePane(new FileTable(new FileTableModel()));
		treeModel = new FileTreeModel();
		footerView = new FooterView();
		tray = new TrayIconJFM(this).getTray();
		
		
		
		footerView.addListListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent eve) {
				footerView.getListController().setSelected(true);
				footerView.getGridController().setSelected(false);
				listView();
			}
		});
		
		footerView.addGridListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent eve) {
				footerView.getGridController().setSelected(true);
				footerView.getListController().setSelected(false);
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
	
		treeModel = new FileTreeModel();
		treeView = new FileTreeView(treeModel.getTree());
		treeController = new FileTreeController(treeModel, treeView);
		filePane = new FilePane(new FileTable(new FileTableModel()));				
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeView, filePane);
		
		content.add(split, BorderLayout.CENTER);		
		content.add(footerView, BorderLayout.SOUTH);
		
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
			
		treeModel = new FileTreeModel();
		treeView = new FileTreeView(treeModel.getTree());
		treeController = new FileTreeController(treeModel, treeView);

		filePane = new FilePane(new FilePanel());
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeView, filePane);
		content.add(split, BorderLayout.CENTER);
		
		content.add(footerView, BorderLayout.SOUTH);
		
		this.setContentPane(content);
		
		this.setVisible(true);
	}
	
}
