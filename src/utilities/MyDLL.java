package utilities;

import java.util.NoSuchElementException;

public class MyDLL<E> implements ListADT<E>
{
	private MyDLLNode<E> head;
	private MyDLLNode<E> tail;
	private int size;

	public MyDLL()
	{
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public void clear()
	{
		head = null;
		tail = null;
		size = 0;
	}

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

		newNode.prev = current.prev;
		newNode.next = current;
		current.prev.next = newNode;
		current.prev = newNode;

		size++;
		return true;
	}

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

	@Override
	public E get(int index) throws IndexOutOfBoundsException
	{
		return getNode(index).data;
	}

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

	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}

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

		if (toHold.length > size)
		{
			toHold[size] = null;
		}

		return toHold;
	}

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

	@Override
	public Iterator<E> iterator()
	{
		return new MyDLLIterator<E>(this);
	}

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

	private MyDLLNode<E> getNode(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}

		MyDLLNode<E> current;

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
}