package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.EditMenuView;
import View.SyncFrameView;

public class EditMenuController {
	
	private EditMenuView view;

	/**
	 * @param view
	 */
	public EditMenuController(EditMenuView view) {
		this.view = view;
		
		view.getSync().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent eve) {
				SyncFrameView syncFrameView = new SyncFrameView();
				new SyncFrameController(syncFrameView);
			}
		});
	}
	
	
}
