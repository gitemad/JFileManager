package Model;

import javax.swing.*;

import View.FrameView;

/**
 * @author Emad
 *
 */
public class SettingsModel {
	
	private String defaultAddress;
	private int numPageHistory;
	private String lookAndFeel;
	private int lNum;
	private boolean list;
	
	public SettingsModel() {
		defaultAddress = System.getProperty("user.home") + "\\Desktop";
		numPageHistory = 10;
		lNum = 1;
		lookAndFeel = UIManager.getInstalledLookAndFeels()[lNum].getClassName();
		list = true;
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

	/**
	 * @return the list
	 */
	public boolean isList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(boolean list) {
		this.list = list;
	}

	/**
	 * @return the lNum
	 */
	public int getlNum() {
		return lNum;
	}

	/**
	 * @param lNum the lNum to set
	 */
	public void setlNum(int lNum) {
		this.lNum = lNum;
	}

	/**
	 * @return the numPageHistory
	 */
	public int getNumPageHistory() {
		return numPageHistory;
	}

	/**
	 * @param numPageHistory the numPageHistory to set
	 */
	public void setNumPageHistory(int numPageHistory) {
		this.numPageHistory = numPageHistory;
	}	
	
	
}
