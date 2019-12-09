package Model;

import javax.swing.*;

import View.FrameView;

/**
 * @author Emad
 *
 */
public class SettingsModel {
	
	private String defaultAddress;
	private String lookAndFeel;
	
	public SettingsModel() {
		defaultAddress = System.getProperty("user.home") + "\\Desktop";
		lookAndFeel = UIManager.getInstalledLookAndFeels()[0].getClassName();
	}

	/**
	 * @return the defaultAddress
	 */
	public String getDefaultAddress() {
		return defaultAddress;
	}

	/**
	 * @param defaultAddress the defaultAddress to set
	 */
	public void setDefaultAddress(String defaultAddress) {
		this.defaultAddress = defaultAddress;
	}

	/**
	 * @return the lookAndFeel
	 */
	public String getLookAndFeel() {
		return lookAndFeel;
	}

	/**
	 * @param lookAndFeel the lookAndFeel to set
	 */
	public void setLookAndFeel(String lookAndFeel) {
		this.lookAndFeel = lookAndFeel;
	}	
	
	
}
