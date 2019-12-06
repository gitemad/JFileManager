package View;

import java.awt.*;
import javax.swing.*;

public class ViewButton extends JToggleButton {
	
	private static Dimension size = new Dimension(30, 30);
	
	public ViewButton(ImageIcon icon, boolean selected) {
		super(icon);
		
		this.setPreferredSize(size);
		this.setSelected(selected);
		
	}
}
