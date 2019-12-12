package Controller;

import java.awt.event.KeyListener;

import Model.AddressBarModel;
import View.AddressBarView;

/**
 * 
 * @author Emad
 *
 */
public class AddressBarController {
	
	private AddressBarModel model;
	private AddressBarView view;
	
	/**
	 * Only constructor of class with following parameter requirement
	 * @param model model of address bar
	 * @param view view of address bar
	 */
	public AddressBarController(AddressBarModel model, AddressBarView view) {
		this.model = model;
		this.view = view;
		
		
//		KeyListener k = new KeyListener() {
//			@Override
//			public void keyTyped(KeyEvent arg0) {
//			}
//			
//			@Override
//			public void keyReleased(KeyEvent arg0) {
//			}
//			
//			@Override
//			public void keyPressed(KeyEvent k) {
//				if (k.getKeyCode() == KeyEvent.VK_ENTER) {
//					File f = new File(view.getText());
//					if (f.exists() && f.isDirectory()) {
//						setPath(f.getPath());
//					} else {
//						view.setText(model.getPath());
//					}
//				}
//			}
//		};
		
//		this.view.addKeyListener(k);
	}
	
	/**
	 * adding key listener to address bar
	 * @param k key listener to add
	 */
	public void addKeyListener(KeyListener k) {
		view.addKeyListener(k);
	}
	
	
	/**
	 * get the model of address bar
	 * @return the model
	 */
	public AddressBarModel getModel() {
		return model;
	}


	/**
	 * get the view of address bar
	 * @return the views
	 */
	public AddressBarView getView() {
		return view;
	}


	/**
	 * set the path to address bar
	 * @param path the path to set
	 */
	public void setPath(String path) {
		model.setPath(path);
		updateView();
	}
	
	private void updateView() {
		view.setText(model.getPath());
	}
}
