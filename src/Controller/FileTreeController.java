package Controller;

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
	 * @param model
	 * @param view
	 */
	public FileTreeController(FileTreeModel model, FileTreeView view) {
		this.model = model;
		this.view = view;
		
		TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent tse) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tse.getPath().getLastPathComponent();
				addChildren(node);
			}
		};

		model.getTree().addTreeSelectionListener(treeSelectionListener);
		
	}
	
	
	private void addChildren(final DefaultMutableTreeNode node) {
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
