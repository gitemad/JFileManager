package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.ContextMenuFileView;
import View.PropertiesView;

public class ContextMenuFileController {

	private ContextMenuFileView view;
	
	/**
	 * Only constructor of class with following parameter requirement
	 * @param view the context menu file view
	 */
	public ContextMenuFileController(ContextMenuFileView view) {
		this.view = view;
		
		
		view.getProperties().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent eve) {
				new PropertiesView(view.getFile());
			}
		});
		
		
	}
}
