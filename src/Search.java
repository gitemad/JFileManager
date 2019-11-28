import javax.swing.*;
import java.awt.*;

/**
 * @author Emad
 *
 */
public class Search extends JTextArea {
	
	private Dimension size = new Dimension(200, 20);
	private Font font = new Font("Calibri", 20, 20);
	private Insets margin = new Insets(0, 15, 0, 0);
	
	
	public Search() {
		super("Search");
		this.setPreferredSize(size);
		this.setFont(font);
		this.setMargin(margin);
	}
}
