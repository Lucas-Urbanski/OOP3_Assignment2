package utilities;

import java.util.NoSuchElementException;

public class MyDLLIterator<E> implements Iterator<E>
{
	private Object[] elements;
	private int currentIndex;

	public MyDLLIterator(MyDLL<E> list)
	{
		this.elements = list.toArray();
		this.currentIndex = 0;
	}

	@Override
	public boolean hasNext()
	{
		return currentIndex < elements.length;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E next() throws NoSuchElementException
	{
		if (!hasNext())
		{
			throw new NoSuchElementException("No more elements in iterator.");
		}

		return (E) elements[currentIndex++];
	}
}