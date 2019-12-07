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
	 * @param model
	 * @param view
	 */
	public FileLabelController(FileLabelModel model, FileLabelView view) {
		this.model = model;
		this.view = view;
		unselect = view.getBackground();
		hover = view.getBackground().darker();
		select = view.getBackground().darker().darker();		
		
		view.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
	            	view.getRightClickMenu().show(e.getComponent(), e.getX(), e.getY());
	            }
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				FileLabelController.this.selected();
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				if (!model.isClicked()) {
					FileLabelController.this.unSelected();
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (!model.isClicked()) {
					FileLabelController.this.hover();
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
	}

	public void selected() {
		model.setClicked(true);
		view.setOpaque(true);
		view.setBackground(select);
	}
	
	public void hover() {
		view.setOpaque(true);
		view.setBackground(hover);
	}
	
	public void unSelected() {
		model.setClicked(false);
		view.setBackground(unselect);
		view.setOpaque(false);
	}
	
	public void addMouseListener(MouseListener listener) {
		view.addMouseListener(listener);
	}

	/**
	 * @return the model
	 */
	public FileLabelModel getModel() {
		return model;
	}

	/**
	 * @return the view
	 */
	public FileLabelView getView() {
		return view;
	}
	
	
}
