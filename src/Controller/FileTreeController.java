package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import javax.swing.tree.*;

import Model.FileTreeModel;
import View.FileTreeView;

public class FileTreeController {
	
	private FileTreeModel model;
	private FileTreeView view;
	
	/**
	 * Only constructor of class with following parameter requirement
	 * @param model the file tree model
	 * @param view the file tree view
	 */
	public FileTreeController(FileTreeModel model, FileTreeView view) {
		this.model = model;
		this.view = view;
		
		model.getTree().addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				JTree jTree = model.getTree();
				if(jTree.isCollapsed(jTree.getRowForLocation(e.getX(),e.getY()))) {
					jTree.expandRow(jTree.getRowForLocation(e.getX(),e.getY()));
				}
				else {
					jTree.collapseRow(jTree.getRowForLocation(e.getX(),e.getY()));
				}				
			}
		});

//		TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {
//			@Override
//			public void valueChanged(TreeSelectionEvent tse) {
//				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tse.getPath().getLastPathComponent();
//				FileTreeController.this.model.setCurrentNode((File) node.getUserObject());
//				addChildren(node);
//			}
//		};

//		FileTreeController.this.model.getTree().addTreeSelectionListener(treeSelectionListener);
		
	}
	
	/**
	 * add tree selection listener to file tree
	 * @param tse tree selection listener to add 
	 */
	public void addTreeSelectionListener(TreeSelectionListener tse) {
		model.getTree().addTreeSelectionListener(tse);
	}
	
	/**
	 * add children of a node
	 * @param node the parent node
	 */
	public void addChildren(final DefaultMutableTreeNode node) {
		FileSystemView fileSystemView = FileSystemView.getFileSystemView();
		SwingWorker worker = new SwingWorker() {
			@Override
			public String doInBackground() {
				model.getTree().setEnabled(false);
				File file = (File) node.getUserObject();
				if (file.isDirectory()) {
					File[] files = fileSystemView.getFiles(file, true);
					if (node.isLeaf()) {
						for (File child : files) {
							node.add(new DefaultMutableTreeNode(child));
						}
					}
//					setTableDate(files);
				}
				model.getTree().setEnabled(true);
				return "done";
			}
		};
		worker.execute();
	}
	
}
