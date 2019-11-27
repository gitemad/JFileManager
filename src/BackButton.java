import javax.swing.*;
import java.awt.*;

/**
 * @author Emad
 *
 */
public class BackButton extends JButton {
	
	Dimension size = new Dimension(20, 20);
	Boolean enable = false;
	
	public BackButton() {
		super(new ImageIcon("img/back.png"));
		this.setPreferredSize(size);
		this.setEnabled(enable);
		
	}
}
