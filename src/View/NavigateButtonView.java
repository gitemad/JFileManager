package View;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class NavigateButtonView extends JButton {
	
	private static Dimension size = new Dimension(20, 20);
	private ImageIcon icon;
	
	/**
	 * Only constructor of class with following parameter requirement
	 * @param icon icon of button
	 */
	public NavigateButtonView(ImageIcon icon) {
		super(icon);
		
		this.icon = icon;
		this.setEnabled(false);
		this.setPreferredSize(size);
	}
	
	public ImageIcon getIcon() {
		return icon;
	}
}
