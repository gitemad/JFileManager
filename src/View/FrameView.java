package View;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.sun.media.sound.ModelAbstractChannelMixer;

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
	
	//Model
	private FileTreeModel treeModel;
	private FileTableModel fileTableModel;
	private FilePanelModel filePanelModel;
	private AddressBarModel addressBarModel;

	
	//View
	private ToolBarView toolBarView;
	private FooterView footerView;
	private FileTreeView treeView;
	private FilePanelView filePanelView;
	private MenuView menuBarView;
	private FilePaneView filePane;
	private FileTableView fileTableView;
	
	//Controller
	private FileTreeController treeController;
	private AddressBarController addressBarController;
	private FilePanelController filePanelController;
//	private FilePanelController filePanelController;
	
	
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
		
		
		addressBarController = toolBarView.getAddressBarController();
		addressBarModel = addressBarController.getModel();
		
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
					File f = new File(addressBarModel.getPath());
					fileTableModel.setTableData(f.listFiles());
					filePanelController.setFolder(f);
				}
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
		
		treeController.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent tse) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tse.getPath().getLastPathComponent();
				treeModel.setCurrentNode((File) node.getUserObject());
				treeController.addChildren(node);
				addressBarController.setPath(treeModel.getCurrentNode().getPath());
				fileTableModel.setTableData(treeModel.getCurrentNode().listFiles());
			}
		});
		
		fileTableModel = new FileTableModel();
		fileTableView = new FileTableView(fileTableModel);
		filePane = new FilePaneView(fileTableView);				
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeView, filePane);
		
		content.add(split, BorderLayout.CENTER);		
		content.add(footerView, BorderLayout.SOUTH);
		
		this.setContentPane(content);
	
		this.setVisible(true);
	}
	
	//a function for grid view
	private void gridView() {
		reconstruct();
		
		filePanelModel = new FilePanelModel(new File(addressBarModel.getPath()));
		filePanelView = new FilePanelView(filePanelModel);
		filePanelController = new FilePanelController(filePanelModel, filePanelView);
		
		treeController.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent tse) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tse.getPath().getLastPathComponent();
				treeModel.setCurrentNode((File) node.getUserObject());
				treeController.addChildren(node);
				addressBarController.setPath(treeModel.getCurrentNode().getPath());
				filePanelController.setFolder(treeModel.getCurrentNode());
			}
		});
		
		
		
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
	
		treeModel = new FileTreeModel();
		treeView = new FileTreeView(treeModel.getTree());
		treeController = new FileTreeController(treeModel, treeView);
	}
	
}
