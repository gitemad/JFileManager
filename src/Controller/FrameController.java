package Controller;

import java.awt.Desktop;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import View.FrameView;
import javafx.scene.input.MouseButton;

/**
 * 
 * @author Emad
 *
 */
public class FrameController {
	
	private FrameView view;
	
	//Controllers
	private FileTreeController treeController;
	private AddressBarController addressBarController;
	private FilePanelController filePanelController;
	private NavigateButtonController backButtonController;
	private NavigateButtonController forwardButtonController;
	private NavigateButtonController parentButtonController;
	
	
	private Desktop desktop;
	
	public FrameController(FrameView view) {
		this.view = view;
		
		this.desktop = Desktop.getDesktop();
		
		treeController = this.view.getTreeController();
		addressBarController = this.view.getAddressBarController();
		filePanelController = this.view.getFilePanelController();
		backButtonController = this.view.getBackButtonController();
		forwardButtonController = this.view.getForwardButtonController();
		parentButtonController = this.view.getParentButtonController();
		
		addressBarController.addKeyListener(confirmAddress());		
		treeController.addTreeSelectionListener(treeSelection());
		
		String key = "Back";
		Action goBack = goBack();
		backButtonController.setAction(goBack);
		backButtonController.putInputMap( KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, KeyEvent.ALT_MASK), key);
		backButtonController.putActionMap(goBack, key);

		key = "Forward";
		Action goForward = goForward();
		forwardButtonController.setAction(goForward);
		forwardButtonController.putInputMap( KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.ALT_MASK), key);
		forwardButtonController.putActionMap(goForward, key);
		
		key = "Parent";
		Action goParent = goParent();
		parentButtonController.setAction(goParent);
		parentButtonController.putInputMap( KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), key);
		parentButtonController.putActionMap(goParent, key);

		
		view.getFileTableView().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File f = view.getFileTableModel().getCurrentFiles()[0];
				if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					if (f.exists() && f.isDirectory()) {
						backButtonController.addMemento(view.getAddressBarModel().getPath());
						addressBarController.setPath(f.getPath());
						view.getFileTableModel().setTableData(f.listFiles());
						filePanelController.setFolder(f);
					} else {
						try {
							desktop.open(f);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					hasParent(f);
				}
			}
		});
		
		
	}
	
	private KeyListener confirmAddress() {
		KeyListener k = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyPressed(KeyEvent k) {
				if (k.getKeyCode() == KeyEvent.VK_ENTER) {
					File f = new File(addressBarController.getView().getText());
					if (f.exists() && f.isDirectory()) {
						backButtonController.addMemento(view.getAddressBarModel().getPath());
						addressBarController.setPath(f.getPath());
					} else {
						addressBarController.getView().setText(view.getAddressBarModel().getPath());
					}
					File ff = new File(view.getAddressBarModel().getPath());
					view.getFileTableModel().setTableData(ff.listFiles());
					filePanelController.setFolder(ff);
					hasParent(ff);
				}
			}
		};
		return k;
	}
	
	private Action goBack() {
		Action a = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				forwardButtonController.addMemento(view.getAddressBarModel().getPath());
				addressBarController.setPath(backButtonController.restoreMemento());
				File f = new File(view.getAddressBarModel().getPath());
				if (f.listFiles() != null) {
					view.getFileTableModel().setTableData(f.listFiles());
					filePanelController.setFolder(f);
				}
				hasParent(f);
			}
		};
		return a;
	}
	
	private Action goForward() {
		Action a = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backButtonController.addMemento(view.getAddressBarModel().getPath());
				addressBarController.setPath(forwardButtonController.restoreMemento());
				File f = new File(view.getAddressBarModel().getPath());
				if (f.listFiles() != null) {
					view.getFileTableModel().setTableData(f.listFiles());
					filePanelController.setFolder(f);
				}
				hasParent(f);
			}
		};
		return a;
	}
	
	private Action goParent() {
		Action a = new AbstractAction() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				File f = new File(view.getAddressBarModel().getPath());
				f = f.getParentFile();
				addressBarController.setPath(f.getPath());
				if (f.listFiles() != null) {
					view.getFileTableModel().setTableData(f.listFiles());
					filePanelController.setFolder(f);
				}
				hasParent(f);
			}
		};
		return a;
	}

	private Action open(File f) {
		Action a = new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (f.exists() && f.isDirectory()) {
					backButtonController.addMemento(view.getAddressBarModel().getPath());
					addressBarController.setPath(f.getPath());
					view.getFileTableModel().setTableData(f.listFiles());
					filePanelController.setFolder(f);
				} else {
					try {
						desktop.open(f);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				hasParent(f);
			}
		};
		return a;
	}
	
	private TreeSelectionListener treeSelection() {
		TreeSelectionListener t = new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent tse) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tse.getPath().getLastPathComponent();
				view.getTreeModel().setCurrentNode((File) node.getUserObject());
				treeController.addChildren(node);
				backButtonController.addMemento(view.getAddressBarModel().getPath());
				addressBarController.setPath(view.getTreeModel().getCurrentNode().getPath());
				if (view.getTreeModel().getCurrentNode().listFiles() != null) {
					view.getFileTableModel().setTableData(view.getTreeModel().getCurrentNode().listFiles());
					filePanelController.setFolder(view.getTreeModel().getCurrentNode());
				}
				hasParent(view.getTreeModel().getCurrentNode());
			}
		};
		return t;
		
	}
	
	private void hasParent(File file) {
		if (file.getParentFile() != null) {
			parentButtonController.setEnable(true);
		} else {
			parentButtonController.setEnable(false);
		}
	}
}
