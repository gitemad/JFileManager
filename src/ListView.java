import java.awt.Dimension;

import javax.swing.*;

/**
 * @author Emad
 *
 */
public class ListView extends JToggleButton {
	
	Dimension size = new Dimension(30, 30);

	public ListView() {
		super(new ImageIcon("img/listshow.png"));
		this.setPreferredSize(size);
	}
}
