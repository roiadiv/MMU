package com.hit.driver;

import java.util.Arrays;

import com.hit.controller.MMUController;
import com.hit.model.MMUModel;
import com.hit.view.LoginView;
import com.hit.view.MMUView;
import com.hit.view.View;

public class MMUDriver extends java.lang.Object
{
	public MMUDriver() 
	{
	}

	public static void main(java.lang.String[] args)   //From the lecturer 
	{		
		CLI cli = new CLI(System.in, System.out);
		MMUModel model = new MMUModel();
		MMUView view = new MMUView();
		LoginView loginView = new LoginView(); 
		View[] views = { view , loginView };
		MMUController controller = new MMUController(model, views);
		model.addObserver(controller);
		cli.addObserver(controller);
		view.addObserver(controller);
		loginView.addObserver(controller);
		new Thread(cli).start();
	}
}
