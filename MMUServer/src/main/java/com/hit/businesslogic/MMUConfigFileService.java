package com.hit.businesslogic;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.hit.login.AuthenticationService;

public class MMUConfigFileService implements Runnable   //The kind of the Thread that the server runs to communicate with the client
{	
	private Socket socket;
	private String[] dataFromClient;
	
	public MMUConfigFileService(java.net.Socket socket) throws ClassNotFoundException, IOException
	{
		this.socket = socket;
		this.dataFromClient = new String[3];
		ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
		this.dataFromClient[0] = (String) input.readObject(); 
		this.dataFromClient[1] = (String)input.readObject(); 
		this.dataFromClient[2] = (String)input.readObject(); 
	}
	
	@Override
	public void run()   //Runs the Thread that communicates with the client
	{
		try {
			AuthenticationService authenticationService = new AuthenticationService();
			if(authenticationService.authenticate(this.dataFromClient[0], this.dataFromClient[1]))
			{
				MMUConfigFileFacade configFileFacade = new MMUConfigFileFacade();
				this.socket.setKeepAlive(true);
				ObjectOutputStream output = new ObjectOutputStream(this.socket.getOutputStream());
				if(configFileFacade.getFile(this.dataFromClient[2])!= null)
				{
					Object jsonConfigFile = new Gson().fromJson(new JsonReader(new FileReader("src/main/resources/com/hit/data/" + this.dataFromClient[2])), Object.class);
					output.writeObject(jsonConfigFile.toString());
				}
				else
				{
					output.writeObject("ERROR: File Not Found");
					output.writeObject(null);
				}
				
				output.flush();
				output.close(); 
			}
			else
			{
				ObjectOutputStream output = new ObjectOutputStream(this.socket.getOutputStream());
				output.writeObject("No Access");
				output.close();
			}
		} 
		catch (JsonIOException | JsonSyntaxException | IOException e) 
		{
			e.printStackTrace();
		}
	}
}
