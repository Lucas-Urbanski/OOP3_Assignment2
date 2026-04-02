package utilities;

import java.util.NoSuchElementException;

/**
 * Doubly linked list implementation of the ListADT interface.
 * Each node stores references to both the next and previous node,
 * which allows efficient insertion and removal at different positions.
 *
 * @param <E> the type of element stored in the list
 */
public class MyDLL<E> implements ListADT<E>
{
	private MyDLLNode<E> head;
	private MyDLLNode<E> tail;
	private int size;

	/**
	 * Creates an empty doubly linked list.
	 */
	public MyDLL()
	{
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Returns the number of elements currently stored in the list.
	 *
	 * @return the current list size
	 */
	@Override
	public int size()
	{
		return size;
	}

	/**
	 * Removes all elements from the list by resetting both ends
	 * and setting the size back to zero.
	 */
	@Override
	public void clear()
	{
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Inserts an element at the specified index.
	 * Special cases are handled when inserting at the front or back.
	 * For middle insertions, the new node is linked between the
	 * node currently at that index and the node before it.
	 *
	 * @param index the position where the element should be inserted
	 * @param toAdd the element to insert
	 * @return true if the insertion is successful
	 * @throws NullPointerException if the given element is null
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException
	{
		if (toAdd == null)
		{
			throw new NullPointerException("Null elements are not allowed.");
		}

		if (index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}

		if (index == 0)
		{
			addFirst(toAdd);
			return true;
		}

		if (index == size)
		{
			addLast(toAdd);
			return true;
		}

		MyDLLNode<E> current = getNode(index);
		MyDLLNode<E> newNode = new MyDLLNode<E>(toAdd);

		// Insert the new node between current.prev and current
		newNode.prev = current.prev;
		newNode.next = current;
		current.prev.next = newNode;
		current.prev = newNode;

		size++;
		return true;
	}

	/**
	 * Appends an element to the end of the list.
	 *
	 * @param toAdd the element to add
	 * @return true if the element is added successfully
	 * @throws NullPointerException if the given element is null
	 */
	@Override
	public boolean add(E toAdd) throws NullPointerException
	{
		if (toAdd == null)
		{
			throw new NullPointerException("Null elements are not allowed.");
		}

		addLast(toAdd);
		return true;
	}

	/**
	 * Appends all elements from another list to the end of this list.
	 * The source list is traversed using its iterator.
	 *
	 * @param toAdd the list whose elements are to be added
	 * @return true if the operation completes successfully
	 * @throws NullPointerException if the provided list is null
	 */
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException
	{
		if (toAdd == null)
		{
			throw new NullPointerException("List to add cannot be null.");
		}

		Iterator<? extends E> iter = toAdd.iterator();

		while (iter.hasNext())
		{
			add(iter.next());
		}

		return true;
	}

	/**
	 * Returns the element stored at the specified index.
	 *
	 * @param index the index of the element to retrieve
	 * @return the element at that position
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException
	{
		return getNode(index).data;
	}

	/**
	 * Removes and returns the element at the specified index.
	 * This method handles removal differently depending on whether
	 * the node is the only node, the head, the tail, or a middle node.
	 *
	 * @param index the index of the element to remove
	 * @return the removed element
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException
	{
		MyDLLNode<E> current = getNode(index);
		E removedData = current.data;

		if (size == 1)
		{
			head = null;
			tail = null;
		}
		else if (current == head)
		{
			head = head.next;
			head.prev = null;
		}
		else if (current == tail)
		{
			tail = tail.prev;
			tail.next = null;
		}
		else
		{
			current.prev.next = current.next;
			current.next.prev = current.prev;
		}

		size--;
		return removedData;
	}

	/**
	 * Removes the first occurrence of the specified element by scanning
	 * from the front of the list until a match is found.
	 *
	 * @param toRemove the element to remove
	 * @return the removed element, or null if the element is not found
	 * @throws NullPointerException if the given element is null
	 */
	@Override
	public E remove(E toRemove) throws NullPointerException
	{
		if (toRemove == null)
		{
			throw new NullPointerException("Null elements are not allowed.");
		}

		MyDLLNode<E> current = head;
		int index = 0;

		while (current != null)
		{
			if (current.data.equals(toRemove))
			{
				return remove(index);
			}
			current = current.next;
			index++;
		}

		return null;
	}

	/**
	 * Replaces the element at the given index with a new value.
	 *
	 * @param index the index of the element to replace
	 * @param toChange the new element to store
	 * @return the old element previously stored at that position
	 * @throws NullPointerException if the replacement element is null
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException
	{
		if (toChange == null)
		{
			throw new NullPointerException("Null elements are not allowed.");
		}

		MyDLLNode<E> current = getNode(index);
		E oldData = current.data;
		current.data = toChange;
		return oldData;
	}

	/**
	 * Returns true if the list contains no elements.
	 *
	 * @return true if empty, otherwise false
	 */
	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}

	/**
	 * Searches the list from front to back for the specified element.
	 *
	 * @param toFind the element to search for
	 * @return true if the element is present, otherwise false
	 * @throws NullPointerException if the element is null
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException
	{
		if (toFind == null)
		{
			throw new NullPointerException("Null elements are not allowed.");
		}

		MyDLLNode<E> current = head;

		while (current != null)
		{
			if (current.data.equals(toFind))
			{
				return true;
			}
			current = current.next;
		}

		return false;
	}

	/**
	 * Copies the list contents into the provided array.
	 * If the provided array is too small, a new one of the same runtime
	 * type is created.
	 *
	 * @param toHold the destination array
	 * @return an array containing all elements in proper sequence
	 * @throws NullPointerException if the array is null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E[] toArray(E[] toHold) throws NullPointerException
	{
		if (toHold == null)
		{
			throw new NullPointerException("Array cannot be null.");
		}

		if (toHold.length < size)
		{
			toHold = (E[]) java.lang.reflect.Array.newInstance(
				toHold.getClass().getComponentType(), size);
		}

		MyDLLNode<E> current = head;
		int index = 0;

		while (current != null)
		{
			toHold[index++] = current.data;
			current = current.next;
		}

		// Match standard toArray behavior by placing null after the last element
		if (toHold.length > size)
		{
			toHold[size] = null;
		}

		return toHold;
	}

	/**
	 * Returns the list contents as an Object array in proper sequence.
	 *
	 * @return an Object array containing all elements in order
	 */
	@Override
	public Object[] toArray()
	{
		Object[] result = new Object[size];
		MyDLLNode<E> current = head;
		int index = 0;

		while (current != null)
		{
			result[index++] = current.data;
			current = current.next;
		}

		return result;
	}

	/**
	 * Returns an iterator over the list.
	 * The iterator works on a copied array version of the list rather
	 * than walking the nodes directly.
	 *
	 * @return an iterator over the elements in the list
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Iterator<E> iterator()
	{
		// The iterator works on a copied array version of the list
		return new MyDLLIterator<E>((E[]) this.toArray());
	}

	/**
	 * Inserts an element at the front of the list.
	 *
	 * @param item the element to add
	 */
	private void addFirst(E item)
	{
		MyDLLNode<E> newNode = new MyDLLNode<E>(item);

		if (isEmpty())
		{
			head = newNode;
			tail = newNode;
		}
		else
		{
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}

		size++;
	}

	/**
	 * Inserts an element at the end of the list.
	 *
	 * @param item the element to add
	 */
	private void addLast(E item)
	{
		MyDLLNode<E> newNode = new MyDLLNode<E>(item);

		if (isEmpty())
		{
			head = newNode;
			tail = newNode;
		}
		else
		{
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}

		size++;
	}

	/**
	 * Returns the node at the specified index.
	 * To reduce traversal time, the search starts from whichever end
	 * of the list is closer to the target index.
	 *
	 * @param index the index of the node to locate
	 * @return the node stored at that index
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
	private MyDLLNode<E> getNode(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}

		MyDLLNode<E> current;

		// Start from whichever end is closer to reduce traversal time
		if (index < size / 2)
		{
			current = head;
			for (int i = 0; i < index; i++)
			{
				current = current.next;
			}
		}
		else
		{
			current = tail;
			for (int i = size - 1; i > index; i--)
			{
				current = current.prev;
			}
		}

		return current;
	}

	/**
	 * Private iterator class used only by MyDLL.
	 * It stores a copy of the list elements in an array and iterates through it.
	 */
	private class MyDLLIterator<T> implements Iterator<T>
	{
		private T[] elements;
		private int currentIndex;

		/**
		 * Creates an iterator using a copied array of list elements.
		 *
		 * @param list the copied array to iterate over
		 */
		public MyDLLIterator(T[] list)
		{
			this.elements = list;
			this.currentIndex = 0;
		}

		/**
		 * Returns true if another element is still available.
		 *
		 * @return true if there is another element, otherwise false
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
		 * @throws NoSuchElementException if there are no elements left
		 */
		@Override
		public T next() throws NoSuchElementException
		{
			if (!hasNext())
			{
				throw new NoSuchElementException("No more elements in iterator.");
			}

			return elements[currentIndex++];
		}
	}
}