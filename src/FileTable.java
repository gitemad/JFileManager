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


/**
 * @author Emad
 *
 */
public class FileTable extends JTable {
	
	private int x;
    private int y;
    private int x2;
    private int y2;
    private JPopupMenu rClickMenu;
	private FileTableModel fileTableModel;
	private ListSelectionListener listSelectionListener;
	private boolean cellSizesSet = false;
	

	public FileTable(FileTableModel fileTableModel) {
		super(fileTableModel);

		rClickMenu = new ContextMenuFile();
		//		x = y = x2 = y2 = 0;
        MyMouseListener listener = new MyMouseListener();
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
              FileTable.this.getSelectionModel().removeListSelectionListener(listSelectionListener);
              fileTableModel.setFiles(files);
              FileTable.this.getSelectionModel().addListSelectionListener(listSelectionListener);
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
    
    public void setStartPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setEndPoint(int x, int y) {
        x2 = (x);
        y2 = (y);
    }

    public void drawPerfectRect(Graphics g, int x, int y, int x2, int y2) {
        int px = Math.min(x,x2);
        int py = Math.min(y,y2);
        int pw = Math.abs(x-x2);
        int ph = Math.abs(y-y2);
        g.fillRect(px, py, pw, ph);
    }
    

    class MyMouseListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            setStartPoint(e.getX(), e.getY());
        }

        public void mouseDragged(MouseEvent e) {
            setEndPoint(e.getX(), e.getY());
            repaint();
        }

        public void mouseReleased(MouseEvent e) {
            x = y = x2 = y2 = 0;
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
//        g.clearRect(0, 0, getX(), getY());
        g.setColor(new Color(0, 0, 255, 100));
        drawPerfectRect(g, x, y, x2, y2);
    }
	
}
