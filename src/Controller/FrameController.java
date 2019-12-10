package Controller;

import java.awt.Desktop;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import View.ContextMenuFileView;
import View.ContextMenuPanelView;
import View.FileMenuView;
import View.FileTableView;
import View.FrameView;

/**
 * 
 * @author Emad
 *
 */
public class FrameController {

	private FrameView view;

	// Controllers
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
		backButtonController.putInputMap(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, KeyEvent.ALT_MASK), key);
		backButtonController.putActionMap(goBack, key);

		key = "Forward";
		Action goForward = goForward();
		forwardButtonController.setAction(goForward);
		forwardButtonController.putInputMap(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.ALT_MASK), key);
		forwardButtonController.putActionMap(goForward, key);

		key = "Parent";
		Action goParent = goParent();
		parentButtonController.setAction(goParent);
		parentButtonController.putInputMap(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), key);
		parentButtonController.putActionMap(goParent, key);

		setFileMenuActions(view.getMenuBarView().getFileView());
		setPanMenuActions(filePanelController.getView().getrClickMenuView());
		addLabelsListener(filePanelController.getView().getFileLabelsController());

		view.getFileTableView().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File f = null;
				try {
					f = view.getFileTableModel().getCurrentFiles()[0];
				} catch (Exception exp) {

				}
//				if (e.isPopupTrigger()) {
//					System.out.println("Whyyyyyy");
//					try {
//						view.getFileTableView().setRClickMenu(new ContextMenuFileView(f));
//						view.getFileTableView().getrClickMenu().show(e.getComponent(), e.getX(), e.getY());
//						new OpenFile(f).execute();
//					} catch (Exception exp) {
//						
//					}
//				}
				if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					try {
						new Open(f).execute();
					} catch (Exception exp) {

					}
				}
			}

			public void mouseReleased(MouseEvent e) {
				File f = null;
				try {
					f = view.getFileTableModel().getCurrentFiles()[0];
				} catch (Exception exp) {

				}
				if (e.isPopupTrigger()) {
					int w = view.getFileTableView().getWidth();
					int h = view.getFileTableView().getRowCount() * view.getFileTableView().getRowHeight();
					if (e.getX() > w || h < e.getY()) {
						view.getFileTableView().setRClickMenu(new ContextMenuPanelView());
						ContextMenuPanelView menu = view.getFileTableView().getPanelRClickMenu();
						menu.show(e.getComponent(), e.getX(), e.getY());
						setPanMenuActions(menu);

					} else {
						try {
							view.getFileTableView().setRClickMenu(new ContextMenuFileView(f));
							ContextMenuFileView menu = view.getFileTableView().getFileRClickMenu();
							menu.show(e.getComponent(), e.getX(), e.getY());
							setConMenuActions(menu);

						} catch (Exception exp) {
						}
					}
				}
			}
		});

		view.getFileTableView().addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent k) {
				if (k.getKeyCode() == KeyEvent.VK_ENTER) {
					File f = view.getFileTableModel().getCurrentFiles()[0];
					new Open(f).execute();
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
					new Open(ff).execute();

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
					setPanMenuActions(filePanelController.getView().getrClickMenuView());
					setFileMenuActions(view.getMenuBarView().getFileView());
					addLabelsListener(filePanelController.getView().getFileLabelsController());
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
					setPanMenuActions(filePanelController.getView().getrClickMenuView());
					setFileMenuActions(view.getMenuBarView().getFileView());
					addLabelsListener(filePanelController.getView().getFileLabelsController());
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
				if (f != null) {
					addressBarController.setPath(f.getPath());
					if (f.listFiles() != null) {
						view.getFileTableModel().setTableData(f.listFiles());
						filePanelController.setFolder(f);
						setPanMenuActions(filePanelController.getView().getrClickMenuView());
						setFileMenuActions(view.getMenuBarView().getFileView());
						addLabelsListener(filePanelController.getView().getFileLabelsController());
					}
					hasParent(f);
				} else {
					parentButtonController.setEnable(false);
				}
			}
		};
		return a;
	}

