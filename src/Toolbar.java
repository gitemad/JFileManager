import javax.swing.*;
import java.awt.*;

/**
 * @author Emad
 *
 */
public class Toolbar extends JToolBar {
	
	JPanel buttonsPanel = new JPanel();
	JButton back = new BackButton();
	JButton forward = new ForwardButton();
	JButton parent = new ParentButton();
	JTextArea address = new AddressBar();
	JTextArea search = new Search();
	BorderLayout layout = new BorderLayout(15, 0);
	
	public Toolbar() {
		super();
		this.setLayout(layout);		
		buttonsPanel.add(back);
		buttonsPanel.add(forward);
		buttonsPanel.add(Box.createHorizontalStrut(15));
		buttonsPanel.add(parent);
		this.add(buttonsPanel, BorderLayout.WEST);
		
		this.add(address, BorderLayout.CENTER);
		
		this.add(search, BorderLayout.EAST);
	}
}
