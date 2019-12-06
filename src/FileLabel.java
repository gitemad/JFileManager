import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
	private ContextMenuFile rClick;
	
	public FileLabel(File file) {
		super();
		this.setPreferredSize(size);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.BOTTOM);
		this.setHorizontalTextPosition(JLabel.CENTER);
		this.setVerticalTextPosition(JLabel.BOTTOM);
		rClick = new ContextMenuFile(file);
		this.setToolTipText(getToolTip(file));
		
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

	public ContextMenuFile getRightClickMenu() {
		return rClick;
	}

	
	private String getToolTip(File file) {
		String toolTip = "<html>";
		toolTip += "Date Created: ";
		toolTip += getCreatedDate(file);
		toolTip += "<br/>";
		toolTip += "Size: ";
		toolTip += getFileSize(file);
		if (file.isDirectory()) {
			toolTip += "<br/>";
			toolTip += "Folders: ";
			toolTip += getSubFolders(file);
			toolTip += "<br/>";
			toolTip += "Files: ";
			toolTip += getSubFiles(file);
		}
		toolTip += "</html>";
		return toolTip;
	}
	
	private Date getCreatedDate(File file) {
		Path filePath;
		try {
			filePath = Paths.get(file.getPath());
		} catch (Exception e) {
			return new Date(file.length());
		}
        BasicFileAttributes attributes = null;
        try {
            attributes =
                    Files.readAttributes(filePath, BasicFileAttributes.class);
        } catch (IOException exception) {
            System.out.println("Exception handled when trying to get file " +
                    "attributes: " + exception.getMessage());
        }
        long milliseconds = attributes.creationTime().to(TimeUnit.MILLISECONDS);
        Date creationDate = new Date(milliseconds);
        return creationDate;
	}
	
	
	private String getSubFolders(File file) {
		String subFolders = "";
		File[] folders = file.listFiles(File::isDirectory);
		int i = 0;
		try {
			for (File folder : folders) {
				subFolders += folder.getName();
				i++;
				if (i == 3) {
					break;
				}
				subFolders += ", ";
			}
		} catch (NullPointerException e) {
			
		} finally {
			return subFolders;
		}
	}

	private String getSubFiles(File file) {
		String subFiles = "";
		File[] files = file.listFiles(File::isFile);
		int i = 0;
		try {
			for (File subFile : files) {
				subFiles += subFile.getName();
				i++;
				if (i == 3) {
					break;
				}
				subFiles += ", ";
			}
		} catch (NullPointerException e) {
			
		} finally {
			return subFiles;
		}
	}
	
	private String getFileSize(File file) {
    	if (!file.isFile())
    		return "";
    	
    	long bytes = file.length();
		long kilobytes = (bytes / 1024);
		long megabytes = (kilobytes / 1024);
		long gigabytes = (megabytes / 1024);
		long terabytes = (gigabytes / 1024);
		
		if (terabytes > 0)
			return terabytes + " TB";
		if (gigabytes > 0)
			return gigabytes + " GB";
		if (megabytes > 0)
			return megabytes + " MB";
		if (kilobytes > 0)
			return kilobytes + " KB";
		if (bytes > 0)
			return bytes + " B";
		return "";
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
