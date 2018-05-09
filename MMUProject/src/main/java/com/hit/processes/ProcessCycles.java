package com.hit.processes;

public class ProcessCycles extends java.lang.Object{
	
	private java.util.List<ProcessCycle> processCycles;
	
	ProcessCycles(java.util.List<ProcessCycle> processCycles)
	{
		this.processCycles = processCycles;
	}

	public java.util.List<ProcessCycle> getProcessCycles() {
		return processCycles;
	}

	public void setProcessCycles(java.util.List<ProcessCycle> processCycles) {
		this.processCycles = processCycles;
	}
	
	@Override
	public java.lang.String toString()
	{
		String stringToReturn = "";
		for(ProcessCycle cycle : this.processCycles) {
			stringToReturn += cycle.toString();
			stringToReturn += "\n\n";
		}
		
		return stringToReturn;
	}	
}
