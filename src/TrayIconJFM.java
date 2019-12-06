import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TrayIconJFM {
	
	private TrayIcon trayIcon;
	private SystemTray tray;
	private Image image;
	private ActionListener open;
	private ActionListener exit;
	
	
	public TrayIconJFM(Frame frame) {
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
//	    		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
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
	
	public SystemTray getTray() {
		return tray;
	}
}
