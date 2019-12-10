package View;

import Controller.FrameController;

/**
 * @author Emad
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FrameView filemanager = new FrameView();
		FrameController fileManagerController = new FrameController(filemanager);
	}

}



