package com.sentimentanalysis.myapp;

public class Document
{
	private double posScore;
	private double negScore;
	private String originalClass;
	private String predictedClass;
	
	public double getPosScore() 
	{
		return posScore;
	}
	
	public void setPosScore(double posScore) 
	{
		this.posScore = posScore;
	}
	
	public double getNegScore() 
	{
		return negScore;
	}
	
	public void setNegScore(double negScore) 
	{
		this.negScore = negScore;
	}
	
	public String getOriginalClass() 
	{
		return originalClass;
	}
	
	public void setOriginalClass(String originalClass) 
	{
		this.originalClass = originalClass;
	}
	
	public String getPredictedClass() 
	{
		return predictedClass;
	}
	
	public void setPredictedClass(String predictedClass) 
	{
		this.predictedClass = predictedClass;
	}

	@Override
	public String toString() 
	{
		boolean correctlyClassified = false;
		
		if (originalClass.equalsIgnoreCase(predictedClass))
		{
			correctlyClassified = true;
		}
		
		return posScore + " " + negScore + " " + originalClass + " " + predictedClass + " " + correctlyClassified;
	}
}
