package com.sentimentanalysis.myapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class NaiveBayesClassifier 
{
	private PClass pClass = new PClass();
	private PWClass pWClass = new PWClass();
	private String TOKENIZER_PATTERN = "[\r\n|\\n|\\s]+";
	
	public void train(String dirName) throws IOException 
	{
		addDirectory(dirName);
	}
	
	public void addDirectory(String dirName) throws IOException
	{
		File[] files = new File(dirName).listFiles();
	    addFiles(dirName, files);
	}
	
	public void addFiles(String dirName, File[] files) throws IOException 
	{
	    for (File file : files) 
	    {
	        if (file.isDirectory()) 
	        {
	            addFiles(file.getName(), file.listFiles());
	        } 
	        else 
	        {
	        	if (file.getName().endsWith(".txt"))
	        	{
	        		addFileContent(dirName, file.getPath());
	        	}
	        }
	    }
	}
	
	public void addFileContent(String dirName, String filePath) throws IOException
	{
		File file = new File (filePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		StringBuilder sb = new StringBuilder();
		String line = null;
		
		while ((line = br.readLine()) != null)
		{
			pWClass.addWords(line.split(TOKENIZER_PATTERN), dirName);
		}
		
		pClass.setNc(dirName);
	}
	
	public void printPClass()
	{
		System.out.println("P(pos): " + pClass.getPcPos());
		System.out.println("P(neg): " + pClass.getPcNeg());
		
		System.out.println("P(chinese|pos): " + pWClass.getCountWC("chinese", "pos"));
		System.out.println("P(tokyo|pos): " + pWClass.getCountWC("tokyo", "pos"));
		System.out.println("P(japan|pos): " + pWClass.getCountWC("japan", "pos"));
		
		System.out.println();
		
		System.out.println("P(chinese|neg): " + pWClass.getCountWC("chinese", "neg"));
		System.out.println("P(tokyo|neg): " + pWClass.getCountWC("tokyo", "neg"));
		System.out.println("P(japan|neg): " + pWClass.getCountWC("japan", "neg"));
		
		System.out.println();
		
		System.out.println("count(pos): " + pWClass.getPosWordCount());
		System.out.println("count(neg): " + pWClass.getNegWordCount());
		
		System.out.println();
		
		System.out.println("|V|: " + pWClass.getVocabularyCount());
	}
}
