package Model;
import java.awt.*;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.tree.*;

public class FileTreeModel {

	private FileSystemView fileSystemView;
	private JTree tree;

	/**
	 * Only constructor of class without parameter requirement
	 */
	public FileTreeModel() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		fileSystemView = FileSystemView.getFileSystemView();
		File[] roots = fileSystemView.getRoots();
		for (File fileSystemRoot : roots) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot);
			root.add(node);
			File[] files = fileSystemView.getFiles(fileSystemRoot, true);
			for (File file : files) {
				node.add(new DefaultMutableTreeNode(file));
			}
		}

		tree = new JTree(root);
		tree.setRootVisible(false);
		tree.setCellRenderer(new FileTreeCellRenderer());
		tree.expandRow(0);
	}
	
	public JTree getTree() {
		return tree;
	}

	
}


class FileTreeCellRenderer extends DefaultTreeCellRenderer {

    private FileSystemView fileSystemView;
    private JLabel label;

    /**
     * Only constructor of class without any parameter requirement
     */
    public FileTreeCellRenderer() {
        label = new JLabel();
        label.setOpaque(true);
        fileSystemView = FileSystemView.getFileSystemView();
    }

    @Override
    public Component getTreeCellRendererComponent(
        JTree tree,
        Object value,
        boolean selected,
        boolean expanded,
        boolean leaf,
        int row,
        boolean hasFocus) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
        File file = (File)node.getUserObject();
        label.setIcon(fileSystemView.getSystemIcon(file));
        label.setText(fileSystemView.getSystemDisplayName(file));
        label.setToolTipText(file.getPath());
        if (selected) {
            label.setBackground(backgroundSelectionColor);
        } else {
            label.setBackground(backgroundNonSelectionColor);
        }
        return label;
    }
}