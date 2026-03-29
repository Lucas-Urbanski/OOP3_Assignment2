package utilities;

import java.util.NoSuchElementException;

/**
 * Iterator implementation for MyDLL.
 * 
 * This iterator works on a copied array version of the list data,
 * which allows safe sequential traversal without modifying the list itself.
 *
 * @param <E> the type of element returned by the iterator
 */
public class MyDLLIterator<E> implements Iterator<E>
{
	private Object[] elements;
	private int currentIndex;

	/**
	 * Creates an iterator for the given doubly linked list by copying
	 * its contents into an array.
	 *
	 * @param list the list to iterate over
	 */
	public MyDLLIterator(MyDLL<E> list)
	{
		this.elements = list.toArray();
		this.currentIndex = 0;
	}

	/**
	 * Returns true if there are still elements left to visit.
	 *
	 * @return true if another element exists, otherwise false
	 */
	@Override
	public boolean hasNext()
	{
		return currentIndex < elements.length;
	}

	/**
	 * Returns the next element in the iteration.
	 *
	 * @return the next element
	 * @throws NoSuchElementException if no elements remain
	 */
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