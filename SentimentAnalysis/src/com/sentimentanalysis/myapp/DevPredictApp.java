package com.sentimentanalysis.myapp;

public class DevPredictApp 
{
	public static void main (String[] args)
	{
		try
		{
			String modelFileName = "model_file.txt";
			String devTestDirName = "textcat/dev";
			String predictionsFileName = "predictions_file.txt";
			
			DevPredict dp = new DevPredict();
			dp.loadModel(modelFileName);
			dp.test(devTestDirName);
			dp.display();
		}
		catch (Exception e)
		{
			e.getStackTrace();
		}
	}
}
