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
	
	
	public Frame() {
		super();
		this.setTitle("JFileManger");
		this.setSize(1024, 720);
		this.setMinimumSize(new Dimension(400, 400));
		this.setLocation(100, 150);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		header.setLayout(new BorderLayout());
		header.add(menuBar, BorderLayout.NORTH);
		
		content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(header, BorderLayout.NORTH);
		
		this.setContentPane(content);
		this.setVisible(true);
	}
}
