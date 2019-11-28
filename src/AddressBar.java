import javax.swing.*;
import java.awt.*;

/**
 * @author Emad
 *
 */
public class AddressBar extends JTextField {

	private Dimension size = new Dimension(450, 20);
	private Font font = new Font("Calibri", 20, 20);
	private Insets margin = new Insets(0, 15, 0, 0);

	public AddressBar() {
		super("Address Bar");
		this.setPreferredSize(size);
		this.setFont(font);
		this.setMargin(margin);

	}
}
