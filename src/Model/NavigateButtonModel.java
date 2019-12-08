package Model;

public class NavigateButtonModel {
	
	private boolean enable;
	
	/**
	 * Only constructor of class with following parameter requirement
	 * @param enable enable state of buttonss
	 */
	public NavigateButtonModel(boolean enable) {
		this.enable = enable;
	}
	

	/**
	 * @return the enable
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * @param enable the enable to set
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	
}
