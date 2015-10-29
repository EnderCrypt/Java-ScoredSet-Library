package com.github.endercrypt.scoredSet;

public class Entry<T>
{
	private T object;
	private int score;
	private Entry<T> nextEntry; //next obj with lower score
	public Entry(T obj)
	{
		this.object = obj;
		score = ((Scoreable)this.object).objScore();
	}
	public T get()
	{
		return object;
	}
	public int getScore()
	{
		return score;
	}
	public void setLink(Entry<T> nextEntry)
	{
		this.nextEntry = nextEntry;
	}
	public Entry<T> getNext()
	{
		return nextEntry;
	}
	public boolean hasNext()
	{
		return (nextEntry != null);
	}
}
