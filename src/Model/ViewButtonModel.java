package Model;


public class ViewButtonModel {
	
	private Boolean selected;

	/**
	 * Only constructor of class with following parameter requirement
	 * @param selected selected state of button
	 */
	public ViewButtonModel(boolean selected) {
		this.selected = selected;
	}

	
	/**
	 * @return the selected
	 */
	public Boolean getSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
	
}
