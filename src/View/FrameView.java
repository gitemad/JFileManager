package View;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import Controller.*;
import Model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

/**
 * @author Emad
 *
 */
public class FrameView extends JFrame {

	// Model
	private FileTreeModel treeModel;
	private FileTableModel fileTableModel;
	private FilePanelModel filePanelModel;
	private AddressBarModel addressBarModel;

	// View
	private ToolBarView toolBarView;
	private FooterView footerView;
	private FileTreeView treeView;
	private FilePanelView filePanelView;
	private MenuView menuBarView;
	private FilePaneView filePane;
	private FileTableView fileTableView;

	// Controller
	private FileTreeController treeController;
	private AddressBarController addressBarController;
	private FilePanelController filePanelController;
	private NavigateButtonController backButtonController;
	private NavigateButtonController forwardButtonController;
	private NavigateButtonController parentButtonController;

	private Image icon;
	private JPanel content;
	private JPanel header;
	private JSplitPane split;
	private SystemTray tray;

	/**
	 * Only constructor of class without any parameter requirement
	 */
	public FrameView() {
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
		menuBarView = new MenuView();
		toolBarView = new ToolBarView();
		footerView = new FooterView();
		tray = new TrayIconJFM(this).getTray();

		fileTableModel = new FileTableModel();
		fileTableView = new FileTableView(fileTableModel);

		treeModel = new FileTreeModel();
		treeView = new FileTreeView(treeModel.getTree());
		treeController = new FileTreeController(treeModel, treeView);

		addressBarController = toolBarView.getAddressBarController();
		addressBarModel = addressBarController.getModel();

		backButtonController = toolBarView.getBackController();
		forwardButtonController = toolBarView.getForwardController();
		parentButtonController = toolBarView.getParentController();

		filePanelModel = new FilePanelModel(new File(addressBarModel.getPath()));
		filePanelView = new FilePanelView(filePanelModel);
		filePanelController = new FilePanelController(filePanelModel, filePanelView);

		addressBarController.addKeyListener(new KeyListener() {
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
						backButtonController.addMemento(addressBarModel.getPath());
						addressBarController.setPath(f.getPath());
					} else {
						addressBarController.getView().setText(addressBarModel.getPath());
					}
					File ff = new File(addressBarModel.getPath());
					fileTableModel.setTableData(ff.listFiles());
					filePanelController.setFolder(ff);
					hasParent(ff);
				}
			}
		});

		backButtonController.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				forwardButtonController.addMemento(addressBarModel.getPath());
				addressBarController.setPath(backButtonController.restoreMemento());
				File f = new File(addressBarModel.getPath());
				fileTableModel.setTableData(f.listFiles());
				filePanelController.setFolder(f);
				hasParent(f);
			}
		});

		forwardButtonController.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backButtonController.addMemento(addressBarModel.getPath());
				addressBarController.setPath(forwardButtonController.restoreMemento());
				File f = new File(addressBarModel.getPath());
				fileTableModel.setTableData(f.listFiles());
				filePanelController.setFolder(f);
				hasParent(f);
			}
		});
		
		parentButtonController.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File f = new File(addressBarModel.getPath());
				f = f.getParentFile();
				addressBarController.setPath(f.getPath());
				fileTableModel.setTableData(f.listFiles());
				filePanelController.setFolder(f);
				hasParent(f);
			}
		});

		treeController.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent tse) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tse.getPath().getLastPathComponent();
				treeModel.setCurrentNode((File) node.getUserObject());
				treeController.addChildren(node);
				backButtonController.addMemento(addressBarModel.getPath());
				addressBarController.setPath(treeModel.getCurrentNode().getPath());
				fileTableModel.setTableData(treeModel.getCurrentNode().listFiles());
				filePanelController.setFolder(treeModel.getCurrentNode());
				hasParent(treeModel.getCurrentNode());
			}
		});

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
	
	

	// a function for list view
	private void listView() {
		reconstruct();

		filePane = new FilePaneView(fileTableView);
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeView, filePane);

		content.add(split, BorderLayout.CENTER);
		content.add(footerView, BorderLayout.SOUTH);

		this.setContentPane(content);

		this.setVisible(true);
	}

	// a function for grid view
	private void gridView() {
		reconstruct();

		filePane = new FilePaneView(filePanelView);
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeView, filePane);
		content.add(split, BorderLayout.CENTER);

		content.add(footerView, BorderLayout.SOUTH);

		this.setContentPane(content);

		this.setVisible(true);
	}

	private void reconstruct() {
		header.setLayout(new BorderLayout());
		header.add(menuBarView, BorderLayout.NORTH);
		header.add(toolBarView, BorderLayout.SOUTH);

		content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(header, BorderLayout.NORTH);

	}

	private void hasParent(File file) {
		if (file.getParentFile() != null) {
			parentButtonController.setEnable(true);
		} else {
			parentButtonController.setEnable(false);
		}
	}
}
