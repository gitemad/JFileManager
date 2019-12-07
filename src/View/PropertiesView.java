package View;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.*;


/**
 * @author Emad
 *
 */
public class PropertiesView extends JOptionPane {
		
	public PropertiesView(File file) {
		this.showMessageDialog(null, getData(file), "Properties", JOptionPane.PLAIN_MESSAGE);
	}
	
	private String getData(File file) {
		String props = "<html>";
		props += "Type:&ensp&emsp &emsp &emsp ";
		props += getType(file);
		props += "<br/>";
		props += "Location:&emsp &emsp ";
		props += file.getPath();
		props += "<br/>";
		props += "Size:&ensp &emsp &emsp &emsp ";
		props += getFileSize(file);
		props += "<br/>";
		props += "Created:  &emsp &emsp ";
		props += getCreatedDate(file);
		try {
			if (file.isDirectory()) {
				props += "<br/>";
				props += "Contains:&emsp &emsp ";
				props += countChilds(file);
			}
		} catch (Exception e) {
			
		}
		props += "</html>";
		return props;
	}
	
	private String getType(File file) {
		if (file.isDirectory()) {
			return "Folder";
		} else {
			return "File";
		}
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
	
	private String getFileSize(File file) {
		long bytes;
    	if (!file.isFile()) {
    		bytes = folderSize(file);
    	} else {
    		bytes = file.length();
    	}   
    	
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
	
	private long folderSize(File directory) {
	    long length = 0;
	    for (File file : directory.listFiles()) {
	        if (file.isFile())
	            length += file.length();
	        else
	            length += folderSize(file);
	    }
	    return length;
	}
	
	private String countChilds(File file) {
		String childs = "";
		File[] subFolders = file.listFiles(File::isDirectory);
		File[] subFiles = file.listFiles(File::isFile);
		int numFolders = subFolders.length;
		int numFiles = subFiles.length;
		childs += numFolders;
		childs += " Folders, ";
		childs += numFiles;
		childs += " Files";
		return childs;
	}
}
