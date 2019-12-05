import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 * @author Emad
 *
 */
public class FileLabel extends JLabel {
	
	private boolean clicked;
	private String name;
	private ImageIcon icon;
	private static Dimension size = new Dimension(130, 130);
	private static FileSystemView fileSystemView = FileSystemView.getFileSystemView();
	
	public FileLabel(File file) {
		super();
		this.setPreferredSize(size);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.BOTTOM);
		this.setHorizontalTextPosition(JLabel.CENTER);
		this.setVerticalTextPosition(JLabel.BOTTOM);
		//TODO
//		fileLabel.setToolTipText();
		
		clicked = false;
		name = fileSystemView.getSystemDisplayName(file);
		icon = enlargeIcon(file);
		
		this.setIcon(icon);
		this.setText(name);
		
	}
	
	/**
	 * @return the clicked
	 */
	public boolean isClicked() {
		return clicked;
	}


	/**
	 * @param clicked the clicked to set
	 */
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}



	private ImageIcon enlargeIcon(File file) {
		ImageIcon icon = (ImageIcon) fileSystemView.getSystemIcon(file);
		Image imgIcon = icon.getImage();
		try {
			imgIcon = sun.awt.shell.ShellFolder.getShellFolder(file).getIcon(true);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		imgIcon = getScaledImage(imgIcon, 100, 100);
		icon.setImage(imgIcon);
		return icon;
	}
	
	
	private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
}
