package Model;

public class SearchModel{
	
	private String text;
	
	/**
	 * Only constructor of class with following parameter requirement
	 * @param text the text you want to search
	 */
	public SearchModel(String text) {
		this.text = text;
	}


	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}


	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	
}
