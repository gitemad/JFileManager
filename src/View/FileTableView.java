package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.*;

import Model.FileTableModel;


/**
 * @author Emad
 *
 */
public class FileTableView extends JTable {
	
	private RectangleDrawerView rectDrawer;
    private ContextMenuFileView fileRClickMenu;
    private ContextMenuPanelView panelRClickMenu;
	private FileTableModel fileTableModel;
//	private ListSelectionListener listSelectionListener;
	private boolean cellSizesSet = false;
	
	/**
	 * Only constructor of class with following parameter requirement
	 * @param fileTableModel the model of file table
	 */
	public FileTableView(FileTableModel fileTableModel) {
		super(fileTableModel);

		fileRClickMenu = new ContextMenuFileView(new File(""));
		rectDrawer = new RectangleDrawerView();
        DrawMouseListener listener = new DrawMouseListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
        
		this.fileTableModel = fileTableModel;
	    
		this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//	    this.setAutoCreateRowSorter(true);
		
		//Sort by type and then by name
	    TableRowSorter<FileTableModel> sorter = new TableRowSorter<FileTableModel>(fileTableModel);
        this.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
        sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sorter.setSortable(0, false);
        sorter.setSortKeys(sortKeys);
        
	    this.setShowVerticalLines(false);
	    this.setShowHorizontalLines(false);
	    this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    this.setRowHeight( (int)(this.getRowHeight() * 1.5) );
	    setTableData(fileTableModel.getFiles());
	    
	    ListSelectionListener listSelectionListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                int[] rows = FileTableView.this.getSelectedRows();
                File[] files = new File[rows.length];
                int i = 0;
                for (int row : rows) {
                	row = sorter.convertRowIndexToModel(row);
                	files[i] = fileTableModel.getFile(row);
                	i++;
                }
                fileTableModel.setCurrentFiles(files);
            }
        };
        this.getSelectionModel().addListSelectionListener(listSelectionListener);
	  	    
	}
	
	
	/**
	 * @return the rClickMenu
	 */
	public ContextMenuFileView getFileRClickMenu() {
		return fileRClickMenu;
	}
	
	public ContextMenuPanelView getPanelRClickMenu() {
		return panelRClickMenu;
	}


	public void setRClickMenu(ContextMenuFileView menu) {
		fileRClickMenu = menu;
	}
	
	
	// a method to set table data by passing files to it
	private void setTableData(final File[] files) {
      SwingUtilities.invokeLater(new Runnable() {
          public void run() {
//              FileTable.this.getSelectionModel().removeListSelectionListener(listSelectionListener);
              fileTableModel.setFiles(files);
//              FileTable.this.getSelectionModel().addListSelectionListener(listSelectionListener);
              if (!cellSizesSet) {

                  setColumnWidth(0,-1);
                  setColumnWidth(1,300);
                  setColumnWidth(2,150);
                  setColumnWidth(3,112);
                  setColumnWidth(4,112);
                  FileTableView.this.setFillsViewportHeight(true);
                  cellSizesSet = true;
              }
          }
      });
  }
	
	// a method to set column width
    private void setColumnWidth(int column, int width) {
        TableColumn tableColumn = this.getColumnModel().getColumn(column);
        if (width < 0) {
            width = 20;
            tableColumn.setMaxWidth(width);
            tableColumn.setMinWidth(width);
        }
        tableColumn.setPreferredWidth(width);
    }

    class DrawMouseListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
        	int w = FileTableView.this.getWidth();
        	int h = FileTableView.this.getRowCount() * FileTableView.this.getRowHeight();
        	if (e.getX() > w || h < e.getY()) {
        		FileTableView.this.clearSelection();
        	}
            rectDrawer.setStartPoint(e.getX(), e.getY());
            
        }

        public void mouseDragged(MouseEvent e) {
            rectDrawer.setEndPoint(e.getX(), e.getY());
            repaint();
        }

        public void mouseReleased(MouseEvent e) {
        	rectDrawer.setStartPoint(10000, 10000);
        	rectDrawer.setEndPoint(10000, 10000);
        	repaint();
        }
        
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = rectDrawer.getX();
        int y = rectDrawer.getY();
        int x2 = rectDrawer.getX2();
        int y2 = rectDrawer.getY2();
//        g.clearRect(0, 0, getX(), getY());
        g.setColor(new Color(0, 0, 255));
        rectDrawer.drawRectBorder(g, x, y, x2, y2);
        g.setColor(new Color(0, 0, 255, 100));
        rectDrawer.fillRect(g, x, y, x2, y2);
    }


	public void setRClickMenu(ContextMenuPanelView panelRClickMenu) {
		this.panelRClickMenu = panelRClickMenu;
	}
	
}
