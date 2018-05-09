package com.hit.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class MMULogger extends java.lang.Object   //Writes to a text file what happens in the whole process
{
	public final static String DEFAULT_FILE_NAME = "logs\\log.txt";
	private FileHandler handler;
	private static MMULogger instanceOfMMULogger = null;
	
	private MMULogger()
	{
		try
		{
			handler = new FileHandler(DEFAULT_FILE_NAME);
			handler.setFormatter(new OnlyMessageFormatter());
		}
		catch(IOException ex)
		{
			System.err.println("Could not create handler" + ex.getMessage());
		}
	}
	
	public static MMULogger getInstance()   //Singleton pattern
	{
		if(instanceOfMMULogger == null) {
			instanceOfMMULogger = new MMULogger();
		}
		return instanceOfMMULogger;
	}
	
	public synchronized void write(String command, Level level)
	{
		handler.publish(new LogRecord(level, command));
	}
	
	public class OnlyMessageFormatter extends Formatter
	{
		public OnlyMessageFormatter() { super(); }
		
		@Override
		public String format(final LogRecord record)
		{
			return record.getMessage();
		}
	}
}
