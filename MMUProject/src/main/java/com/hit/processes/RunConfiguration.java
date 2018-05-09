package com.hit.processes;

public class RunConfiguration extends java.lang.Object
{
	
	private java.util.List<ProcessCycles> processesCycles;
	
	RunConfiguration(java.util.List<ProcessCycles> processesCycles)
	{
		this.processesCycles = processesCycles;
	}

	public java.util.List<ProcessCycles> getProcessesCycles() {
		return processesCycles;
	}

	public void setProcessesCycles(java.util.List<ProcessCycles> processesCycles) {
		this.processesCycles = processesCycles;
	}
	
	@Override
	public java.lang.String toString()
	{
		String stringToReturn = "";
		for(ProcessCycles cycles : this.processesCycles) {
			stringToReturn += cycles.toString();
			stringToReturn += "\n\n\n\n";
		}
		
		return stringToReturn;
	}
	
}
