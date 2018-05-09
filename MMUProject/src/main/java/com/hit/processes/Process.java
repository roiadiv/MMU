package com.hit.processes;

import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;

import com.hit.memoryunits.MemoryManagementUnit;
import com.hit.memoryunits.Page;
import com.hit.util.MMULogger;

public class Process extends java.lang.Object implements java.util.concurrent.Callable<java.lang.Boolean>
{
	private int id;
	private MemoryManagementUnit mmu;
	private ProcessCycles processCycles;

	public Process(int id, MemoryManagementUnit mmu, ProcessCycles processCycles)
	{
		this.id = id;
		this.mmu = mmu;
		this.processCycles = processCycles;
	}

	@Override
	public Boolean call() throws java.lang.Exception
	{
		Boolean isCallFinishedSuccessfully = true;
		try 
		{	
			for (ProcessCycle cycle : this.processCycles.getProcessCycles())
			{
				synchronized (this.mmu) {
					Page<byte[]>[] pagesFromRam = this.mmu.getPages(convertPageListToPageArray(cycle.getPages()));
					
					for (int i = 0; i < pagesFromRam.length; i++) 
					{
						pagesFromRam[i].setContent(cycle.getData().get(i));
						MMULogger.getInstance().write("GP:P" + this.id + " " + pagesFromRam[i].getPageId() + " " + Arrays.toString((byte[]) pagesFromRam[i].getContent()) + "\r\n\r\n" , Level.INFO);
					}					
				}		

				Thread.sleep(cycle.getSleepMs());
			}

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			isCallFinishedSuccessfully = false;
		}

		return isCallFinishedSuccessfully;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private java.lang.Long[] convertPageListToPageArray(java.util.List<java.lang.Long> pageList)
	{
		java.lang.Long[] pageArray = new java.lang.Long[pageList.size()];
		int i = 0;
		for (java.lang.Long currentPage : pageList)
		{
			pageArray[i] = currentPage;
			i++;
		}

		return pageArray;
	}
}
