package com.hit.algorithm;

public class TestClass 
{
	public static void main(String[] args)
	{
		System.out.println("Hello World!!!");
		Random<Integer, String> base = new Random<>(7);
		base.putElement(1, "Ichi");	
		base.putElement(2, "Ni");	
		base.putElement(3, "San");	
		base.putElement(4, "Yon");	
		base.putElement(5, "Go");
		base.putElement(6, "Roku");
		base.putElement(7, "Nana");
		base.putElement(8, "Hachi");
		base.putElement(9, "Kyu");
		base.printTest();
	}
}
