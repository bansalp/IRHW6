package com.sentimentanalysis.myapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class RatioFinderApp {

	public static void main(String[] args) 
	{
		RatioFinder rf = new RatioFinder();
		Map<String, Double> positiveNegative = new LinkedHashMap<String, Double>();
		Map<String, Double> negativePositive = new LinkedHashMap<String, Double>();
		final String MODEL_FILE = "model_file.txt";
		double termPositive, termNegative;
		 
		
		try 
		{
			rf.loadModel(MODEL_FILE);
			for(String term : rf.getpWClass().getpWC().keySet()){
				termPositive = rf.calculatePWC(term, "pos");
				termNegative = rf.calculatePWC(term, "neg");
				positiveNegative.put(term, Math.log(termPositive/termNegative));
				negativePositive.put(term, Math.log(termNegative/termPositive));
			}
		} 
		catch (ClassNotFoundException | IOException e) 
		{
			e.printStackTrace();
		}
		positiveNegative = sorter(positiveNegative);
		negativePositive = sorter(negativePositive);
		
		System.out.println("Positive/Negative");
		int cnt = 0;
		for(String t : positiveNegative.keySet()){
			cnt++;
			System.out.println(t + " " + positiveNegative.get(t));
			if(cnt == 20)
				break;
		}
		System.out.println("Negative/Positive");
		cnt = 0;
		for(String t : negativePositive.keySet()){
			cnt++;
			System.out.println(t + " " + negativePositive.get(t));
			if(cnt == 20)
				break;
		}
	}
	
	public static Map<String, Double> sorter(Map<String, Double> ret) {
		List<Map.Entry<String, Double>> sorted = new ArrayList<Entry<String, Double>>(
				ret.entrySet());
		Collections.sort(sorted, new Comparator<Entry<String, Double>>() {
			@Override
			public int compare(Entry<String, Double> e1,
					Entry<String, Double> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}
		});
		Map<String, Double> result = new LinkedHashMap<String, Double>();
		for (Entry<String, Double> entry : sorted) {
			result.put(entry.getKey(), entry.getValue());
		}
		return (HashMap<String, Double>) result;
	}
	
}
