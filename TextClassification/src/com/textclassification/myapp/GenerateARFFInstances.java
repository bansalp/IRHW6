package com.textclassification.myapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class GenerateARFFInstances 
{
	public Instances createDataset(String directoryPath) throws Exception 
	{
		// 1. set up attributes
		FastVector atts = new FastVector();
		
		FastVector mr = new FastVector(2);
		mr.addElement("pos");
		mr.addElement("neg");
		Attribute mrAttr = new Attribute("mr", mr);

		// - string
		atts.addElement(new Attribute("classifytext", (FastVector) null));
		// - class 
		atts.addElement(new Attribute("classifyreview", mr));

		// 2. create Instances object
		Instances data = new Instances("MovieReviews", atts, 0);
		data.setClassIndex(data.numAttributes() - 1);

		File dir = new File(directoryPath);
		String[] files = dir.list();

		for (int i = 0; i < files.length; i++) 
		{
			if (files[i].endsWith(".txt")) 
			{
				try 
				{
					File txt = new File(directoryPath + File.separator + files[i]);
					BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(txt)));
					StringBuilder sb = new StringBuilder();
					String line = null;
					
					while ((line = br.readLine()) != null)
					{
						sb.append(line);
						sb.append("\n");
					}
						
					Instance inst = new DenseInstance(2);
					inst.setValue((Attribute) atts.elementAt(0), sb.toString());

					// add
					data.add(inst);
				} 
				catch (Exception e) 
				{
					System.out.println(e.getMessage());
					System.err.println("failed to convert file: " + directoryPath + File.separator + files[i]);
				}
			}
		}

		return data;
	}
}