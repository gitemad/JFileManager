import javax.swing.*;
import java.awt.*;

/**
 * @author Emad
 *
 */
public class FilePane extends JScrollPane {
	private JPanel panel;
	private JTable table;
	private Dimension minSize = new Dimension(400, 300);
	
	public FilePane(JPanel panel) {
		super(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.panel = panel;
		this.setMinimumSize(minSize);
	}
	
	public FilePane(JTable table) {
		super(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.table = table;
		this.setMinimumSize(minSize);
	}
}
