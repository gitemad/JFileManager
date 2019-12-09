package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.*;

/**
 * @author Emad
 *
 */
public class FilePaneView extends JScrollPane {
	
    private JPopupMenu rClickMenu;
	private JPanel panel;
	private JTable table;
	private Dimension minSize;
	private RectangleDrawerView rectDrawer;
	
	/**
	 * first constructor of class with following parameter requirement
	 * @param panel the panel you want to add to pane for grid view 
	 */
	public FilePaneView(JPanel panel) {
		super(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		rClickMenu = new JPopupMenu();
		rectDrawer = new RectangleDrawerView();
		minSize = new Dimension(400, 300);
		
		this.panel = panel;
		this.setMinimumSize(minSize);

	}
	
	/**
	 * second constructor of class with following parameter requirement
	 * @param table the table you want to show files in list view
	 */
	public FilePaneView(JTable table) {
		super(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.table = table;
		rClickMenu = new ContextMenuPanelView();
		rectDrawer = new RectangleDrawerView();
//		this.getViewport().setBackground(Color.WHITE);
//		this.getViewport().setOpaque(false);
//		this.getViewport().getView().setBackground(new Color(0, 0, 0, 0));
//		this.getViewport().getView().setForeground(Color.BLACK);
		
		this.setOpaque(false);
		this.getViewport().setOpaque(false);
		this.setMinimumSize(minSize);

		DrawMouseListener listener = new DrawMouseListener();
		this.getViewport().addMouseListener(listener);
		this.getViewport().addMouseMotionListener(listener);
	}
	

    class DrawMouseListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            rectDrawer.setStartPoint(e.getX(), e.getY());
            table.clearSelection();
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
