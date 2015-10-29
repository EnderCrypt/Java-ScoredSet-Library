package com.github.endercrypt.scoredSet;

import java.io.Serializable;

public class Entry<T> implements Serializable
{
	private static final long serialVersionUID = 4400547082175963299L;
	/**
	 * 
	 */
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
