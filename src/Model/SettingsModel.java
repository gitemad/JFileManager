package Model;

import javax.swing.*;

import View.FrameView;

/**
 * @author Emad
 *
 */
public class SettingsModel {
	
	private static String defaultAddress;
	private static int numPageHistory;
	private static String lookAndFeel;
	private static int lNum;
	private static boolean list;
	
	/**
	 * Only constructor of class without any parameters
	 */
	public SettingsModel() {
		if (lookAndFeel == null) {
			defaultAddress = System.getProperty("user.home") + "\\Desktop";
			numPageHistory = 10;
			lNum = 1;
			lookAndFeel = UIManager.getInstalledLookAndFeels()[lNum].getClassName();
			list = true;
		}
	}

	/**
	 * @return the defaultAddress
	 */
	public static String getDefaultAddress() {
		return defaultAddress;
	}

	/**
	 * @param defaultAddress the defaultAddress to set
	 */
	public static void setDefaultAddress(String defaultAdrs) {
		defaultAddress = defaultAdrs;
	}

	/**
	 * @return the lookAndFeel
	 */
	public static String getLookAndFeel() {
		return lookAndFeel;
	}

	/**
	 * @param lookAndFeel the lookAndFeel to set
	 */
	public static void setLookAndFeel(String lookFeel) {
		lookAndFeel = lookFeel;
	}

	/**
	 * @return the list
	 */
	public static boolean isList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public static void setList(boolean listt) {
		list = listt;
	}

	/**
	 * @return the lNum
	 */
	public static int getlNum() {
		return lNum;
	}

	/**
	 * @param lNum the lNum to set
	 */
	public static void setlNum(int num) {
		lNum = num;
	}

	/**
	 * @return the numPageHistory
	 */
	public static int getNumPageHistory() {
		return numPageHistory;
	}

	/**
	 * @param numPageHistory the numPageHistory to set
	 */
	public static void setNumPageHistory(int numHistory) {
		numPageHistory = numHistory;
	}	
	
	
}
