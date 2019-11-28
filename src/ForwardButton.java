import javax.swing.*;
import java.awt.*;

/**
 * @author Emad
 *
 */
public class ForwardButton extends JButton {
	
	private Dimension size = new Dimension(20, 20);
	private Boolean enable = false;
	
	public ForwardButton() {
		super(new ImageIcon("img/forward.png"));
		this.setPreferredSize(size);
		this.setEnabled(enable);
		
	}
}
