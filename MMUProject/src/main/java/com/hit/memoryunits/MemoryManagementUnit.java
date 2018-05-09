package com.hit.memoryunits;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.algorithm.MRUAlgoCacheImpl;
import com.hit.algorithm.Random;
import com.hit.exception.HardDiskException;
import com.hit.util.MMULogger;

public class MemoryManagementUnit extends java.lang.Object
{
	private IAlgoCache<Long, Page<byte[]>> algo;
	private RAM ram;

	public MemoryManagementUnit(int ramCapacity, IAlgoCache<Long, Page<byte[]>> algo) 
	{
		this.algo = algo;
		this.ram = new RAM(ramCapacity);
		MMULogger.getInstance().write("RC:" + ramCapacity + "\r\n", Level.INFO);
	}

	public IAlgoCache<Long, Page<byte[]>> getAlgo() 
	{
		return algo;
	}

	public Page<byte[]>[] getPages(java.lang.Long[] pageIds) throws java.io.IOException   //From the lecturer
	{
		List<Page<byte[]>> pagesToReturn = new ArrayList<Page<byte[]>>();
		for(java.lang.Long id : pageIds) 
		{
			if(this.ram.getPage(id) == null)
			{
				Page<byte[]> page = HardDisk.getInstance().pageFault(id);
				pagesToReturn.add(page);
				if(this.ram.getPages().size() < this.ram.getInitialCapacity())
				{
					this.ram.addPage(page);
					this.algo.putElement(page.getPageId(), page);
					MMULogger.getInstance().write("PF: " + id  + "\r\n", Level.INFO);
				}
				else 
				{
					Page<byte[]> pageToHD = this.algo.putElement(page.getPageId(), page);
					HardDisk.getInstance().pageReplacement(pageToHD, id);
					MMULogger.getInstance().write("PR:MTH " + pageToHD.getPageId() + " MTR " + id + "\r\n" , Level.INFO);
					this.ram.removePage(pageToHD);
					this.ram.addPage(page);
				}
			}	
			else
			{
				pagesToReturn.add(this.ram.getPage(id));
			}
		}

		return convertToPageArray(pagesToReturn);
	}

	private Page<byte[]>[] convertToPageArray(List<Page<byte[]>> pagesToConvert)   //Convert list of pages to array of pages
	{
		Page<byte[]>[] pagesToReturn = new Page[pagesToConvert.size()];
		for(int i = 0 ; i < pagesToConvert.size() ; ++i)
		{
			pagesToReturn[i] = pagesToConvert.get(i);
		}
		return pagesToReturn;

	}

	public RAM getRam() {
		return ram;
	}

	public void setAlgo(IAlgoCache algo) 
	{
		this.algo = algo;
	}


	public void setRam(RAM ram)
	{
		this.ram = ram;
	}

	public void shutDown()   //Shuts down the mmu by 'erasing' its memory 
	{
		update();
		this.ram = null;
		this.algo = null;
	}

	public void update()   //Including the bonus
	{
		for (java.lang.Long key : ram.getPages().keySet()) 
		{
			try
			{
				HardDisk.getInstance().getMap().put(key, ram.getPage(key));
			} 
			catch (HardDiskException hde)
			{
				MMULogger.getInstance().write(hde.getMessage() + "\r\n", Level.SEVERE);
			}

		}
		try 
		{
			HardDisk.getInstance().updateFile();
		} 
		catch (HardDiskException hde) 
		{
			MMULogger.getInstance().write(hde.getMessage() + "\r\n", Level.SEVERE);
		}
	}

}
