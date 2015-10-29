package com.github.endercrypt.test;
import com.github.endercrypt.scoredSet.Scoreable;

public class TestData implements Scoreable
{
	private int id = ++Main.counter;
	private int score;
	public TestData(int score)
	{
		this.score = score;
	}
	@Override
	public int objScore()
	{
		return score;
	}
	@Override
	public String toString()
	{
		return "("+id+". s"+score+")";
	}
}
