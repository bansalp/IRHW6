package com.textclassification.myapp;

import java.io.PrintWriter;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class TextClassificationApp 
{
	public static void main (String[] args)
	{
		try
		{
			String trainDirName = "textcat/train";
			String testDataDirName = "textcat/test";
			
			GenerateARFF generateARFF = new GenerateARFF();
			generateARFF.addDirectory(trainDirName);
			
			NaiveBayes cModel = new NaiveBayes();
			cModel.buildClassifier(generateARFF.getFilteredInstances());
			
			GenerateARFFInstances tdta = new GenerateARFFInstances();
			tdta.createDataset(testDataDirName);
			Instances dataset = tdta.getInstances();  
			
			PrintWriter writer = new PrintWriter("dataset.txt", "UTF-8");
			writer.println(dataset);
			writer.close();
			
			/*NGramTokenizer tokenizer = new NGramTokenizer(); 
			tokenizer.setNGramMinSize(1); 
			tokenizer.setNGramMaxSize(1); 
			tokenizer.setDelimiters("\\s+\\n");
			
			// Set the filter 
			StringToWordVector filter = new StringToWordVector(); 
			filter.setInputFormat(dataset); 
			filter.setTokenizer(tokenizer); 
			filter.setDoNotOperateOnPerClassBasis(true);
			filter.setMinTermFreq(5);
			
			//Instances resultDataset = Filter.useFilter(dataset, filter);
			Instances resultDataset = Filter.useFilter(dataset, filter);
			PrintWriter writer = new PrintWriter("temp.txt", "UTF-8");
			writer.println(resultDataset);
			writer.close();
			
			Instances labeled = new Instances(resultDataset);
			
		    for (int index = 0; index < resultDataset.numInstances(); index++) 
		    {
		        double clsLabel = cModel.classifyInstance(resultDataset.instance(index));
			    labeled.instance(index).setClassValue(clsLabel);
		    }
		    
		    System.err.println(labeled.toString());
		    
		    PrintWriter writer1 = new PrintWriter("tempresult.txt", "UTF-8");
		    
		    for (int i = 0; i < labeled.numInstances(); i++)
		    {
		    	writer1.println(labeled.get(i).stringValue(1));
		    }
		    
			writer1.close();*/
			
			//Evaluation eTest = new Evaluation(generateARFF.getFilteredInstances());
			//eTest.evaluateModel(cModel, dataset);
			
			//String strSummary = eTest.toSummaryString();
			//System.out.println(strSummary);
			
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