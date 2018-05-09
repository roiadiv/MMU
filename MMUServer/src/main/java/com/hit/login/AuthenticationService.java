package com.hit.login;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.hit.controller.MMUController;
import com.hit.dm.User;


public class AuthenticationService extends java.lang.Object
{
	private List<User> users;
	
	public AuthenticationService()
	{
		readUsersFile();
	}

	private void readUsersFile()   //Reads fron json file the authorized Users
	{
		users = null;
		java.lang.reflect.Type type = new TypeToken<List<User>>(){}.getType();
		String filePath = "src\\main\\resources\\com\\hit\\login\\Users.json";

		try 
		{
			users =  new Gson().fromJson(new JsonReader(new FileReader(filePath)), type);
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

	public boolean authenticate(java.lang.String user, java.lang.String password)   //Checks if User is exist
	{
		boolean isAuthenticated = false;
		for (User currentUser : users) {
			if(currentUser.getPassword().equals(password) && currentUser.getUserName().equals(user)) 
			{
				isAuthenticated = true;
				break;
			}
		}
		
		return isAuthenticated;
	}
}
