package Model;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.table.*;


public class FileTableModel extends AbstractTableModel {

    private File[] files;
    private FileSystemView fileSystemView = FileSystemView.getFileSystemView();
    private String[] columns = {
        "",
        "Name",
        "Date Modified",
        "Type",
        "Size",
    };

    public FileTableModel() {
        this(new File[0]);
    }

    public FileTableModel(File[] files) {
        this.files = files;
    }

    public Object getValueAt(int row, int column) {
        File file = files[row];
        switch (column) {
            case 0:
                return fileSystemView.getSystemIcon(file);
            case 1:
                return fileSystemView.getSystemDisplayName(file);
            case 2:
            	return file.lastModified();
            case 3:
            	if (file.isDirectory())
            		return "File folder";
            	if (file.isFile())
            		return getFileType(file);
//                return file.getPath();
            case 4:
            	return getFileSize(file);
//                return file.length();
            default:
                System.err.println("Logic Error");
        }
        return "";
    }

    public int getColumnCount() {
        return columns.length;
    }

    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0:
                return ImageIcon.class;
            case 2:
            	return Date.class;
            case 4:
                return Long.class;
        }
        return String.class;
    }

    public String getColumnName(int column) {
        return columns[column];
    }

    public int getRowCount() {
        return files.length;
    }

    public File getFile(int row) {
        return files[row];
    }

    public void setFiles(File[] files) {
        this.files = files;
        fireTableDataChanged();
    }
    
//    private void setRClickMenu(File[] files) {
//    	for (File file : files) {
//			file.set
//		}
//    }
    
    private String getFileType(File file) {
    	String extension = "";
    	String fileName = file.getName();
    	int i = fileName.lastIndexOf('.');
    	if (i > 0) {
    	    extension = fileName.substring(i+1);
    	}
    	if (extension.equals("lnk")) {
    		extension = "Shortcut";
    	}
    	return extension;
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
}