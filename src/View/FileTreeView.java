package View;
import javax.swing.*;
import java.awt.*;

/**
 * @author Emad
 *
 */
public class FileTreeView extends JScrollPane {
	
	private JTree tree;
	private Dimension minSize;
	
	
	public FileTreeView(JTree tree) {
		super(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.tree = tree;
		
		minSize = new Dimension(180, 300);
		this.setMinimumSize(minSize);

	}
}
