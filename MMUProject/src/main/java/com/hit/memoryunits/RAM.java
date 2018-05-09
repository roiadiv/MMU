package com.hit.memoryunits;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RAM extends java.lang.Object
{
	private int initialCapacity;
	private java.util.Map<java.lang.Long, Page<byte[]>> pages;
	
	public RAM(int initialCapacity)
	{
		this.initialCapacity = initialCapacity;
		this.pages = new LinkedHashMap<java.lang.Long, Page<byte[]>>(this.initialCapacity);
	}
	
	public void addPage(Page<byte[]> addPage)
	{
		if (this.pages.size() <= this.initialCapacity)
		{
			this.pages.put(addPage.getPageId(), addPage);
		}
	}
	
	public void addPages(Page<byte[]>[] addPages)
	{
		for (Page<byte[]> page : addPages)
		{
			addPage(page);
		}
	}
	
	public int getInitialCapacity() 
	{
		return initialCapacity;
	}
	
	public Page<byte[]> getPage(java.lang.Long pageId)
	{
		Page<byte[]> pageToReturn = null;
		if (this.pages.containsKey(pageId))
		{
			pageToReturn = this.pages.get(pageId);
		}
		
		return pageToReturn;
	}
	
	
	public java.util.Map<java.lang.Long, Page<byte[]>> getPages() 
	{
		return pages;
	}

	public Page<byte[]>[] getPages(java.lang.Long[] pageIds)
	{
		List<Page<byte[]>> pagesToReturn = new ArrayList<Page<byte[]>>();
		for (int i = 0; i < pageIds.length; ++i)
		{
			pagesToReturn.add(getPage(pageIds[i]));
		}
		
		return (Page<byte[]>[]) pagesToReturn.toArray();
	}
	
	public void removePage(Page<byte[]> removePage)
	{
		this.pages.remove(removePage.getPageId());
	}
	
	public void removePage(Page<byte[]>[] removePages)
	{
		for (Page<byte[]> page : removePages)
		{
			removePage(page);
		}
	}	

	public void setInitialCapacity(int initialCapacity) 
	{
		this.initialCapacity = initialCapacity;
	}
	
	public void setPages(java.util.Map<java.lang.Long, Page<byte[]>> pageMap) {
		this.pages = pageMap;
	}		
}
