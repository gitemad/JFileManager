import javax.swing.*;
import java.awt.*;


/**
 * @author Emad
 *
 */
public class Frame extends JFrame {
	
	private JPanel content = new JPanel();
	private JPanel header = new JPanel();
	private JMenuBar menuBar = new Menu();
	private JToolBar toolBar = new Toolbar();
	private JTree tree = new Tree();
	private JPanel filePanel = new JPanel();
	private JScrollPane treePane = new TreePane(tree);
	private JScrollPane filePane = new FilePane(filePanel);
	private JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePane, filePane);
	private JPanel footer = new Footer();
	
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
		
		
		content.add(footer, BorderLayout.SOUTH);
		
		
		this.setContentPane(content);
		
		
		this.setVisible(true);
	}
}
