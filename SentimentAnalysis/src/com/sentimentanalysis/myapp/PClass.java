package com.sentimentanalysis.myapp;

import java.io.Serializable;

public class PClass implements Serializable
{
	private long Nc_positive = 0;
	private long Nc_negative = 0;
	
	public long getNc_positive() 
	{
		return Nc_positive;
	}
	
	public void setNc_positive(long nc_positive) 
	{
		Nc_positive = nc_positive;
	}
	
	public long getNc_negative() 
	{
		return Nc_negative;
	}
	
	public void setNc_negative(long nc_negative) 
	{
		Nc_negative = nc_negative;
	}
	
	public long getN()
	{
		return Nc_positive + Nc_negative;
	}
	
	public double getPcPos()
	{
		return Nc_positive / (double) getN();
	}
	
	public double getPcNeg()
	{
		return Nc_negative / (double) getN();
	}

	public void setNc(String dirName) 
	{
		if (dirName.equalsIgnoreCase("pos"))
		{
			Nc_positive++;
		}
		else
		{
			Nc_negative++;
		}
	}
}
