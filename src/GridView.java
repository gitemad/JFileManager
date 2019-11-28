import java.awt.Dimension;

import javax.swing.*;

/**
 * 
 */

/**
 * @author Emad
 *
 */
public class GridView extends JToggleButton {
	
	Dimension size = new Dimension(30, 30);
	public GridView() {
		super(new ImageIcon("img/gridshow.png"));
		this.setPreferredSize(size);
	}
}
