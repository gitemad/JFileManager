package View;

import java.awt.*;
import javax.swing.*;

public class ViewButtonView extends JToggleButton {
	
	private static Dimension size = new Dimension(30, 30);
	
	/**
	 * Only constructor of class with following parameter requirement
	 * @param icon the icon of button
	 * @param selected selected state of button
	 */
	public ViewButtonView(ImageIcon icon, boolean selected) {
		super(icon);
		
		this.setPreferredSize(size);
		this.setSelected(selected);
		
	}
}
