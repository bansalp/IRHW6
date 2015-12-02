package com.textclassification.myapp;

public class TextClassificationApp 
{
	public static void main (String[] args)
	{
		try
		{
			String trainData = "textcat/train";
			String trainFile = "train.csv";
			String testData = "textcat/test";
			String testFile = "test.csv";
			
			GenerateARFF trainArff = new GenerateARFF();
			trainArff.addDirectory(trainData, true);
			trainArff.createFile(trainFile, true);
			
			GenerateARFF testArff = new GenerateARFF();
			testArff.addDirectory(testData, false);
			testArff.createFile(testFile, false);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}