import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Emad
 *
 */
public class FilePane extends JScrollPane {
	
    private int x;
    private int y;
    private int x2;
    private int y2;
    private JPopupMenu rClickMenu = new ContextMenuPanel();
	private JPanel panel;
	private JTable table;
	private Dimension minSize = new Dimension(400, 300);
	
	public FilePane(JPanel panel) {
		super(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.panel = panel;
		this.setMinimumSize(minSize);
		

	}
	
	public FilePane(JTable table) {
		super(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.table = table;
		JPanel rectPanel = new JPanel();
		rectPanel.setBackground(Color.BLACK);
		this.add(rectPanel, new BorderLayout());
		this.getViewport().setBackground(Color.WHITE);
//		this.getViewport().setOpaque(false);
//		this.getViewport().getView().setBackground(new Color(0, 0, 0, 0));
		this.setMinimumSize(minSize);

		MyMouseListener listener = new MyMouseListener();
		this.getViewport().addMouseListener(listener);
		this.getViewport().addMouseMotionListener(listener);
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
        this.getViewport().paint(g);
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
            if (e.getButton() == MouseEvent.BUTTON3) {
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
