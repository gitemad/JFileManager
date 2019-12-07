package View;
import java.awt.Graphics;
import javax.swing.JComponent;

public class RectangleDrawerView extends JComponent {
	
	private int x;
    private int y;
    private int x2;
    private int y2;
    
    
	public void setStartPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setEndPoint(int x, int y) {
        x2 = (x);
        y2 = (y);
    }
    
	public void drawRectBorder(Graphics g, int x, int y, int x2, int y2) {
    	int px = Math.min(x,x2);
    	int py = Math.min(y,y2);
    	int pw = Math.abs(x-x2);
    	int ph = Math.abs(y-y2);
    	g.drawRect(px, py, pw, ph);
    }
    
    public void fillRect(Graphics g, int x, int y, int x2, int y2) {
        int px = Math.min(x,x2);
        int py = Math.min(y,y2);
        int pw = Math.abs(x-x2);
        int ph = Math.abs(y-y2);
        g.fillRect(px, py, pw, ph);
    }
    
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the x2
	 */
	public int getX2() {
		return x2;
	}

	/**
	 * @return the y2
	 */
	public int getY2() {
		return y2;
	}
    
}
