package View;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SyncFrameView extends JFrame  {

	JPanel panel;
	JButton start;
	JProgressBar pbar;
	static final int MY_MINIMUM = 0;
	static final int MY_MAXIMUM = 100;

	/**
	 * Only constructor of class without any parameter requirement
	 */
	public SyncFrameView() {
		this.setSize(720, 480);
		this.setResizable(false);
		this.setMinimumSize(new Dimension(600, 480));
		this.setLocation(100, 150);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		panel = new JPanel();
		start = new JButton("Start");
		pbar = new JProgressBar();
		panel.setLayout(new GridBagLayout());
		
		pbar.setMinimum(MY_MINIMUM);
		pbar.setMaximum(MY_MAXIMUM);
		pbar.setStringPainted(true);
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.SOUTH;
		panel.add(pbar, gbc);
		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(start, gbc);
		this.setContentPane(panel);
		this.setVisible(true);
	}

	/**
	 * get the start button
	 * @return the start
	 */
	public JButton getStart() {
		return start;
	}

	/**
	 * get the progress bar
	 * @return the pbar
	 */
	public JProgressBar getPbar() {
		return pbar;
	}

//	public void run() {
//		for (int i = MY_MINIMUM; i <= MY_MAXIMUM; i++) {
//			final int percent = i;
//			try {
//				SwingUtilities.invokeLater(new Runnable() {
//					public void run() {
//						updateBar(percent);
//					}
//				});
//				java.lang.Thread.sleep(100);
//			} catch (InterruptedException e) {
//
//			}
//		}
//	}

	

}