package com.hit.businesslogic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class MMUConfigFileFacade extends java.lang.Object
{
	private List<String> FileNamesList;
	
	public MMUConfigFileFacade() 
	{
		readFileNamesFile();
	}
	
	public java.lang.String getFile(java.lang.String filename)   //Checks if data file is exist
	{
		String fileNameToReturn = null;
		if(FileNamesList.contains(filename))
		{
			fileNameToReturn = filename;
		}
		
		return fileNameToReturn;
	}
	
	private void readFileNamesFile()   //Reads fron json file the exist data files
	{
		FileNamesList = null;
		java.lang.reflect.Type type = new TypeToken<List<String>>(){}.getType();
		String filePath = "src\\main\\resources\\com\\hit\\data\\FileNames.json";

		try 
		{
			FileNamesList =  new Gson().fromJson(new JsonReader(new FileReader(filePath)), type);
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

}
