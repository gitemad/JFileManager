import javax.swing.*;
import java.awt.*;


/**
 * @author Emad
 *
 */
public class Frame {
	JFrame frame;
	JPanel panel;
	JToolBar toolbar;
	Menu menu;
	
	public Frame() {
		frame = new JFrame();
		frame.setTitle("JFileManger");
		frame.setSize(1024, 720);
		frame.setMinimumSize(new Dimension(400, 400));
		frame.setLocation(100, 150);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setVisible(true);
	}
}
