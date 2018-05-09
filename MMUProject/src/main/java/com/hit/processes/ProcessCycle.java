package com.hit.processes;

import java.util.Arrays;

public class ProcessCycle extends java.lang.Object{
	
	private java.util.List<java.lang.Long> pages;
	private java.util.List<byte[]> data;
	private int sleepMs;
	
	ProcessCycle(java.util.List<java.lang.Long> pages, int sleepMs, java.util.List<byte[]> data)
	{
		this.pages = pages;
		this.sleepMs = sleepMs;
		this.data = data;
	}

	public java.util.List<byte[]> getData() {
		return data;
	}
	
	public java.util.List<java.lang.Long> getPages() {
		return pages;
	}
	
	public int getSleepMs() {
		return sleepMs;
	}

	public void setData(java.util.List<byte[]> data) {
		this.data = data;
	}
	
	public void setPages(java.util.List<java.lang.Long> pages) {
		this.pages = pages;
	}

	public void setSleepMs(int sleepMs) {
		this.sleepMs = sleepMs;
	}

	
	@Override
	public java.lang.String toString()
	{
		String stringToReturn = "Ids: ";
		for(java.lang.Long page : this.pages) {
			stringToReturn += page.toString();
			stringToReturn += " ";
		}
		stringToReturn += "\nDatas: ";
		
		for(byte[] content : this.data) {
			stringToReturn += Arrays.toString(content);
			stringToReturn += " ";
		}
		stringToReturn += "\nsleep in Ms: ";
		stringToReturn += this.sleepMs;
		return stringToReturn;
	}
	

	
	

}
