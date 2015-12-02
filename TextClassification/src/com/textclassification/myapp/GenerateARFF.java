package com.textclassification.myapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.tokenizers.NGramTokenizer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class GenerateARFF 
{
	private FastVector atts;
	private FastVector mr;
	private Instances data;

	public GenerateARFF() 
	{
		// 1. set up attributes
		atts = new FastVector();
		
		mr = new FastVector(2);
		mr.addElement("pos");
		mr.addElement("neg");
		Attribute mrAttr = new Attribute("mr", mr);

		// - string
		atts.addElement(new Attribute("classifytext", (FastVector) null));
		// - class 
		atts.addElement(new Attribute("classifyreview", mr));

		// 2. create Instances object
		data = new Instances("MovieReviews", atts, 0);
		data.setClassIndex(data.numAttributes() - 1);
	}
	
	public void addDirectory(String dirName, boolean isData) throws IOException
	{
	    File[] files = new File(dirName).listFiles();
	    addFiles(dirName, files, isData);
	}

	public void addFiles(String dirName, File[] files, boolean isData) throws IOException 
	{
	    for (File file : files) 
	    {
	        if (file.isDirectory()) 
	        {
	            addFiles(file.getName(), file.listFiles(), isData);
	        } 
	        else 
	        {
	        	if (file.getName().endsWith(".txt"))
	        	{
	        		addFileContent(dirName, file.getPath(), isData);
	        	}
	        }
	    }
	}
	
	public void addFileContent(String dirName, String filePath, boolean isData) throws IOException
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
		
		addInstance(sb.toString(), dirName, isData);
	}

	public void addInstance(String text, String review, boolean isData) 
	{
		// 3. fill with data
		Instance inst = new DenseInstance(2);
		inst.setValue((Attribute) atts.elementAt(0), text.replaceAll("(\r\n|\n)", ""));
		
		if (isData)
		{
			inst.setValue((Attribute) atts.elementAt(1), review);
		}

		// add
		data.add(inst);
	}
	
	public void createFile(String fileName, boolean isData) throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		
		for (int i = 0; i < data.numInstances(); i++)
		{
			Instance inst = data.get(i);
			writer.write(inst.stringValue(0));
			
			if (isData)
			{
				writer.write(",");
				writer.write(inst.stringValue(1));
			}
			
			writer.newLine();
		}
		
		writer.flush();
		writer.close();
	}
	
	public Instances getFilteredInstances() throws Exception
	{
		// Set the tokenizer 
		NGramTokenizer tokenizer = new NGramTokenizer(); 
		tokenizer.setNGramMinSize(1); 
		tokenizer.setNGramMaxSize(1); 
		tokenizer.setDelimiters("\\s+\\n");
		
		// Set the filter 
		StringToWordVector filter = new StringToWordVector(); 
		filter.setInputFormat(data); 
		filter.setTokenizer(tokenizer); 
		filter.setDoNotOperateOnPerClassBasis(true);
		filter.setMinTermFreq(5);
		
		return Filter.useFilter(data, filter);
	}
	
	public Instances getInstances() throws Exception
	{
		return data;
	}
}