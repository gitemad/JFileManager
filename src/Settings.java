import javax.swing.*;
import java.awt.*;

/**
 * @author Emad
 *
 */
public class Settings extends JFrame {
	
	private Image icon = new ImageIcon("img/settings.png").getImage();
	private JPanel panel = new JPanel(new GridBagLayout());
	private JPanel adrsPanel = new JPanel(new GridBagLayout());
	private JPanel sharePanel = new JPanel(new GridBagLayout());
	private JPanel viewPanel = new JPanel(new GridBagLayout());
	private JTextField address = new JTextField("Select default address");
	private JTextField savePath = new JTextField("Select save path location");
	private JTextField compAdrs = new JTextField("Select computer address to share files");
	private JTextField compPort = new JTextField("Select computer port to share files");
	private JComboBox<String> lookFeels = new JComboBox<String>();
	private Checkbox view = new Checkbox("List View");
	private JComboBox<String> syncTime = new JComboBox<String>();
	private JButton ok = new JButton("OK");
	private JButton cancel = new JButton("Cancel");
	
	public Settings() {
		super("Settings");
		this.setIconImage(icon);
		this.setSize(720, 480);
		this.setResizable(false);
		this.setMinimumSize(new Dimension(600, 480));
		this.setLocation(100, 150);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	
		for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			lookFeels.addItem(info.getName());
		}
		
		String[] times = {"1 minute", "5 minutes", "10 minutes", "30 minutes", "60 minutes", "never"};
		
		for (String time : times) {
			syncTime.addItem(time);
		}
		
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        adrsPanel.add(new JLabel("Default address: "), gbc);
        
        
        sharePanel.add(new JLabel("Computer address to file sharing: "), gbc);
        gbc.gridy++;
        sharePanel.add(new JLabel("Computer port to file sharing: "), gbc);
        gbc.gridy++;
        sharePanel.add(new JLabel("Default save path location: "), gbc);
        gbc.gridy++;
        sharePanel.add(new JLabel("Sync data every:  "), gbc);
        
        gbc.gridy = 0;
        viewPanel.add(new JLabel("Select look and feel: "));
        
        
        gbc.gridx++;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        adrsPanel.add(address, gbc);
        
        
        sharePanel.add(compAdrs, gbc);
        gbc.gridy++;
        sharePanel.add(compPort, gbc);
        gbc.gridy++;
        sharePanel.add(savePath, gbc);
        gbc.gridy++;
		sharePanel.add(syncTime, gbc);
		
		gbc.gridy = 0;
		viewPanel.add(lookFeels, gbc);
		gbc.gridy++;
		viewPanel.add(view, gbc);
		
		
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(40, 40, 40, 40);
		panel.add(adrsPanel, gbc);
		gbc.gridy++;
		panel.add(sharePanel, gbc);
		gbc.gridy++;
		panel.add(viewPanel, gbc);
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.fill = GridBagConstraints.REMAINDER;
		gbc.weighty = 1;
		gbc.weightx = 0;
		gbc.gridx++;
		gbc.gridy++;
		panel.add(ok, gbc);
		gbc.gridx++;
		panel.add(cancel, gbc);
		this.setContentPane(panel);
		this.setVisible(true);
	}
}
