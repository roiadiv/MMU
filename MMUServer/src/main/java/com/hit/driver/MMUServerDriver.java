package com.hit.driver;

import java.io.IOException;

import com.hit.controller.MMUController;

public class MMUServerDriver 
{
	public static void main(String[] args) throws IOException   //From the lecturer
	{
		new MMUController().start();
	}
}
