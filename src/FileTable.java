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
public class FileTable extends JTable {
	
	private RectangleDrawer rectDrawer;
    private JPopupMenu rClickMenu;
	private FileTableModel fileTableModel;
//	private ListSelectionListener listSelectionListener;
	private boolean cellSizesSet = false;
	

	public FileTable(FileTableModel fileTableModel) {
		super(fileTableModel);

		rClickMenu = new ContextMenuFile(new File(""));
		rectDrawer = new RectangleDrawer();
        DrawMouseListener listener = new DrawMouseListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
        
		this.fileTableModel = fileTableModel;
	    
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//	    this.setAutoCreateRowSorter(true);
		
		//Sort by name and then by date
	    TableRowSorter<FileTableModel> sorter = new TableRowSorter<FileTableModel>(fileTableModel);
        this.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
        sorter.setSortable(0, false);
        sorter.setSortKeys(sortKeys);
        
	    this.setShowVerticalLines(false);
	    this.setShowHorizontalLines(false);
	    this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    this.setRowHeight( (int)(this.getRowHeight() * 1.5) );
	    setTableData(FileSystemView.getFileSystemView().getFiles(FileSystemView.getFileSystemView().getRoots()[0], true));
	  	    
	}
	
	
		
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
                  FileTable.this.setFillsViewportHeight(true);
                  cellSizesSet = true;
              }
          }
      });
  }
	
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
        	if (e.isPopupTrigger()) {
                JTable source = (JTable) e.getSource();
                int row = source.rowAtPoint( e.getPoint() );
                int column = source.columnAtPoint( e.getPoint() );
                if (! source.isRowSelected(row))
                    source.changeSelection(row, column, false, false);
                rClickMenu.show(e.getComponent(), e.getX(), e.getY());
            }
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
	
}
