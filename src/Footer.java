import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Emad
 *
 */
public class Footer extends JPanel {
	
	private JToggleButton list = new ListView();
	private JToggleButton grid = new GridView();
	private FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
	
	public Footer() {
		super();
		this.setLayout(layout);
		this.add(list);
		this.add(grid);
	}
	
	public void addListListener(ActionListener actionListener) {
		list.addActionListener(actionListener);
	}
	
	public void addGridListener(ActionListener actionListener) {
		grid.addActionListener(actionListener);
	}
	
	public JToggleButton getList() {
		return list;
	}
	
	public JToggleButton getGrid() {
		return grid;
	}
}
