import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		list.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent eve) {
				list.setSelected(true);
				grid.setSelected(false);
			}
		});
		
		grid.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent eve) {
				grid.setSelected(true);
				list.setSelected(false);
			}
		});

		this.add(list);
		this.add(grid);
	}
}
