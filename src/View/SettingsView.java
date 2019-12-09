package View;

import javax.swing.*;

import Model.SettingsModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.nio.channels.SeekableByteChannel;

/**
 * @author Emad
 *
 */
public class SettingsView extends JFrame {

	private SettingsModel model;

	private Image icon;
	private JPanel panel;
	private JPanel adrsPanel;
	private JPanel sharePanel;
	private JPanel viewPanel;
	private JTextField address;
	private JTextField savePath;
	private JTextField compAdrs;
	private JTextField compPort;
	private JComboBox<String> lookFeels;
	private Checkbox view;
	private JComboBox<String> syncTime;
	private JButton ok;
	private JButton cancel;

	/**
	 * Only constructor of class without any parameter requirement
	 */
	public SettingsView(SettingsModel model) {
		super("Settings");
		this.model = model;

		icon = new ImageIcon("img/settings.png").getImage();
		panel = new JPanel(new GridBagLayout());
		adrsPanel = new JPanel(new GridBagLayout());
		sharePanel = new JPanel(new GridBagLayout());
		viewPanel = new JPanel(new GridBagLayout());
		address = new JTextField(model.getDefaultAddress());
		savePath = new JTextField("Select save path location");
		compAdrs = new JTextField("Select computer address to share files");
		compPort = new JTextField("Select computer port to share files");
		lookFeels = new JComboBox<String>();
		view = new Checkbox("Set List View As Default View");
		syncTime = new JComboBox<String>();
		ok = new JButton("OK");
		cancel = new JButton("Cancel");

		ok.addActionListener(confirm());

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

		String[] times = { "1 minute", "5 minutes", "10 minutes", "30 minutes", "60 minutes", "never" };

		for (String time : times) {
			syncTime.addItem(time);
		}

		view.setState(true);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.anchor = GridBagConstraints.EAST;

		adrsPanel.add(new JLabel("Default address: "), gbc);

		sharePanel.add(new JLabel("Computer address to file sharing: "), gbc);
		gbc.gridy++;
		sharePanel.add(new JLabel("Computer port to file sharing: "), gbc);
		gbc.gridy++;
		sharePanel.add(new JLabel("Default save path location: "), gbc);
		gbc.gridy++;
		sharePanel.add(new JLabel("Sync data every:  "), gbc);

		gbc.gridy = 0;
		viewPanel.add(new JLabel("Select look and feel: "), gbc);

		gbc.gridx++;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;

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
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = new Insets(40, 40, 40, 40);
		panel.add(adrsPanel, gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridy++;
		panel.add(sharePanel, gbc);
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.gridy++;
		panel.add(viewPanel, gbc);
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.fill = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.gridx++;
		gbc.gridy++;
		panel.add(ok, gbc);
		gbc.gridx++;
		gbc.weightx = 0;
		panel.add(cancel, gbc);
		this.setContentPane(panel);
	}

	public String getDefaultAddress() {
		return address.getText();
	}
	

	/**
	 * @return the ok
	 */
	public JButton getOk() {
		return ok;
	}

	public Action confirm() {
		Action a = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = lookFeels.getSelectedIndex();
				try {
					String lf = UIManager.getInstalledLookAndFeels()[i].getClassName();
					model.setLookAndFeel(lf);
					UIManager.setLookAndFeel(lf);
					for(Window window: Window.getWindows()) {
					    SwingUtilities.updateComponentTreeUI(window);
					}
				} catch (Exception ignored) {
					System.out.println("Catched");
				} finally {
					SettingsView.this.setVisible(false);
				}
			}
		};
		return a;
	}
}
