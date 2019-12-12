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

		treeModel = new FileTreeModel();
		treeView = new FileTreeView(treeModel.getTree());
		treeController = new FileTreeController(treeModel, treeView);

		addressBarController = toolBarView.getAddressBarController();
		addressBarModel = addressBarController.getModel();
		
		
		backButtonController = toolBarView.getBackController();
		forwardButtonController = toolBarView.getForwardController();
		parentButtonController = toolBarView.getParentController();
		
		fileTableModel = new FileTableModel(new File(addressBarModel.getPath()).listFiles());
		fileTableView = new FileTableView(fileTableModel);

		filePanelModel = new FilePanelModel(new File(addressBarModel.getPath()));
		filePanelView = new FilePanelView(filePanelModel);
		filePanelController = new FilePanelController(filePanelModel, filePanelView);
		

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
		
		try {
			UIManager.setLookAndFeel(menuBarView.getHelpController().getSettingsModel().getLookAndFeel());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
		}

		if (menuBarView.getHelpController().getSettingsModel().isList()) {
			listView();
			footerView.getListController().setSelected(true);
			footerView.getGridController().setSelected(false);
		} else {
			footerView.getGridController().setSelected(true);
			footerView.getListController().setSelected(false);
			gridView();
		}
	}
	
	
	/**
	 * @return the treeModel
	 */
	public FileTreeModel getTreeModel() {
		return treeModel;
	}



	/**
	 * @return the fileTableModel
	 */
	public FileTableModel getFileTableModel() {
		return fileTableModel;
	}



	/**
	 * @return the filePanelModel
	 */
	public FilePanelModel getFilePanelModel() {
		return filePanelModel;
	}



	/**
	 * @return the addressBarModel
	 */
	public AddressBarModel getAddressBarModel() {
		return addressBarModel;
	}

	

	/**
	 * @return the fileTableView
	 */
	public FileTableView getFileTableView() {
		return fileTableView;
	}


	/**
	 * @return the treeController
	 */
	public FileTreeController getTreeController() {
		return treeController;
	}


	/**
	 * @return the addressBarController
	 */
	public AddressBarController getAddressBarController() {
		return addressBarController;
	}





	/**
	 * @return the filePanelController
	 */
	public FilePanelController getFilePanelController() {
		return filePanelController;
	}





	/**
	 * @return the backButtonController
	 */
	public NavigateButtonController getBackButtonController() {
		return backButtonController;
	}





	/**
	 * @return the forwardButtonController
	 */
	public NavigateButtonController getForwardButtonController() {
		return forwardButtonController;
	}

	/**
	 * @return the parentButtonController
	 */
	public NavigateButtonController getParentButtonController() {
		return parentButtonController;
	}
	
	


	/**
	 * @return the menuBarView
	 */
	public MenuView getMenuBarView() {
		return menuBarView;
	}


	/**
	 * @return the filePane
	 */
	public FilePaneView getFilePane() {
		return filePane;
	}


	/**
	 * @return the footerView
	 */
	public FooterView getFooterView() {
		return footerView;
	}


	/**
	 * @return the toolBarView
	 */
	public ToolBarView getToolBarView() {
		return toolBarView;
	}


	// a function for list view
	public void listView() {
		reconstruct();

		filePane = new FilePaneView(fileTableView);
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeView, filePane);

		content.add(split, BorderLayout.CENTER);
		content.add(footerView, BorderLayout.SOUTH);

		this.setContentPane(content);

		this.setVisible(true);
	}
	
	public SystemTray getTray() {
		return tray;
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

}
