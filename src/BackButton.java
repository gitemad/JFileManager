import javax.swing.*;
import java.awt.*;

/**
 * @author Emad
 *
 */
public class BackButton extends JButton {
	
	private Dimension size = new Dimension(20, 20);
	private Boolean enable = true;
	
	public BackButton() {
		super(new ImageIcon("img/back.png"));
		this.setPreferredSize(size);
		this.setEnabled(enable);
		
	}
}
