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
		
	/**
	 * Only constructor of class with following parameter requirement
	 * @param file the file you want to see properties of it
	 */
	public PropertiesView(File file) {
		this.showMessageDialog(null, getData(file), "Properties", JOptionPane.PLAIN_MESSAGE);
	}
	
	public PropertiesView(File[] files) {
		this.showMessageDialog(null, getData(files), "Properties", JOptionPane.PLAIN_MESSAGE);
	}
	
	//get data of file
	private String getData(File file) {
		String props = "<html>";
		props += "Type:&ensp&emsp &emsp &emsp ";
		props += getType(file);
		props += "<br/>";
		props += "Location:&emsp &emsp ";
		props += file.getParent();
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
	
	private String getData(File[] files) {
		String props = "<html>";
		props += "Location:&emsp &emsp ";
		props += files[0].getParent();
		props += "<br/>";
		props += "Size:&ensp &emsp &emsp &emsp ";
		props += getFilesSize(files);
		props += "<br/>";
		props += "Items: &emsp &emsp &emsp ";
		props += countItems(files);
		props += "</html>";
		return props;
	}
	
	//get type of file
	private String getType(File file) {
		if (file.isDirectory()) {
			return "Folder";
		} else {
			return "File";
		}
	}
	
	//get created date of file
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
	
	// get the size of file
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
	
	private String getFilesSize(File[] files) {
    	long bytes = 0;
    	for (File file :files) {
	    	if (!file.isFile()) {
	    		bytes += folderSize(file);
	    	} else {
	    		bytes += file.length();
	    	}
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
	
	//get the size of folder
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
	
	//count number of childs of a directory
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
	
	private String countItems(File[] files) {
		String items = "";
		int numFolders = 0;
		int numFiles = 0;
		for (File file : files) {
			if (file.isDirectory()) {
				numFolders++;
			} else {
				numFiles++;
			}
		}
		items += numFolders;
		items += " Folders, ";
		items += numFiles;
		items += " Files";
		return items;
	}
}
