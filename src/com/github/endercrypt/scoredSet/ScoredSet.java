package com.github.endercrypt.scoredSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ScoredSet<T extends Scoreable> implements Set// , Iterable<T>
{
	private Entry<T> first = null;
	private int size = 0;

	public boolean add(Object object)
	{
		T item;
		if (object instanceof Scoreable)
		{
			item = (T) object;
		}
		else
		{
			throw new UnsupportedOperationException("the object your trying to add (" + object + ") does not implement Scorable");
		}
		Entry<T> entry = new Entry<>(item);
		if (size == 0)
		{
			first = entry;
			size = 1;
		}
		else
		{
			Entry<T> previous = null;
			Entry<T> iterEntry = first;
			while (entry.getScore() < iterEntry.getScore())
			{
				previous = iterEntry;
				if (iterEntry.hasNext() == false)
				{
					break;
				}
				iterEntry = iterEntry.getNext();
			}
			// re-link
			if (previous == null)
			{
				entry.setLink(first);
				first = entry;
			}
			else
			{
				entry.setLink(previous.getNext());
				previous.setLink(entry);
			}
			size += 1;
		}
		return true;
	}

	public T get(int index)
	{
		if (index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		Entry<T> object = first;
		while (index > 0)
		{
			index--;
			object = object.getNext();
		}
		return object.get();
	}

	public List<T> toList()
	{
		List<T> list = new ArrayList<>();
		Entry<T> object = first;
		for (int i = 0; i < size; i++)
		{
			list.add(object.get());
			object = object.getNext();
		}
		return list;
	}

	public T remove(int index)
	{
		if (index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		Entry<T> removed = null;
		if (index == 0)
		{
			removed = first;
			first = first.getNext();
			size--;
			return removed.get();
		}
		Entry<T> previousObject = null;
		Entry<T> object = first;
		while (index > 0)
		{
			index--;
			previousObject = object;
			object = object.getNext();
		}
		removed = object;
		previousObject.setLink(object.getNext());
		size--;
		return removed.get();
	}

	public boolean remove(Object inObject)
	{
		T other;
		if (inObject instanceof Scoreable)
		{
			other = (T) inObject;
		}
		else
		{
			throw new UnsupportedOperationException("the object your trying to remove (" + inObject + ") does not implement Scorable");
		}
		if (first.get() == other)
		{
			first = first.getNext();
			size--;
			return true;
		}
		Entry<T> previousObject = null;
		Entry<T> object = first;
		for (int i = 0; i < size; i++)
		{
			if (object.get() == other)
			{
				previousObject.setLink(object.getNext());
				size--;
				return true;
			}
			previousObject = object;
			object = object.getNext();
		}
		return false;
	}

	public boolean contains(Object other)// boolean contains(T other)
	{
		if ((other instanceof Scoreable) == false)
		{
			throw new UnsupportedOperationException("the object your trying to check for (" + other + ") does not implement Scorable");
		}
		Entry<T> object = first;
		do
		{
			if (object.get() == other)
			{
				return true;
			}
			object = object.getNext();
		}
		while (object.hasNext());
		return false;
	}

	public void clear()
	{
		first = null;
		size = 0;
	}

	public int size()
	{
		return size;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		if (size > 0)
		{
			Entry<T> entry = first;
			boolean hasNext = false;
			do
			{
				sb.append(entry.get());
				hasNext = entry.hasNext();
				if (hasNext)
				{
					sb.append(", ");
					entry = entry.getNext();
				}
			}
			while (hasNext);
		}
		sb.append(']');
		return sb.toString();
	}

	@Override
	public Iterator<T> iterator()
	{
		Iterator<T> itr = new Iterator<T>()
		{
			private int index = 0;
			private Entry<T> current = first;
			private Entry<T> previous = null;

			@Override
			public boolean hasNext()
			{
				if (size == 0)
				{
					return false;
				}
				return (index < size);
			}

			@Override
			public T next()
			{
				T present = current.get();
				previous = current;
				current = current.getNext();
				index++;
				return present;
			}

			@Override
			public void remove()
			{
				if (size == 0)
				{
					return;
				}
				if (index == 0)
				{
					first.setLink(first.getNext());
				}
				else
				{
					previous.setLink(current.getNext());
				}
			}
		};
		return itr;
	}

	@Override
	public boolean isEmpty()
	{
		return (size == 0);
	}

	/**
	 * UNSUPPORTED
	 */
	@Override
	public Object[] toArray()
	{
		unsupported();
		return null;
	}

	/**
	 * UNSUPPORTED
	 */
	@Override
	public Object[] toArray(Object[] a)
	{
		unsupported();
		return null;
	}

	/**
	 * UNSUPPORTED
	 */
	@Override
	public boolean containsAll(Collection c)
	{
		unsupported();
		return false;
	}

	/**
	 * UNSUPPORTED
	 */
	@Override
	public boolean addAll(Collection c)
	{
		unsupported();
		return false;
	}

	/**
	 * UNSUPPORTED
	 */
	@Override
	public boolean retainAll(Collection c)
	{
		unsupported();
		return false;
	}

	/**
	 * UNSUPPORTED
	 */
	@Override
	public boolean removeAll(Collection c)
	{
		unsupported();
		return false;
	}

	private void unsupported()
	{
		throw new UnsupportedOperationException("the method you are trying to use has not yet been implmented");
	}
}
