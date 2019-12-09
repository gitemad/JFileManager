package Controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Model.FileLabelModel;
import View.FileLabelView;

public class FileLabelController {
	
	private FileLabelModel model;
	private FileLabelView view;
	private Color unselect;
	private Color hover;
	private Color select;
	
	/**
	 * Only constructor of class with following parameter requirement
	 * @param model the file label model
	 * @param view the file label view 
	 */
	public FileLabelController(FileLabelModel model, FileLabelView view) {
		this.model = model;
		this.view = view;
		unselect = view.getBackground();
		hover = new Color(0, 0, 255, 50);
		select = new Color(0, 0, 255, 100);
//		hover = view.getBackground().darker();
//		select = view.getBackground().darker().darker();		
		
		view.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
	            	view.getRightClickMenu().show(e.getComponent(), e.getX(), e.getY());
	            }
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				FileLabelController.this.selected();
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				if (!model.isSelected()) {
					FileLabelController.this.unSelected();
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (!model.isSelected()) {
					FileLabelController.this.hover();
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
	}

	/**
	 * set label to selected mode
	 */
	public void selected() {
		model.setSelected(true);
		view.setOpaque(true);
		view.setBackground(select);
	}
	
	/**
	 * sert label to hover mode
	 */
	public void hover() {
		view.setOpaque(true);
		view.setBackground(hover);
	}
	
	/**
	 * set label to unselected mode
	 */
	public void unSelected() {
		model.setSelected(false);
		view.setBackground(unselect);
		view.setOpaque(false);
	}
	
	public boolean getSelected() {
		return model.isSelected();
	}
	
	/**
	 * Add mouse listener to file label
	 * @param listener the listener to add
	 */
	public void addMouseListener(MouseListener listener) {
		view.addMouseListener(listener);
	}

	/**
	 * get the file label model
	 * @return the model
	 */
	public FileLabelModel getModel() {
		return model;
	}

	/**
	 * get the file label view
	 * @return the view
	 */
	public FileLabelView getView() {
		return view;
	}
	
	
}
