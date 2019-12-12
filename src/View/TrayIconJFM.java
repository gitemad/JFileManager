package View;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TrayIconJFM {
	
	private TrayIcon trayIcon;
	private SystemTray tray;
	private Image image;
	private ActionListener open;
	private ActionListener exit;
	
	/**
	 * Only constructor of class with following parameters requirement
	 * @param frame the main frame of program
	 */
	public TrayIconJFM(FrameView frame) {
	    tray = SystemTray.getSystemTray();
	    image = Toolkit.getDefaultToolkit().getImage("img/icon.png");
	    open = new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            frame.setVisible(true);
	        }
	    };
	    exit = new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		System.exit(0);
	    	}
	    };
	    
	    PopupMenu popup = new PopupMenu();
	    MenuItem openItem = new MenuItem("Open");
	    MenuItem exitItem = new MenuItem("Exit");
	    openItem.addActionListener(open);
	    exitItem.addActionListener(exit);
	    popup.add(openItem);
	    popup.add(exitItem);
	    trayIcon = new TrayIcon(image, "Tray Demo", popup);
	    trayIcon.addActionListener(open);
	    try {
	        tray.add(trayIcon);
	    } catch (AWTException e) {
	        System.err.println(e);
	    }
	}
	
	/**
	 * get the system tray
	 * @return the system tray
	 */
	public SystemTray getTray() {
		return tray;
	}
	
	public TrayIcon getTrayIcon() {
		return trayIcon;
	}
}
