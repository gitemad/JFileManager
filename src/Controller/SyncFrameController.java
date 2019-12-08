package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import View.SyncFrameView;

public class SyncFrameController {
	
	private SyncFrameView view;

	/**
	 * Only constructor of class with following parameter requirement
	 * @param view the sync frame view
	 */
	public SyncFrameController(SyncFrameView view) {
		this.view = view;
		
		view.getStart().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent eve) {
				updateBar();
			}
		});
	}
	

	/**
	 * Update the progress bar
	 */
	public void updateBar() {
		view.getStart().setEnabled(false);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				for (int i = 0; i <= 100; i++) {
					view.getPbar().setValue(i);
					view.getPbar().paintImmediately(0, 0, 200, 200);
					view.repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				view.getStart().setEnabled(true);
			}
		});
	}
	
}
