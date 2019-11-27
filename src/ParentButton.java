import javax.swing.*;
import java.awt.*;

/**
 * @author Emad
 *
 */
public class ParentButton extends JButton {
	
	Dimension size = new Dimension(20, 20);
	Boolean enable = false;
	
	public ParentButton() {
		super(new ImageIcon("img/parent.png"));
		this.setPreferredSize(size);
		this.setEnabled(enable);
		
	}
}
