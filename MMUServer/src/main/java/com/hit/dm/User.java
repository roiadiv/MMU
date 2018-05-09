package com.hit.dm;

public class User extends java.lang.Object   //Basic 'User' class for reading from the json file
{
	private String userName;
	private String password;
	
	public User(String userName, String password)
	{
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() 
	{
		return userName;
	}

	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	@Override
	public String toString()
	{
		return "Username: " + this.userName + ", Password: " + this.password;
	}
}
