package utilities;

import java.util.Iterator;
import java.util.EmptyStackException;

/**
 * StackADT.java
 * 
 * @author Lucas Urbanski & Jordi Usen
 * @version 1.1
 * 
 * The <code>StackADT</code> interface defines the standard operations for a
 * Last-In-First-Out (LIFO) stack data structure,
 * including base one indexing where the top is considered index 1.
 * 
 * @param <E> The type of elements held in this stack.
 */
public interface P1StackADT<E> {

	/**
	 * Tests whether the stack is empty.
	 * 
	 * Precondition: A valid stack object exists.
	 * 
	 * Postcondition: The stack remains unchanged.
	 * 
	 * @return true if the stack contains no elements, false otherwise.
	 */
	public boolean isEmpty();

	/**
	 * Looks at the object at the top of the stack without removing it.
	 * 
	 * Precondition: The stack must not be empty.
	 * 
	 * Postcondition: The stack remains unchanged.
	 * 
	 * @return the object at the top of the stack.
	 * 
	 * @throws EmptyStackException if the stack is empty.
	 */
	public E peek() throws EmptyStackException;

	/**
	 * Removes and returns the object at the top of the stack.
	 * 
	 * Precondition: The stack must not be empty.
	 * 
	 * Postcondition: The top element is removed and size decreases by 1.
	 * 
	 * @return the object removed from the top of the stack.
	 * 
	 * @throws EmptyStackException if the stack is empty.
	 */
	public E pop() throws EmptyStackException;

	/**
	 * Pushes an item onto the top of the stack.
	 * 
	 * Precondition: A valid stack object exists.
	 * 
	 * Postcondition: The item is placed at the top and size increases by 1.
	 * 
	 * @param item the item to be pushed onto this stack.
	 * 
	 * @return the <code>item</code> argument.
	 */
	public E push(E item);

	/**
	 * Returns the depth of the object from the top of the stack.
	 * 
	 * Precondition: A valid stack object exists and a non-null object is passed.
	 * 
	 * Postcondition: The stack remains unchanged.
	 * 
	 * @param o the desired object.
	 * 
	 * @return the 1-based depth from the top, or -1 if the object is not found.
	 */
	public int search(Object o);

	/**
	 * Compares this stack with another for equality.
	 * 
	 * Precondition: A valid stack object exists.
	 * 
	 * Postcondition: Both stacks remain unchanged.
	 * 
	 * @param otherStack the stack to compare against.
	 * 
	 * @return true if both stacks contain the same elements in the same order.
	 */
	public boolean equals(P1StackADT<E> otherStack);

	/**
	 * Returns an iterator over the elements in this stack in proper sequence.
	 * 
	 * Precondition: A valid stack object exists.
	 * 
	 * Postcondition: The stack remains unchanged.
	 * 
	 * @return an iterator over the elements.
	 */
	public Iterator<E> iterator();

	/**
	 * Returns an array containing all elements in this stack in proper sequence.
	 * 
	 * Precondition: A valid stack object exists.
	 * 
	 * Postcondition: The stack remains unchanged.
	 * 
	 * @return an array containing all of the elements in this stack.
	 */
	public Object[] toArray();

	/**
	 * Returns an array containing all elements in this stack; the runtime type
	 * of the returned array is that of the specified array.
	 * 
	 * Precondition: A valid stack object exists and the passed array is not null.
	 * 
	 * Postcondition: The stack remains unchanged.
	 * 
	 * @param copy the array into which the elements of the stack are to be stored.
	 * 
	 * @return an array containing the elements of the stack.
	 */
	public E[] toArray(E[] copy);

	/**
	 * Checks if the stack contains a specific element.
	 * 
	 * Precondition: A valid stack object exists.
	 * 
	 * Postcondition: The stack remains unchanged.
	 * 
	 * @param obj the element to search for.
	 * 
	 * @return true if the stack contains the element, false otherwise.
	 */
	public boolean contains(E obj);

	/**
	 * Returns the number of elements in the stack.
	 * 
	 * Precondition: A valid stack object exists.
	 * 
	 * @return the current size of the stack.
	 */
	public int size();

	/**
	 * Removes all elements from the stack.
	 * 
	 * Precondition: A valid stack object exists.
	 * 
	 * Postcondition: The stack is empty and size is 0.
	 */
	public void clear();
}