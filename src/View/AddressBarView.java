package View;

import java.awt.*;
import javax.swing.*;

import Model.AddressBarModel;

public class AddressBarView extends JTextField {
	
	private Dimension size;
	private Font font;
	private Insets margin = new Insets(0, 15, 0, 0);
	
	/**
	 * Only constructor of class with following parameter requirement
	 * @param model the address bar model
	 */
	public AddressBarView(AddressBarModel model) {
		super(model.getPath());
		font = new Font("Calibri", 20, 20);
		margin = new Insets(0, 15, 0, 0);
		
		this.setPreferredSize(size);
		this.setFont(font);
		this.setMargin(margin);
	}
}
