package com.sentimentanalysis.myapp;

import java.util.HashMap;
import java.util.Map;

public class PWClass 
{
	private Map<String, Map<String, Long>> pWC = new HashMap<String, Map<String, Long>>();
	private long posCount = 0;
	private long negCount = 0;
	private long V = 0;
	
	public void addWords(String[] words, String dirName)
	{
		// add all the words to the vocabulary
		for (String word: words)
		{
			addWord(word, dirName);
		}
	}
	
	public void addWord(String word, String dirName)
	{
		if (pWC.containsKey(word))
		{
			// get the class attributes for the given word 
			Map<String, Long> cCount = pWC.get(word);
			
			// update counter if the class attribute is present
			if (cCount.containsKey(dirName))
			{
				long counter = cCount.get(dirName);
				cCount.put(dirName, ++counter);
			}
			// add class attribute if not present
			else
			{
				cCount.put(dirName, (long) 1);
			}
		}
		else
		{
			// create a new class attribute
			Map<String, Long> cCount = new HashMap<String, Long>();
			cCount.put(dirName, (long) 1);
			
			// add the word along-with the newly created class attribute
			pWC.put(word, cCount);
			
			// increment count as new word is added to the vocabulary
			V++;
		}
		
		// update class counter 
		updateClassCounter(dirName);
	}
	
	private void updateClassCounter(String dirName) 
	{
		if (dirName.equalsIgnoreCase("pos"))
		{
			posCount++;
		}
		else
		{
			negCount++;
		}
	}
	
	public long getPosWordCount()
	{
		return posCount;
	}
	
	public long getNegWordCount()
	{
		return negCount;
	}
	
	public long getVocabularyCount()
	{
		return V;
	}

	public long getCountWC(String word, String dirName)
	{
		// return count of word given a class
		Long countWC = pWC.get(word).get(dirName); 
		
		if (countWC != null)
		{
			return countWC;
		}
		else
		{
			return 0;
		}
	}
}