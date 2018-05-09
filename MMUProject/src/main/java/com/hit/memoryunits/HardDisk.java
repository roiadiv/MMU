package com.hit.memoryunits;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.hit.exception.HardDiskException;
import com.hit.util.MMULogger;

public class HardDisk extends java.lang.Object
{
	private static final int _SIZE = 100;
	private static final String DEFAULT_FILE_NAME = "HardDiskPages.bin";
	private static HardDisk instanceOfHardDisk = null;
	private java.util.Map<java.lang.Long, Page<byte[]>> pages;

	private HardDisk() throws HardDiskException   //Constructor that creates the memory inside the hd
	{
		this.pages = new LinkedHashMap<java.lang.Long, Page<byte[]>>(_SIZE);
		for (java.lang.Long i = (long) 0; i < _SIZE; ++i)
		{
			this.pages.put(i, new Page<byte[]>(i, new byte[] {0}));
		}

		updateFile();
	}

	public static HardDisk getInstance() throws HardDiskException   //Singleton pattern
	{
		if(instanceOfHardDisk == null) 
		{
			instanceOfHardDisk = new HardDisk();
		}
		return instanceOfHardDisk;
	}

	public Page<byte[]> pageFault(java.lang.Long pageId)throws HardDiskException
	{
		return this.pages.get(pageId);
	}

	public Page<byte[]> pageReplacement(Page<byte[]> moveToHdPage ,java.lang.Long moveToRamId)throws HardDiskException
	{
		if(!pages.containsKey(moveToHdPage.getPageId())) 
		{
			pages.put(moveToHdPage.getPageId(), moveToHdPage);
		}	
		return pageFault(moveToRamId);
	}

	public void updateFile()   //Saving the memory of the hd into a file
	{
		ObjectOutputStream outputStream = null;
		try {			
			outputStream = new ObjectOutputStream(new FileOutputStream(DEFAULT_FILE_NAME));

			for (java.lang.Long key : this.pages.keySet())
			{
				outputStream.writeObject(this.pages.get(key));
			}

			outputStream.close();			
		} 
		catch (FileNotFoundException fnfe) 
		{
			MMULogger.getInstance().write(fnfe.getMessage() + "\r\n", Level.SEVERE);
		} 
		catch (IOException ioe) 
		{
			MMULogger.getInstance().write(ioe.getMessage() + "\r\n", Level.SEVERE);
		}
	}

	public Map<java.lang.Long, Page<byte[]>> getMap()
	{
		return this.pages;
	}
}
