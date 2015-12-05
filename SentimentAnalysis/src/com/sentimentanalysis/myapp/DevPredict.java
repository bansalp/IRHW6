package com.sentimentanalysis.myapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DevPredict 
{
	private Map<String, Document> result = new LinkedHashMap<String, Document>();
	private PClass pClass;
	private PWClass pWClass;
	private String TOKENIZER_PATTERN = "[\r\n|\\n|\\s]+";
	private long docTotal = 0;
	private long posPredictTotal = 0;
	private long negPredictTotal = 0;

	public void loadModel(String modelFileName) throws IOException, ClassNotFoundException 
	{
		FileInputStream fis = new FileInputStream(modelFileName);
		ObjectInputStream ois = new ObjectInputStream(fis);
		pClass = (PClass) ois.readObject();
		pWClass = (PWClass) ois.readObject();
		ois.close();
	}
	
	public void test(String dirName) throws IOException 
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
			sb.append(line);
			sb.append("\n");
		}
		
		classifyFile(filePath, dirName, sb.toString());
		docTotal++;
	}
	
	public void classifyFile(String filePath, String dirName, String fileContent)
	{
		Document doc = new Document();
		doc.setOriginalClass(dirName);
		
		classifyWords(fileContent.split(TOKENIZER_PATTERN), doc);
		
		result.put(filePath.substring(filePath.lastIndexOf("/") + 1), doc);
	}

	public void classifyWords(String[] words, Document doc) 
	{
		double pWCPos = log100(pClass.getPcPos());
		double pWCNeg = log100(pClass.getPcNeg());
		
		for (String word: words)
		{
			pWCPos *= log100(calculatePWC(word, "pos"));
			pWCNeg *= log100(calculatePWC(word, "neg"));
		}
		
		doc.setPosScore(pWCPos);
		doc.setNegScore(pWCNeg);
		
		if (pWCPos > pWCNeg)
		{
			doc.setPredictedClass("pos");
			posPredictTotal++;
		}
		else
		{
			doc.setPredictedClass("neg");
			negPredictTotal++;
		}
	}
	
	public double calculatePWC(String word, String className)
	{	
		long countWC = pWClass.getCountWC(word, className);
		long countC = pWClass.getWordCount(className);
		long V = pWClass.getVocabularyCount();
		
		long numerator = countWC + 1;
		long denominator = countC + V;
		
		return (numerator / (double) denominator);
	}
	
	public double logb(double a, double b)
	{
		return Math.log(a) / Math.log(b);
	}

	public double log100(double a)
	{
		return logb(a, 100);
	}
	
	public void display() throws FileNotFoundException, UnsupportedEncodingException
	{
		PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
		
		for (Map.Entry<String, Document> entry: result.entrySet())
		{
			writer.println(entry.getKey() + " " + entry.getValue());
		}
		
		writer.println();
		
		writer.println("Percentage of Positive: " + ((posPredictTotal / (double) docTotal) * 100));
		writer.println("Percentage of Negative: " + ((negPredictTotal / (double) docTotal) * 100));
		
		writer.close();
	}
}
