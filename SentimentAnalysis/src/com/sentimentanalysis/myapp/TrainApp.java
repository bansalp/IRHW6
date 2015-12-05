package com.sentimentanalysis.myapp;

public class TrainApp 
{
	private static int TWO = 2;
	
	public static void main (String[] args)
	{
		if (args.length == TWO)
		{
			try
			{
				NaiveBayesClassifier classifier = new NaiveBayesClassifier();
				classifier.train(args[0]);
				classifier.serializeObject(args[1]);
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
