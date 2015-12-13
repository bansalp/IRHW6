package com.sentimentanalysis.myapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class RatioFinder {
	
	private PWClass pWClass;
	private PClass pClass;
	
	public double calculatePWC(String word, String className)
	{	
		long countWC = pWClass.getCountWC(word, className);
		long countC = pWClass.getWordCount(className);
		long V = pWClass.getVocabularyCount();
		
		long numerator = countWC + 1;
		long denominator = countC + V;
		
		return (numerator / (double) denominator);
	}
		
	public void loadModel(String modelFileName) throws IOException, ClassNotFoundException 
	{
		FileInputStream fis = new FileInputStream(modelFileName);
		ObjectInputStream ois = new ObjectInputStream(fis);
		pClass = (PClass) ois.readObject();
		pWClass = (PWClass) ois.readObject();
		ois.close();
	}

	public PClass getpClass() {
		return pClass;
	}

	public void setpClass(PClass pClass) {
		this.pClass = pClass;
	}

	public PWClass getpWClass() {
		return pWClass;
	}

	public void setpWClass(PWClass pWClass) {
		this.pWClass = pWClass;
	}
}
