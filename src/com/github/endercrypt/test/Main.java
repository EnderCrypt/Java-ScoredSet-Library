package com.github.endercrypt.test;

import com.github.endercrypt.scoredSet.ScoredSet;

public class Main
{
	public static int counter = 0;
	public static void main(String[] args)
	{
		ScoredSet<TestData> ss = new ScoredSet<>();
		for (int i=0;i<5;i++)
		{
			int num = (int) (Math.random()*100);
			ss.add(new TestData(num));
		}
		System.out.println(ss+" Size: "+ss.size());
		System.out.println(ss.remove(1));
		System.out.println(ss+" Size: "+ss.size());
	}
}
