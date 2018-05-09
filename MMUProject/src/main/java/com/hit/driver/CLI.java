package com.hit.driver;

import java.io.PrintWriter;
import java.util.Observable;
import java.util.Scanner;

public class CLI extends Observable implements java.lang.Runnable
{
	public static final java.lang.String LRU = "LRU";
	public static final java.lang.String MRU = "MRU";
	public static final java.lang.String RANDOM = "RANDOM";
	public static final java.lang.String START = "start";
	public static final java.lang.String STOP = "stop";
	private Scanner in;
	private PrintWriter out;


	CLI(java.io.InputStream in, java.io.OutputStream out) 
	{
		this.in = new Scanner(in);
		this.out = new PrintWriter(out);
	}

	@Override
	public void run() 
	{
		String inCommand = in.nextLine();
		while (!inCommand.equals(START) && !inCommand.equals(STOP))
		{
			write("Not a valid command");
			inCommand = in.nextLine();
		}

		if(inCommand.equals(START))
		{
			do
			{
				write("Please enter required algorithm, RAM capacity and MODE(LOCAL or REMOTE):");
				inCommand = in.nextLine();
			}
			while(!checkIfInputIsValid(inCommand));

			setChanged();
			notifyObservers(inCommand.split(" "));
		}
		else
		{
			write("Thank you");
		}

	}

	public void write(java.lang.String string) 
	{
		this.out.println(string);
		this.out.flush();
	}

	private boolean checkIfInputIsValid(String input)   //The input must be in a way thet the kind of the cache algorithm is first and then the ram capacity
	{
		boolean isValidInput = true;
		String[] splittedInput = input.split(" ");
		if (splittedInput.length != 3)
		{
			write("Not a valid command");
			isValidInput = false;
		}
		else if(!checkAlgo(splittedInput[0]) || !checkRamCapacity(splittedInput[1]) || !checkMode(splittedInput[2])) {
			write("Not a valid command");
			isValidInput = false;
		}

		return isValidInput;
	}

	private boolean checkAlgo(String checkedString)   //Checks if cache algorithm is proper
	{
		boolean isCurrectAlgo = false;
		if (checkedString.equals("LRU") || checkedString.equals("MRU") || checkedString.equals("RANDOM"))
		{
			isCurrectAlgo = true;
		}

		return isCurrectAlgo;
	}

	private boolean checkRamCapacity(String checkedString)   //Checks if ram capacity is proper
	{
		boolean isCorrectRamCapacity = true;
		try
		{
			Integer.parseInt(checkedString);
		}
		catch (Exception ex)
		{
			isCorrectRamCapacity = false;
		}

		return isCorrectRamCapacity;
	}

	private boolean checkMode(String checkedString)   //Checks if mode is proper
	{
		boolean isCorrectMode = false;
		if (checkedString.equals("LOCAL") || checkedString.equals("REMOTE"))
		{
			isCorrectMode = true;
		}

		return isCorrectMode;
	}
}
