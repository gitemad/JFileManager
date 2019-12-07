import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.*;

import View.RectangleDrawerView;

/**
 * @author Emad
 *
 */
public class FilePane extends JScrollPane {
	
    private JPopupMenu rClickMenu;
	private JPanel panel;
	private JTable table;
	private Dimension minSize;
	private RectangleDrawerView rectDrawer;
	
	
	public FilePane(JPanel panel) {
		super(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		rClickMenu = new JPopupMenu();
		rectDrawer = new RectangleDrawerView();
		minSize = new Dimension(400, 300);
		
		this.panel = panel;
		this.setMinimumSize(minSize);

	}
	
	public FilePane(JTable table) {
		super(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.table = table;
		rClickMenu = new ContextMenuEmptyPanel();
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
	
	
    public void drawPerfectRect(Graphics g, int x, int y, int x2, int y2) {
        int px = Math.min(x,x2);
        int py = Math.min(y,y2);
        int pw = Math.abs(x-x2);
        int ph = Math.abs(y-y2);
        g.fillRect(px, py, pw, ph);
        this.getViewport().paint(g);
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
