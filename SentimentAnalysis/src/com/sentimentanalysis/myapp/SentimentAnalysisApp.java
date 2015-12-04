package com.sentimentanalysis.myapp;

public class SentimentAnalysisApp 
{
	public static void main (String[] args)
	{
		try
		{
			String trainDirPath = "textcat/train";
			NaiveBayesClassifier classifier = new NaiveBayesClassifier();
			classifier.train(trainDirPath);
			classifier.printPClass();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
