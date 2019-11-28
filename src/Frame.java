import javax.swing.*;
import java.awt.*;


/**
 * @author Emad
 *
 */
public class Frame extends JFrame {
	
	JPanel content = new JPanel();
	JPanel header = new JPanel();
	JMenuBar menuBar = new Menu();
	JToolBar toolBar = new Toolbar();
	JTree tree = new Tree();
	JPanel filePanel = new JPanel();
	JScrollPane treePane = new TreePane(tree);
	JScrollPane filePane = new FilePane(filePanel);
	JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePane, filePane);
	
	public Frame() {
		super();
		this.setTitle("JFileManger");
		this.setSize(1024, 720);
		this.setMinimumSize(new Dimension(600, 400));
		this.setLocation(100, 150);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		header.setLayout(new BorderLayout());
		header.add(menuBar, BorderLayout.NORTH);
		header.add(toolBar, BorderLayout.SOUTH);
		
		content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(header, BorderLayout.NORTH);
		
		content.add(split, BorderLayout.CENTER);
		
		this.setContentPane(content);
		
		
		this.setVisible(true);
	}
}
