import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * @author Emad
 *
 */
public class ListView extends JToggleButton {
	
	Dimension size = new Dimension(30, 30);

	public ListView() {
		super(new ImageIcon("img/listshow.png"));
		this.setPreferredSize(size);
		this.setSelected(true);
	}
	
}
