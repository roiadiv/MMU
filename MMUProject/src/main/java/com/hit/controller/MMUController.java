package com.hit.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;

import com.hit.driver.CLI;
import com.hit.model.MMUClient;
import com.hit.model.MMUModel;
import com.hit.model.Model;
import com.hit.util.MMULogger;
import com.hit.view.LoginView;
import com.hit.view.MMUView;
import com.hit.view.View;

public class MMUController extends java.lang.Object implements Controller, java.util.Observer
{
	private Model model;
	private View[] views;
	private String[] dataToGet;   //Saves the initial initial input from clint 
	
	public MMUController(Model model, View[] views)
	{
		this.model = model;
		this.views = views;
	}
	
	@Override
	public void update(Observable caller, Object data)   //Checks who called it and treat it accordingly 
	{	
		if (caller instanceof CLI)
		{
			if ((((String[])data)[2]).equals("LOCAL"))
			{
			((MMUView) this.views[0]).setPageColNumber((Integer.parseInt(((String[])data)[1])));
			((MMUModel) this.model).setConfiguration(Arrays.asList((String[])data));
			((MMUModel) this.model).start();
			((MMUView) this.views[0]).setNumberOfProcess(((MMUModel)this.model).getNumProcesses());
			((MMUView) this.views[0]).start();
			}
			else
			{
				dataToGet = (String[])data;
				((LoginView) this.views[1]).start();
			}
		}
		else if (caller instanceof MMUModel)
		{		
			((MMUView) this.views[0]).UpdateCounters((List<String>) data);
			((MMUView) this.views[0]).UpdateTable(((MMUModel) this.model).getRamPagesInTheEnd());
			
		}
		else if (caller instanceof MMUView)
		{
			List<com.hit.processes.Process> actualProcessesToRun = new LinkedList<>();
			for (com.hit.processes.Process currentCheckedProcess : ((MMUModel) this.model).getProcesses())
			{
				for (int i = 0; i < ((int[]) data).length; i++)
				{
					if (currentCheckedProcess.getId() == ((int[]) data)[i])
					{
						actualProcessesToRun.add(currentCheckedProcess);
						break;
					}
				}
			}
			
			((MMUModel) this.model).startProcessProgress(actualProcessesToRun);
		}
		
		else if (caller instanceof LoginView)
		{
			
			Object json = MMUClient.getJSONFromServer((String[])data);
			if (json != null && !json.toString().equals("No Access"))
			{
				MMUModel m = (MMUModel) model;
				m.setConfiguration(Arrays.asList(dataToGet));
				m.setConfiguration((String) json.toString());
				m.start();
				((MMUView) this.views[0]).setPageColNumber(Integer.parseInt(dataToGet[1]));
				((MMUView) this.views[0]).setNumberOfProcess(((MMUModel)this.model).getNumProcesses());
				((MMUView) this.views[0]).start();
			}
		}
	
		
	}
}
