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
	
	
	public Frame() {
		super();
		this.setTitle("JFileManger");
		this.setSize(1024, 720);
		this.setMinimumSize(new Dimension(600, 600));
		this.setLocation(100, 150);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		header.setLayout(new BorderLayout());
		header.add(menuBar, BorderLayout.NORTH);
		header.add(toolBar, BorderLayout.SOUTH);
		
		content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(header, BorderLayout.NORTH);
		
		this.setContentPane(content);
		
		
		
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look and feel.
		}
		this.setVisible(true);
	}
}
