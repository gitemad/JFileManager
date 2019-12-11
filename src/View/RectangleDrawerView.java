package View;
import java.awt.Graphics;
import javax.swing.JComponent;

public class RectangleDrawerView extends JComponent {
	
	private int x;
    private int y;
    private int x2;
    private int y2;
    
    /**
     * set the start point of rectangle
     * @param x the x axis position
     * @param y the y axis position
     */
	public void setStartPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

	/**
	 * Set the end point of rectangle
	 * @param x the x axis position
	 * @param y the y axis position
	 */
    public void setEndPoint(int x, int y) {
        x2 = (x);
        y2 = (y);
    }
    
    /**
     * draw border of rectangle
     * @param g graphics to draw
     * @param x x axis of start point
     * @param y y axis of start point
     * @param x2 x axis of end point
     * @param y2 y axis of end point
     */
	public void drawRectBorder(Graphics g, int x, int y, int x2, int y2) {
    	int px = Math.min(x,x2);
    	int py = Math.min(y,y2);
    	int pw = Math.abs(x-x2);
    	int ph = Math.abs(y-y2);
    	g.drawRect(px, py, pw, ph);
    }
    
	/**
	 * fill the rectangle
	 * @param g graphics to draw
	 * @param x x axis of start point
	 * @param y y axis of start point
	 * @param x2 x axis of end point
	 * @param y2 y axis of end point
	 */
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
