import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 
 * @author Emad
 *
 */
public class FilePanel extends JPanel {

    private int x;
    private int y;
    private int x2;
    private int y2;
    private JPopupMenu rClickMenu = new ContextMenuPanel();

    FilePanel() {
        x = y = x2 = y2 = 0;
        MyMouseListener listener = new MyMouseListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
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
            x = y = x2 = y2 = 10000;
            repaint();
            if (e.getButton() == MouseEvent.BUTTON3) {
            	rClickMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }
        
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0, 0, 255, 100));
        drawPerfectRect(g, x, y, x2, y2);
    }

}