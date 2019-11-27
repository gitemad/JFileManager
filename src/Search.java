import javax.swing.*;
import java.awt.*;

/**
 * @author Emad
 *
 */
public class Search extends JTextArea {
	Dimension size = new Dimension(200, 20);
	Font font = new Font("Calibri", 20, 20);

	public Search() {
		super("Search");
		this.setPreferredSize(size);
		this.setFont(font);
	}
}
