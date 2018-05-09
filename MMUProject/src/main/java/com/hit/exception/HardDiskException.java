package com.hit.exception;

public class HardDiskException extends java.io.IOException
{
	public enum ActionError {
		PAGE_FAULT,
		PAGE_REPLACEMENT 
	}
	
	private static final long serialVersionUID = 1L;	
	
	public HardDiskException(){	}
	
	public HardDiskException(java.lang.String msg){
		super(msg);
	}
	public HardDiskException(java.lang.String msg, HardDiskException.ActionError status){
		super(msg + " " + status.toString());
	}
	
	public static HardDiskException.ActionError valueOf(java.lang.String name)
	{
		ActionError error = ActionError.PAGE_REPLACEMENT;
		switch(name)
		{
		case "PAGE_FAULT":
		{
			error = ActionError.PAGE_FAULT;
		}
		}
		
		return error;
	}
	
	static HardDiskException.ActionError[] values()
	{
		return new ActionError[] {ActionError.PAGE_FAULT, ActionError.PAGE_REPLACEMENT};
	}
}
