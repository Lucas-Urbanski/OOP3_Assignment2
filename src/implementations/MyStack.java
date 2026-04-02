package implementations;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import static java.util.Arrays.copyOf;
import utilities.Iterator;
import utilities.StackADT;

/**
 * MyStack.java
 * 
 * @author Lucas Urbanski
 * @version 1.0
 * 
 * MyStack is a class that implements the StackADT interface using a MyArrayList as the underlying data structure.
 * It provides methods to perform standard stack operations such as push, pop, peek, clear, etc.
 */

public class MyStack<E> implements StackADT<E> {

	// Attributes
	private MyArrayList<E> list;


	// Constructor
	public MyStack() {
		list = new MyArrayList<>();
	}

	/**
	 * Pushes an element onto the top of the stack.
	 * 
	 * @param toAdd the element to be added to the stack
	 * @throws NullPointerException if the element to be added is null
	 */
	@Override
	public void push(E toAdd) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException("Cannot add null element to the stack.");
		}
		list.add(toAdd);

	}

	/**
	 * Removes and returns the element at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws EmptyStackException if the stack is empty
	 */
	@Override
	public E pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return list.remove(list.size() - 1);
	}

	/**
	 * Returns the element at the top of the stack without removing it.
	 * 
	 * @return the element at the top of the stack
	 * @throws EmptyStackException if the stack is empty
	 */
	@Override
	public E peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return list.get(list.size() - 1);
	}

	/**
	 * Empties the stack of all elements.
	 */
	@Override
	public void clear() {
		list.clear();
	}

	/**
	 * Checks if the stack is empty.
	 * 
	 * @return if true the stack is empty, otherwise false
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}


	/**
	 * Converts the stack to an array.
	 * 
	 * @return an array containing all the elements in the stack
	 */
	@Override
	public Object[] toArray() {
		Object[] array = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(list.size() - 1 - i);
		}
		return array;
	}

	/**
	 * Converts the stack to an array of the specified type.
	 * 
	 * @param holder an array of the type to hold the elements of the stack
	 * @return an array containing all the elements in the stack
	 * @throws NullPointerException if the holder array is null
	 */
	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		if (holder == null) {
			throw new NullPointerException("Cannot search for null element in the stack.");
		}
		E[] target = holder.length >= list.size()
				? holder
				: copyOf(holder, list.size());
		for (int i = 0; i < list.size(); i++) {
			target[i] = list.get(list.size() - 1 - i);
		}
		return target;
	}

	/**
	 * Checks if the stack contains a specific element.
	 * 
	 * @param toFind the element to find in the stack
	 * @return true if the stack contains the element, otherwise false
	 * @throws NullPointerException if the element to find is null
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null) {
			throw new NullPointerException("Cannot search for null element in the stack.");
		}
		return list.contains(toFind);
	}

	/**
	 * Searches for the position of a specific element in the stack.
	 * 
	 * @param toFind the element to find in the stack
	 * @return the 1-based position of the element in the stack, or -1 if not found
	 * @throws NullPointerException if the element to find is null
	 */
	@Override
	public int search(E toFind) throws NullPointerException {
		if (toFind == null) {
			throw new NullPointerException("Cannot search for null element in the stack.");
		}
		for (int i = 1; i <= list.size(); i++) {
			E element = list.get(list.size() - i);
			if (element.equals(toFind)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returns an iterator over the elements in the stack in LIFO order.
	 * 
	 * @return an iterator over the elements in the stack
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int current = size() - 1;

			/**
			 * Checks if there are more elements to iterate over.
			 * 
			 * @return true if there are more elements to iterate over, otherwise false
			 */
			@Override
			public boolean hasNext() {
				return current >= 0;
			}

			/**
			 * Returns the next element in the iteration.
			 * 
			 * @return the next element in the iteration
			 * @throws NoSuchElementException if there are no more elements to iterate over
			 */
			@Override
			public E next() throws NoSuchElementException {
				if (!hasNext())
					throw new NoSuchElementException();
				return list.get(current--);
			}
		};
	}

	/**
	 * Checks if this stack is equal to another stack.
	 * 
	 * @param that the stack to compare with
	 * @return true if the stacks are equal, otherwise false
	 */
	@Override
	public boolean equals(StackADT<E> that) {
		if (that == null) {
			return false;
		}
		if (this.size() != that.size()) {
			return false;
		}
		Object[] thisArray = this.toArray();
		Object[] thatArray = that.toArray();
		for (int i = 0; i < this.size(); i++) {
			if (!thisArray[i].equals(thatArray[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns the number of elements in the stack.
	 * 
	 * @return the number of elements in the stack
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Checks if the stack has reached its maximum capacity.
	 * 
	 * @return false since this implementation does not have a maximum capacity
	 */
	@Override
	public boolean stackOverflow() {
		return false;
	}
}