import javax.swing.*;
import java.awt.*;

/**
 * @author Emad
 *
 */
public class Toolbar extends JToolBar {
	
	private JPanel buttonsPanel = new JPanel();
	private JButton back = new BackButton();
	private JButton forward = new ForwardButton();
	private JButton parent = new ParentButton();
	private JTextField address = new AddressBar();
	private JTextField search = new Search();
	private BorderLayout layout = new BorderLayout(15, 0);
	private Insets margin = new Insets(0, 5, 0, 15);
	
	public Toolbar() {
		super();
		this.setLayout(layout);		
		this.setMargin(margin);
		buttonsPanel.add(back);
		buttonsPanel.add(forward);
		buttonsPanel.add(Box.createHorizontalStrut(15));
		buttonsPanel.add(parent);
		this.add(buttonsPanel, BorderLayout.WEST);
		
		this.add(address, BorderLayout.CENTER);
		
		this.add(search, BorderLayout.EAST);
	}
}
