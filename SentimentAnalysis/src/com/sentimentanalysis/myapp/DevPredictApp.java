package com.sentimentanalysis.myapp;

public class DevPredictApp 
{
	private static int THREE = 3;
	
	public static void main (String[] args)
	{
		if (args.length == THREE)
		{
			try
			{
				DevPredict dp = new DevPredict();
				dp.loadModel(args[0]);
				dp.test(args[1]);
				dp.display(args[2]);
			}
			catch (Exception e)
			{
				System.out.println("Error: " + e.getMessage());
			}
		}
		else
		{
			System.out.println("Illegal number of arguments!"); 
		}
	}
}