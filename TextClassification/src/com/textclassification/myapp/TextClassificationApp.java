package com.textclassification.myapp;

import weka.classifiers.bayes.NaiveBayes;

public class TextClassificationApp 
{
	public static void main (String[] args) throws Exception
	{
		try
		{
			String trainDirName = "textcat/train";  
			
			GenerateARFF generateARFF = new GenerateARFF();
			generateARFF.addDirectory(trainDirName);
			
			NaiveBayes cModel = new NaiveBayes();
			cModel.buildClassifier(generateARFF.getFilteredInstances());
			
			//cModel.setOptions(new String[] {"-A", "1.0"});
			//cModel.buildClassifier(generateARFF.getFilteredInstances());
			
			//SimpleEstimator se = new SimpleEstimator();
			//se.setAlpha(1.0);
			//cModel.setEstimator(se);
			
			//String[] options = cModel.getOptions();
			//for (String s: options)
			//	System.out.println(s);
			
			//System.out.println(cModel);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
