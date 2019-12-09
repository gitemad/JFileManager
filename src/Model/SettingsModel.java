package Model;

/**
 * @author Emad
 *
 */
public class SettingsModel {
	
	private String defaultAddress;
	
	public SettingsModel() {
		defaultAddress = System.getProperty("user.home") + "\\Desktop";
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
	
	
}
