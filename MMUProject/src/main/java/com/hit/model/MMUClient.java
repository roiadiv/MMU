package com.hit.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class MMUClient 
{
	public static Object getJSONFromServer(String[] req)
	{
		Object json = null;
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		Socket myServer = null;
		try
		{
			InetAddress address = InetAddress.getLocalHost();
			myServer = new Socket (address, 12345);
			out = new ObjectOutputStream(myServer.getOutputStream());
			out.writeObject(req[0]);
			out.writeObject(req[1]);
			out.writeObject(req[2]);
			in = new ObjectInputStream(myServer.getInputStream());
			json = (Object) in.readObject();			
			if (json.toString().equals("No Access"))
			{
				System.out.println("Wrong username or password");
				json = null;
			}
			if (json.toString().equals("ERROR: File Not Found"))
			{
				System.out.println("File Not Found");
				json = null;
			}
			
		}
		catch (Exception e)
		{
		}
		finally
		{
			if(out !=null)
			{
				try
				{
					out.close();
				}
				catch (Exception e)
				{}
			}

			if (in != null)
			{
				try {
					in.close();
				}
				catch 
				(Exception e)
				{}
			}
			if(myServer != null)
			{
				try{
					myServer.close();
				}
				catch(Exception e){}
			}
			
		}
		
		return json;
	}
}
