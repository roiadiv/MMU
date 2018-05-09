package com.hit.memoryunits;

public class Page<T> extends java.lang.Object implements java.io.Serializable
{
	private java.lang.Long pageId;
	private T content;
	
	public Page()
	{		
	}
	
	public Page(java.lang.Long id, T content)
	{
		this.pageId = id;
		this.content = content;
	}
	
	@Override
	public boolean equals(java.lang.Object obj)
	{
		boolean isPageSame = false;
		if (obj.getClass() == this.getClass())
		{
			Page<T> checkedObj = (Page<T>)obj;

			if(this.pageId == checkedObj.getPageId() && this.content == checkedObj.getContent())
			{
				isPageSame = true;
			}
		}

		return isPageSame;
	}
	
	public T getContent() 
	{
		return content;
	}

	public java.lang.Long getPageId() 
	{
		return pageId;
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
	
	public void setContent(T content) 
	{
		this.content = content;
	}
	
	public void setPageId(java.lang.Long pageId) 
	{
		this.pageId = pageId;
	}
	
	@Override
	public java.lang.String toString()
	{
		return this.pageId.toString() + " " + this.content.toString();
	}
}