//	private Action open(File f) {
//		Action a = new AbstractAction() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (f.exists() && f.isDirectory()) {
//					backButtonController.addMemento(view.getAddressBarModel().getPath());
//					addressBarController.setPath(f.getPath());
//					view.getFileTableModel().setTableData(f.listFiles());
//					filePanelController.setFolder(f);
//					addOpenListener(filePanelController.getView().getFileLabelsController());
//				} else {
//					try {
//						desktop.open(f);
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
//				}
//				hasParent(f);
//			}
//		};
//		return a;
//	}

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
					addLabelsListener(filePanelController.getView().getFileLabelsController());
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

	private void addLabelsListener(ArrayList<FileLabelController> flcs) {
		for (FileLabelController flc : flcs) {
			File f = flc.getModel().getFile();
			File[] fs = new File[1];
			fs[0] = flc.getModel().getFile();
			flc.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
						new Open(f).execute();
					}
				}

				public void mouseReleased(MouseEvent e) {
					if (e.isPopupTrigger()) {
						flc.getView().getRightClickMenu().getOpen().addActionListener(new AbstractAction() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								new Open(f).execute();
							}
						});

						flc.getView().getRightClickMenu().getRename().addActionListener(new AbstractAction() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								new Rename(f).execute();
							}
						});

						flc.getView().getRightClickMenu().getDelete().addActionListener(new AbstractAction() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								new Delete(fs).execute();
							}
						});
					}
				}
			});

			flc.getView().addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent arg0) {
				}

				@Override
				public void keyReleased(KeyEvent arg0) {
				}

				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_DELETE) {
						new Delete(fs).execute();
					}
				}
			});
		}
	}

	private void setConMenuActions(ContextMenuFileView menu) {
		menu.getOpen().addActionListener(new AbstractAction() {
			File f = view.getFileTableModel().getCurrentFiles()[0];

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Open(f).execute();
			}
		});

		menu.getRename().addActionListener(new AbstractAction() {
			File f = view.getFileTableModel().getCurrentFiles()[0];

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Rename(f).execute();
			}
		});

		menu.getDelete().addActionListener(new AbstractAction() {
			File[] f = view.getFileTableModel().getCurrentFiles();

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Delete(f).execute();
			}
		});

	}

	private void setPanMenuActions(ContextMenuPanelView menu) {
		String path = addressBarController.getModel().getPath();
		menu.getNewFile().addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new NewFile(path).execute();
			}
		});
		
		menu.getNewFolder().addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new newFolder(path).execute();
			}
		});
	}
	
	private void setFileMenuActions(FileMenuView menu) {
		String path = addressBarController.getModel().getPath();
		menu.getNewFile().addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new NewFile(path).execute();
			}
		});
		
		menu.getNewFolder().addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new newFolder(path).execute();
			}
		});

	}

	class Open implements Command {

		File f;

		public Open(File f) {
			this.f = f;
		}

		public void execute() {
			if (f.exists() && f.isDirectory()) {
				backButtonController.addMemento(view.getAddressBarModel().getPath());
				addressBarController.setPath(f.getPath());
				view.getFileTableModel().setTableData(f.listFiles());
				filePanelController.setFolder(f);
				setPanMenuActions(filePanelController.getView().getrClickMenuView());
				setFileMenuActions(view.getMenuBarView().getFileView());
				addLabelsListener(filePanelController.getView().getFileLabelsController());
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

	class Rename implements Command {
		File f;

		public Rename(File f) {
			this.f = f;
		}

		public void execute() {
			String newName;
			newName = JOptionPane.showInputDialog("Enter file name: ");
			File f2 = new File(newName);
			f.renameTo(f2);
			view.repaint();
		}
	}

	class Delete implements Command {
		File[] f;

		public Delete(File[] f) {
			this.f = f;
		}

		public void execute() {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this file?", "",
					JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION) {
				File path = f[0].getParentFile();
				for (File file : f) {
					file.delete();
				}
				new Open(path).execute();
			}
		}
	}
	
	class NewFile implements Command {
		
		String path;
		
		public NewFile(String path) {
			this.path = path;
		}
		
		public void execute() {
			path += "\\";
			String fileName;
			fileName = JOptionPane.showInputDialog("Enter file name: ");
			if (fileName != null) {
				path += fileName;
				File file = new File(path);
				try {
					file.createNewFile();
					new Open(file.getParentFile()).execute();
				} catch (IOException e) {
				}
			}
		}
	}
	
	class newFolder implements Command {
		String path;
		
		public newFolder(String path) {
			this.path = path;
		}
		
		public void execute() {
			path += "\\";
			String fileName;
			fileName = JOptionPane.showInputDialog("Enter file name: ", JOptionPane.OK_OPTION);
			if (fileName != null) {
				path += fileName;
				File file = new File(path);			
				file.mkdir();
				new Open(file.getParentFile()).execute();
			}
		}
	}

}
