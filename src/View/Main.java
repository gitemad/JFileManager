package View;

import Controller.FrameController;

/**
 * @author Emad
 *
 */
public class Main {

	
	static FrameView view;
	static FrameController controller;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FrameView filemanager = new FrameView();
		view = filemanager;
		FrameController fileManagerController = new FrameController(filemanager);
		controller = fileManagerController;
	}
	
}




