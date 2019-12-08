package View;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import Model.SearchModel;

public class SearchView extends JTextField {
	
	private Dimension size;
	private Font font;
	private Insets margin;
	Icon icon;
	private Border outer;
	private Border iconBorder;
	private CompoundBorder border;

	/**
	 * Only constructor of class with following parameter requirement
	 * @param model the search model
	 */
	public SearchView(SearchModel model) {
		super(model.getText());
		
		size = new Dimension(200, 20);
		font = new Font("Calibri", 20, 20);
		margin = new Insets(0, 0, 0, 0);
		icon = new ImageIcon("img/search.png");
		outer = this.getBorder();
		iconBorder = new MatteBorder(0, 25, 0, 0, icon);
		border = new CompoundBorder(outer, iconBorder);


		this.setBorder(border);
		this.setPreferredSize(size);
		this.setFont(font);
		this.setMargin(margin);
	}
}
