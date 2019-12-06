package View;

import java.awt.*;
import javax.swing.*;

public class NavigateButtonView extends JButton {
	
	private static Dimension size = new Dimension(20, 20);

	public NavigateButtonView(ImageIcon icon) {
		super(icon);
		
		this.setPreferredSize(size);
	}
}
