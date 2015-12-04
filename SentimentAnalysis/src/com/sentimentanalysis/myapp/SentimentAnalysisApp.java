package com.sentimentanalysis.myapp;

public class SentimentAnalysisApp 
{
	public static void main (String[] args)
	{
		try
		{
			//String trainDirPath = "textcat/train";
			//DirectoryLoader dl = new DirectoryLoader();
			//dl.loadDir(trainDirPath);
			
			String str = "Hey hi .       hello parth   \n hmm tum   \n          ";
			String[] array = str.split("[\r\n|\\n|\\s]+");
			for (String s: array)
			{
				System.out.print(s);
			}
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
	}
}
