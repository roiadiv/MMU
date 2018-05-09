package com.hit.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.algorithm.MRUAlgoCacheImpl;
import com.hit.algorithm.Random;
import com.hit.memoryunits.MemoryManagementUnit;
import com.hit.memoryunits.Page;
import com.hit.processes.Process;
import com.hit.processes.ProcessCycles;
import com.hit.processes.RunConfiguration;
import com.hit.util.MMULogger;

public class MMUModel extends java.util.Observable implements Model   //Contains logic that runs the whole system
{
	private int numProcesses ;
	private static int ramCapacity;
	private List<Process> processes;
	private static String[] configuration;
	private List<String> logFileData;
	private Map<Long, Page<byte[]>> ramPagesInTheEnd;
	private MemoryManagementUnit mmu;
	private static String filePath = "src\\main\\resources\\Configuration.json";
	
	public void setConfiguration(java.util.List<java.lang.String> configuration)
	{
		this.configuration = (String[]) configuration.toArray();	
	}	
	
	@Override
	public void start() 
	{
		IAlgoCache<Long, Page<byte[]>> algo = null;
		this.ramCapacity = Integer.parseInt(this.configuration[1]);

		switch (this.configuration[0])
		{
		case "LRU":
		{
			algo = new LRUAlgoCacheImpl<>(this.ramCapacity);
			break;	
		}
		case "MRU":
		{
			algo = new MRUAlgoCacheImpl<>(this.ramCapacity);
			break;	
		}
		case "RANDOM":
		{
			algo = new Random<>(this.ramCapacity);
			break;	
		}
		}

		mmu = new MemoryManagementUnit(this.ramCapacity, algo);
		RunConfiguration runConfig = readConfigurationFile();
		List<ProcessCycles> processCycles = runConfig.getProcessesCycles();
		processes = createProcesses(processCycles, mmu);
		this.numProcesses = processes.size();
		MMULogger.getInstance().write("PN:" + this.numProcesses + "\r\n\r\n" , Level.INFO);
	}
	
	public void startProcessProgress(List<Process> actualProcessesToRun)
	{
		runProcesses(actualProcessesToRun);
		try 
		{
			Thread.sleep((long) 100.0);   //The main program needs to be paused a little in order to let all its threads to finish
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		this.ramPagesInTheEnd = mmu.getRam().getPages();
		readLogFile();
		setChanged();
		notifyObservers(this.logFileData);
	}
	
	
	public static RunConfiguration readConfigurationFile()
	{
		RunConfiguration config = null;
		
		if(configuration[2].equals("LOCAL")) {
		try 
		{
			config =  new Gson().fromJson(new JsonReader(new FileReader(filePath)), RunConfiguration.class);
		}
		catch (JsonSyntaxException e) 
		{
			e.printStackTrace();
		} 
		catch (JsonIOException e) 
		{
			e.printStackTrace();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		}
		else {
			try 
			{
				config =  new Gson().fromJson(filePath, RunConfiguration.class);
			}
			catch (JsonSyntaxException e) 
			{
				e.printStackTrace();
			} 
			catch (JsonIOException e) 
			{
				e.printStackTrace();
			}
		}
		return config;
	}
	
	public static List<Process> createProcesses(List<ProcessCycles> appliocationsScenarios, MemoryManagementUnit mmu)
	{
		List<Process> processes = new LinkedList<>();
		int processId = 0;

		for (ProcessCycles cycles : appliocationsScenarios)
		{
			processes.add(new Process(processId, mmu, cycles));
			processId++;
		}
		
		return processes;
	}

	public static void runProcesses(java.util.List<Process> applications)
	{
		for (Process app : applications)
		{
			FutureTask task = new FutureTask(app);
			Thread currentThread = new Thread(task);
			currentThread.start();
		}
	}
	
	public void readLogFile() 
	{
		try 
		{
			this.logFileData = Files.readAllLines(Paths.get(MMULogger.DEFAULT_FILE_NAME));
		} 
		catch (IOException exception) 
		{
			MMULogger.getInstance().write(exception.getMessage(), Level.SEVERE);
		}
	}

	public int getNumProcesses() 
	{
		return numProcesses;
	}
	
	public List<Process> getProcesses()
	{
		return this.processes;
	}

	public Map<Long, Page<byte[]>> getRamPagesInTheEnd() 
	{
		return ramPagesInTheEnd;
	}	
	
	public static void setConfiguration(String newConfiguration) 
	{
		filePath = newConfiguration;
	}
}
