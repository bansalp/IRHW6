package com.textclassification.myapp;

import ir.classifiers.NaiveBayes;

public class TextClassificationApp 
{
	public static void main (String[] args) throws Exception
	{
		GenerateARFF generateARFF = new GenerateARFF();
		generateARFF.addInstance("Parth      is a good boy \n", "positive");
		generateARFF.addInstance("Parth      is an intelligent boy \n", "positive");
		generateARFF.addInstance("Parth      is a bad boy \n", "negative");
		generateARFF.addInstance("      is a Parth Parth worse boy \n", "negative");
		generateARFF.addInstance("Parth is a smart boy", "positive");
		
		NaiveBayes cModel = new NaiveBayes(new String[] {"filename"}, false);
		//cModel.setOptions(new String[] {"-A", "1.0"});
		//cModel.buildClassifier(generateARFF.getFilteredInstances());		
		
		//SimpleEstimator se = new SimpleEstimator();
		//se.setAlpha(1.0);
		//cModel.setEstimator(se);
		
		//String[] options = cModel.getOptions();
		//for (String s: options)
		//	System.out.println(s);
		
		System.out.println(cModel.getIsLaplace());
	}
}
