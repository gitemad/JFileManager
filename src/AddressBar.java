import javax.swing.*;
import java.awt.*;

/**
 * @author Emad
 *
 */
public class AddressBar extends JTextArea {
	Dimension size = new Dimension(450, 20);
	Font font = new Font("Calibri", 20, 20);
	
	public AddressBar() {
		super("Address Bar");
		this.setPreferredSize(size);
		this.setFont(font);
	}
}
