import javax.swing.*;
import java.awt.*;

/**
 * @author Emad
 *
 */
public class TreePane extends JScrollPane {
	JTree tree;
	
	public TreePane(JTree tree) {
		super(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.tree = tree;
		
	}
}
