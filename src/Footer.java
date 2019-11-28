import javax.swing.*;
import java.awt.*;

/**
 * @author Emad
 *
 */
public class Footer extends JPanel {
	
	JToggleButton list = new ListView();
	JToggleButton grid = new GridView();
	FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
	
	public Footer() {
		super();
		this.setLayout(layout);		
		this.add(list);
		this.add(grid);
	}
}
