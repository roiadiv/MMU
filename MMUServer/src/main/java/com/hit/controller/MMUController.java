package com.hit.controller;
import java.net.ServerSocket;
import java.net.Socket;
import com.hit.businesslogic.MMUConfigFileFacade;
import com.hit.businesslogic.MMUConfigFileService;
import com.hit.login.AuthenticationService;
import java.util.concurrent.ExecutorService;

public class MMUController extends java.lang.Object
{
	private boolean isServerUp = true;
	
	public MMUController()
	{
	}
	
	public MMUController(AuthenticationService authenticationManager, MMUConfigFileFacade mmuLogService) 
	{
	}
	
	public void start()   //Runs the server
	{
		try {
			ServerSocket server = new ServerSocket(12345);
			while(isServerUp)
			{
				Socket socket = server.accept();
				new Thread(new MMUConfigFileService(socket)).start();
			}
			server.close();
		} catch (Exception e)
		{
			System.out.println("Server error");
		}
	}
}
