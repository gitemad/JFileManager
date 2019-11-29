import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import javax.swing.tree.*;

public class FileExplorerTree {

	private Desktop desktop;
	private FileSystemView fileSystemView;
	private JTree tree;
	private File node;

	public FileExplorerTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		fileSystemView = FileSystemView.getFileSystemView();
        desktop = Desktop.getDesktop();
		File[] roots = fileSystemView.getRoots();
		for (File fileSystemRoot : roots) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot);
			root.add(node);
			File[] files = fileSystemView.getFiles(fileSystemRoot, true);
			for (File file : files) {
				node.add(new DefaultMutableTreeNode(file));
			}
		}

		TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent tse) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tse.getPath().getLastPathComponent();
				addChildren(node);
			}
		};

		tree = new JTree(root);
		tree.setRootVisible(false);
		tree.addTreeSelectionListener(treeSelectionListener);
		tree.setCellRenderer(new FileTreeCellRenderer());
	}
	
	public JTree getTree() {
		return tree;
	}

	private void addChildren(final DefaultMutableTreeNode node) {
		SwingWorker worker = new SwingWorker() {
			@Override
			public String doInBackground() {
				tree.setEnabled(false);
				File file = (File) node.getUserObject();
				if (file.isDirectory()) {
					File[] files = fileSystemView.getFiles(file, true);
					if (node.isLeaf()) {
						for (File child : files) {
							node.add(new DefaultMutableTreeNode(child));
						}
					}
				}
				tree.setEnabled(true);
				return "done";
			}
		};
		worker.execute();
	}
}