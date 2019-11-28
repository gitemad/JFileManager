import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;

/**
 * @author Emad
 *
 */
public class Search extends JTextField {
	
	private Dimension size = new Dimension(200, 20);
	private Font font = new Font("Calibri", 20, 20);
	private Insets margin = new Insets(0, 0, 0, 0);
	Icon icon = new ImageIcon("img/search.png");
	private Border outer = this.getBorder();
	private Border iconBorder = new MatteBorder(0, 25, 0, 0, icon);
	private CompoundBorder border = new CompoundBorder(outer, iconBorder);
	
	public Search() {
		super("Search");
		this.setBorder(border);
		this.setPreferredSize(size);
		this.setFont(font);
		this.setMargin(margin);
	}
}
