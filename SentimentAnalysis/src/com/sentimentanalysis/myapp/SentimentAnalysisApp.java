package com.sentimentanalysis.myapp;

public class SentimentAnalysisApp 
{
	public static void main (String[] args)
	{
		try
		{
			String trainDirPath = "textcat/train_copy";
			NaiveBayesClassifier classifier = new NaiveBayesClassifier();
			classifier.train(trainDirPath);
			classifier.printPClass();
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
	}
}
