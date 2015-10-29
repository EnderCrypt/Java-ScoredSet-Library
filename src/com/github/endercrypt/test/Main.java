package com.github.endercrypt.test;

import java.util.TreeSet;

import com.github.endercrypt.scoredSet.ScoredSet;

public class Main
{
	public static int counter;
	public static void main(String[] args)
	{
		// SCOREDSET
		counter = 0;
		ScoredSet<TestData> ss = new ScoredSet<>();
		ss.add(new TestData(0));
		ss.add(new TestData(-50));
		ss.add(new TestData(100));
		ss.add(new TestData(50));
		ss.add(new TestData(50));
		System.out.println(ss);
		
		//> [(3. s100), (5. s50), (4. s50), (1. s0), (2. s-50)]
		
		// TREESET
		counter = 0;
		TreeSet<TestData> ts = new TreeSet<>();
		ts.add(new TestData(0));
		ts.add(new TestData(-50));
		ts.add(new TestData(100));
		ts.add(new TestData(50));
		ts.add(new TestData(50));
		System.out.println(ts);
		
		//> [(3. s100), (4. s50), (1. s0), (2. s-50)]
	}
}
