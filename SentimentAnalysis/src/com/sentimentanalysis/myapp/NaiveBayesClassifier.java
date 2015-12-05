package com.sentimentanalysis.myapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

public class NaiveBayesClassifier 
{
	private PClass pClass = new PClass();
	private PWClass pWClass = new PWClass();
	private String TOKENIZER_PATTERN = "[\r\n|\\n|\\s]+";
	
	public void train(String dirName) throws IOException 
	{
		addDirectory(dirName);
		pWClass.filterWordsFewerThan5();
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
		String line = null;
		
		while ((line = br.readLine()) != null)
		{
			pWClass.addWords(line.split(TOKENIZER_PATTERN), dirName);
		}
		
		pClass.setNc(dirName);
	}
	
	public void serializeObject(String objectFileName) throws IOException 
	{
		FileOutputStream fos = new FileOutputStream(objectFileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(pClass);
		oos.writeObject(pWClass);
		oos.close();
	}
}
