package Controller;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import View.ContextMenuFileView;
import View.ContextMenuPanelView;
import View.EditMenuView;
import View.FileMenuView;
import View.FrameView;
import View.PropertiesView;

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
	private ArrayList<File> found;
	private File[] clipboard;
	private boolean cut;

	public FrameController(FrameView view) {
		this.view = view;

		this.desktop = Desktop.getDesktop();
		this.found = new ArrayList<File>();
		clipboard = null;

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
		setEditMenuActions(view.getMenuBarView().getEditView());
		setPanMenuActions(filePanelController.getView().getrClickMenuView());
		setPanMenuActions(view.getFilePane().getrClickMenu());
		addLabelsListener(filePanelController.getView().getFileLabelsController());

		view.getFileTableView().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File f = null;
				try {
					f = view.getFileTableModel().getCurrentFiles()[0];
				} catch (Exception exp) {

				}
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

		view.getFilePane().getViewport().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					view.getFilePane().setrClickMenu(new ContextMenuPanelView());
					ContextMenuPanelView menu = view.getFilePane().getrClickMenu();
					menu.show(e.getComponent(), e.getX(), e.getY());
					setPanMenuActions(menu);

				}
			}
		});

		view.getToolBarView().getSearchView().addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyPressed(KeyEvent k) {
				if (k.getKeyCode() == KeyEvent.VK_ENTER) {
					found.removeAll(found);
					if (view.getToolBarView().getSearchView().getText().equals("")) {
						new Open(new File(addressBarController.getView().getText())).execute();
					} else {
						File f = new File(addressBarController.getView().getText());
						search(f, view.getToolBarView().getSearchView().getText());
						File[] arr = new File[found.size()];
						arr = found.toArray(arr);
						view.getFileTableModel().setTableData(arr);
					}

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
					if (!f.exists()) {
						new JOptionPane().showMessageDialog(null, "Address is not correct!");
					}
					if (f.exists() && f.isDirectory()) {
						if (!(f.getPath().equals(view.getAddressBarModel().getPath()))) {
							backButtonController.addMemento(view.getAddressBarModel().getPath());
						}
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
					setEditMenuActions(view.getMenuBarView().getEditView());
					setPanMenuActions(view.getFilePanelController().getView().getrClickMenuView());
					setPanMenuActions(view.getFilePane().getrClickMenu());
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
					setEditMenuActions(view.getMenuBarView().getEditView());
					setPanMenuActions(view.getFilePanelController().getView().getrClickMenuView());
					setPanMenuActions(view.getFilePane().getrClickMenu());
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
				backButtonController.addMemento(view.getAddressBarModel().getPath());
				f = f.getParentFile();
				if (f != null) {
					addressBarController.setPath(f.getPath());
					if (f.listFiles() != null) {
						view.getFileTableModel().setTableData(f.listFiles());
						filePanelController.setFolder(f);
						setPanMenuActions(filePanelController.getView().getrClickMenuView());
						setFileMenuActions(view.getMenuBarView().getFileView());
						setEditMenuActions(view.getMenuBarView().getEditView());
						setPanMenuActions(view.getFilePanelController().getView().getrClickMenuView());
						setPanMenuActions(view.getFilePane().getrClickMenu());
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

	private TreeSelectionListener treeSelection() {
		TreeSelectionListener t = new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent tse) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tse.getPath().getLastPathComponent();
				view.getTreeModel().setCurrentNode((File) node.getUserObject());
				treeController.addChildren(node);
				new Open(view.getTreeModel().getCurrentNode()).execute();
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
			fs[0] = f;

			try {
				flc.getView().removeMouseListener(flc.getView().getMouseListeners()[3]);
			} catch (Exception e) {

			}

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

						flc.getView().getRightClickMenu().getCopy().addActionListener(new AbstractAction() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								new Copy(fs).execute();
							}
						});

						flc.getView().getRightClickMenu().getCut().addActionListener(new AbstractAction() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								new Cut(fs).execute();
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

			try {
				flc.getView().removeKeyListener(flc.getView().getKeyListeners()[0]);
			} catch (Exception e) {

			}

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
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						new Open(f).execute();
					}
				}
			});
		}
	}

	private void setConMenuActions(ContextMenuFileView menu) {
		final File[] f;
		if (view.getFooterView().getGridController().isSelected()) {
			File[] tmp = new File[filePanelController.getModel().getCurrentFiles().size()];
			f = filePanelController.getModel().getCurrentFiles().toArray(tmp);
		} else {
			f = view.getFileTableModel().getCurrentFiles();
		}
		if (f.length > 1) {
			menu.getOpen().setVisible(false);
			menu.getRename().setVisible(false);
		}

		try {
			menu.getOpen().removeActionListener(menu.getOpen().getActionListeners()[0]);
		} catch (Exception e) {

		}
		menu.getOpen().addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Open(f[0]).execute();
			}
		});

		try {
			menu.getCopy().removeActionListener(menu.getCopy().getActionListeners()[0]);
		} catch (Exception e) {

		}

		menu.getCopy().addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Copy(f).execute();
			}
		});

		try {
			menu.getCut().removeActionListener(menu.getCut().getActionListeners()[0]);
		} catch (Exception e) {

		}

		menu.getCut().addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Cut(f).execute();
			}
		});

		try {
			menu.getRename().removeActionListener(menu.getRename().getActionListeners()[0]);
		} catch (Exception e) {

		}

		menu.getRename().addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Rename(f[0]).execute();
			}
		});

		try {
			menu.getDelete().removeActionListener(menu.getDelete().getActionListeners()[0]);
		} catch (Exception e) {

		}

		menu.getDelete().addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Delete(f).execute();
			}
		});

		try {
			menu.getProperties().removeActionListener(menu.getProperties().getActionListeners()[0]);
		} catch (Exception e) {

		}

		menu.getProperties().addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Properties(f).execute();
			}
		});

	}

	private void setPanMenuActions(ContextMenuPanelView menu) {
		String path = addressBarController.getModel().getPath();

		if (clipboard == null) {
			menu.getPaste().setEnabled(false);
		} else {
			menu.getPaste().setEnabled(true);
		}

		try {
			menu.getNewFile().removeActionListener(menu.getNewFile().getActionListeners()[0]);
		} catch (Exception e) {

		}

		menu.getNewFile().addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new NewFile(path).execute();
			}
		});

		try {
			menu.getNewFolder().removeActionListener(menu.getNewFolder().getActionListeners()[0]);
		} catch (Exception e) {

		}

		menu.getNewFolder().addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new NewFolder(path).execute();
			}
		});

		try {
			menu.getPaste().removeActionListener(menu.getPaste().getActionListeners()[0]);
		} catch (Exception e) {

		}

		menu.getPaste().addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Paste(path).execute();
			}
		});

		try {
			menu.getProperties().removeActionListener(menu.getProperties().getActionListeners()[0]);
		} catch (Exception e) {

		}

		menu.getProperties().addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Properties(path).execute();
			}
		});
	}

	private void setFileMenuActions(FileMenuView menu) {
		String path = addressBarController.getModel().getPath();

		try {
			menu.getNewFile().removeActionListener(menu.getNewFile().getActionListeners()[0]);
		} catch (Exception e) {

		}

		menu.getNewFile().addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new NewFile(path).execute();
			}
		});

		try {
			menu.getNewFolder().removeActionListener(menu.getNewFolder().getActionListeners()[0]);
		} catch (Exception e) {

		}

		menu.getNewFolder().addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new NewFolder(path).execute();
			}
		});

		try {
			menu.getDelete().removeActionListener(menu.getDelete().getActionListeners()[0]);
		} catch (Exception e) {

		}

		menu.getDelete().addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent arg0) {
				File[] files;
				if (view.getFooterView().getGridController().isSelected()) {
					File[] temp = new File[filePanelController.getModel().getCurrentFiles().size()];
					files = filePanelController.getModel().getCurrentFiles().toArray(temp);
				} else if (view.getFooterView().getListController().isSelected()) {
					files = view.getFileTableModel().getCurrentFiles();
					if (files != null) {
						new Delete(files).execute();
					}
				}
			}
		});

	}

	private void setEditMenuActions(EditMenuView menu) {
		String path = addressBarController.getModel().getPath();

		if (clipboard == null) {
			menu.getPaste().setEnabled(false);
		} else {
			menu.getPaste().setEnabled(true);
		}

		final File[] f;
		if (view.getFooterView().getGridController().isSelected()) {
			File[] tmp = new File[filePanelController.getModel().getCurrentFiles().size()];
			f = filePanelController.getModel().getCurrentFiles().toArray(tmp);
		} else {
			f = view.getFileTableModel().getCurrentFiles();
		}

		try {
			menu.getRename().removeActionListener(menu.getRename().getActionListeners()[0]);
		} catch (Exception e) {

		}

		menu.getRename().addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Rename(f[0]).execute();
				} catch (Exception e) {
				}
			}
		});

		try {
			menu.getCopy().removeActionListener(menu.getCopy().getActionListeners()[0]);
		} catch (Exception e) {

		}

		menu.getCopy().addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Copy(f).execute();
			}
		});

		try {
			menu.getCut().removeActionListener(menu.getCut().getActionListeners()[0]);
		} catch (Exception e) {

		}

		menu.getCut().addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Cut(f).execute();
			}
		});

		try {
			menu.getPaste().removeActionListener(menu.getPaste().getActionListeners()[0]);
		} catch (Exception e) {

		}

		menu.getPaste().addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Paste(path).execute();
			}
		});
	}

	private void search(File path, String text) {
		view.getFooterView().getGridController().setSelected(false);
		view.getFooterView().getListController().setSelected(true);
		view.listView();
		for (File file : path.listFiles()) {
			String fileName = file.getName().toLowerCase();
			text = text.toLowerCase();
			if (fileName.contains(text)) {
				found.add(file);
			}
			if (file.isDirectory()) {
				search(file, text);
			}
		}
	}

	class Open implements Command {

		File f;

		public Open(File f) {
			this.f = f;
		}

		public void execute() {
			if (!f.exists()) {
				new JOptionPane().showMessageDialog(null, "Address is not correct!");
			}
			if (f.exists() && f.isDirectory()) {
				if (!(f.getPath().equals(view.getAddressBarModel().getPath()))) {
					backButtonController.addMemento(view.getAddressBarModel().getPath());
				}
				addressBarController.setPath(f.getPath());
				view.getFileTableModel().setTableData(f.listFiles());
				filePanelController.setFolder(f);
				setPanMenuActions(filePanelController.getView().getrClickMenuView());
				setFileMenuActions(view.getMenuBarView().getFileView());
				setEditMenuActions(view.getMenuBarView().getEditView());
				setPanMenuActions(view.getFilePane().getrClickMenu());
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
			FileInputStream is = null;
			FileOutputStream os = null;
			JTextField newName;
			int option;
			File f2;
			do {
				JPanel panel = new JPanel(new GridLayout(1, 1));
				newName = new JTextField();
				panel.add(newName);
				option = JOptionPane.showConfirmDialog(null, panel, "Enter new name for file",
						JOptionPane.OK_CANCEL_OPTION);
				if (option != JOptionPane.OK_OPTION) {
					return;
				}
				f2 = new File(f.getParent() + "\\" + newName.getText());
			} while (f2.exists());
			f.renameTo(f2);
			new Open(new File(addressBarController.getModel().getPath())).execute();
		}
	}

	class Delete implements Command {
		File[] f;

		public Delete(File[] f) {
			this.f = f;
		}

		public void execute() {
			if (cut) {
				for (File file : f) {
					file.delete();
				}
				cut = false;
				return;
			}
			if (view.getFooterView().getGridController().isSelected()) {
				File[] tmp = new File[filePanelController.getModel().getCurrentFiles().size()];
				f = filePanelController.getModel().getCurrentFiles().toArray(tmp);
			}
			int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this file?", "",
					JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION) {
				File path = f[0].getParentFile();
				for (File file : f) {
					delete(file);
				}
				new Open(path).execute();
			}
		}

		private void delete(File f) {
			if (f.isDirectory()) {
				for (File file : f.listFiles()) {
					delete(file);
				}
			}
			f.delete();
		}
	}

	class Copy implements Command {

		File[] sources;

		public Copy(File[] sources) {
			this.sources = sources;
		}

		public void execute() {
			if (view.getFooterView().getGridController().isSelected()) {
				File[] tmp = new File[filePanelController.getModel().getCurrentFiles().size()];
				sources = filePanelController.getModel().getCurrentFiles().toArray(tmp);
			}
			clipboard = sources;
			cut = false;
			setEditMenuActions(view.getMenuBarView().getEditView());
			setPanMenuActions(filePanelController.getView().getrClickMenuView());
			setPanMenuActions(view.getFilePane().getrClickMenu());
		}
	}

	class Cut implements Command {

		File[] sources;

		public Cut(File[] sources) {
			this.sources = sources;
		}

		public void execute() {
			if (view.getFooterView().getGridController().isSelected()) {
				File[] tmp = new File[filePanelController.getModel().getCurrentFiles().size()];
				sources = filePanelController.getModel().getCurrentFiles().toArray(tmp);
			}
			clipboard = sources;
			cut = true;
			setEditMenuActions(view.getMenuBarView().getEditView());
			setPanMenuActions(filePanelController.getView().getrClickMenuView());
			setPanMenuActions(view.getFilePane().getrClickMenu());
		}
	}

	class Paste implements Command {

		String destPath;

		public Paste(String destPath) {
			this.destPath = destPath;
		}

		public void execute() {
			if (clipboard == null) {
				return;
			}
			for (File source : clipboard) {
				try {
					paste(source, destPath);
				} catch (IOException e) {
				}

//				try {
//					FileInputStream is = new FileInputStream(source);
//					String fileName = source.getName();
//					destPath += "\\" + fileName;
//					while (new File(destPath).isDirectory()) {
//						destPath += "_copy";
//					}
//					while (new File(destPath).exists()) {
//						destPath = destPath.replace(fileName, "");
//						int i = fileName.lastIndexOf(".");
//						if (i > 0) {
//							String extension = "";
//							extension = fileName.substring(i);
//							fileName = fileName.replace(extension, "");
//							fileName += "_copy";
//							fileName += extension;
//							destPath += fileName;
//						}
//					}
//					File dest = new File(destPath);
//					FileOutputStream os = new FileOutputStream(dest);
//					byte[] buffer = new byte[1024];
//					int length;
//					while ((length = is.read(buffer)) > 0) {
//						os.write(buffer, 0, length);
//					}
//					is.close();
//					os.close();
//				} catch (Exception e) {
//				}

			}
			if (cut) {
				new Delete(clipboard).execute();
			}
			new Open(new File(addressBarController.getModel().getPath())).execute();
		}

		public void paste(File src, String destAdrs) throws IOException {
			String fileName = src.getName();
			destPath += "\\" + fileName;
			
			if (src.isDirectory()) {
				while (new File(destPath).isDirectory()) {
					destPath += "_copy";
				}
				File dest = new File(destPath);
				if (!dest.exists()) {
					dest.mkdir();
					destPath = dest.getPath();
				}
				File[] files = src.listFiles();

				for (File file : files) {
					File srcFile = new File(file.getPath());
					File destFile = new File(destPath + "\\" + file.getName());
					if (file.isDirectory()) {
						paste(srcFile, destFile.getPath());
						destPath = destPath.replace(file.getName(), "");
					}
				}
				
				for (File file : files) {
					File srcFile = new File(file.getPath());
					File destFile = new File(destPath + "\\" + file.getName());
					if (!file.isDirectory())
						paste(srcFile, destFile.getPath());
				}
				
				
			} else {
				
				while (new File(destPath).exists()) {
					destPath = destPath.replace(fileName, "");
					int i = fileName.lastIndexOf(".");
					if (i > 0) {
						String extension = "";
						extension = fileName.substring(i);
						fileName = fileName.replace(extension, "");
						fileName += "_copy";
						fileName += extension;
						destPath += fileName;
					}
				}
				File dest = new File(destPath);
				InputStream in = new FileInputStream(src);
				OutputStream out = new FileOutputStream(dest);
	
				byte[] buffer = new byte[1024];
	
				int length;
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
	
				in.close();
				out.close();
				destPath = destPath.replace("\\" + fileName, "");
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
				while (new File(path).exists()) {
					path = path.replace(fileName, "");
					int i = fileName.lastIndexOf(".");
					if (i > 0) {
						String extension = "";
						extension = fileName.substring(i);
						fileName = fileName.replace(extension, "");
						fileName += "_copy";
						fileName += extension;
						path += fileName;
					}
				}
				File file = new File(path);
				try {
					file.createNewFile();
					new Open(file.getParentFile()).execute();
				} catch (IOException e) {
				}
			}
		}
	}

	class NewFolder implements Command {
		String path;

		public NewFolder(String path) {
			this.path = path;
		}

		public void execute() {
			path += "\\";
			String fileName;
			fileName = JOptionPane.showInputDialog("Enter folder name: ", JOptionPane.OK_OPTION);
			if (fileName != null) {
				path += fileName;
				while (new File(path).isDirectory()) {
					path += "_copy";
				}
				File file = new File(path);
				file.mkdir();
				new Open(file.getParentFile()).execute();
			}
		}
	}

	class Properties implements Command {

		String path;
		File[] files;

		public Properties(String path) {
			this.path = path;
			this.files = new File[1];
			this.files[0] = new File(path);
		}

		public Properties(File[] files) {
			this.files = files;
		}

		public void execute() {
			if (files.length == 1) {
				new PropertiesView(files[0]);
			} else if (files.length > 1) {
				new PropertiesView(files);
			}
		}
	}

}
