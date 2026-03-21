package utilities;

import java.util.EmptyStackException;

/**
 * StackADT.java
 * 
 * @author Lucas & Jordi
 * @version 1.0
 * 
 * <p>
 * The <code>StackADT</code> interface defines the standard operations for a 
 * Last-In-First-Out (LIFO) stack data structure 
 * including base one indexing where the top is considered index 1.
 * </p>
 * 
 * @param <E> The type of elements held in this stack.
 */
public interface StackADT<E> {

	/**
	 * Tests whether the stack is empty.
	 *
	 * Precondition:
	 * - A valid stack object exists.
	 *
	 * Postcondition:
	 * - The stack remains unchanged.
	 *
	 * @return true if the stack contains no elements, false otherwise
	 */
	public boolean isEmpty();

	/**
	 * Looks at the object at the top of the stack without removing it.
	 *
	 * Precondition:
	 * - The stack must not be empty.
	 *
	 * Postcondition:
	 * - The stack remains unchanged.
	 *
	 * @return the object at the top of the stack
	 * @throws EmptyStackException if the stack is empty
	 */
	public E peek() throws EmptyStackException;

	/**
	 * Removes and returns the object at the top of the stack.
	 *
	 * Precondition:
	 * - The stack must not be empty.
	 *
	 * Postcondition:
	 * - The top element is removed from the stack.
	 * - The size of the stack decreases by 1.
	 *
	 * @return the object removed from the top of the stack
	 * @throws EmptyStackException if the stack is empty
	 */
	public E pop() throws EmptyStackException;

	/**
	 * Mutator method that pushes an item onto the top of the stack.
	 * 
	 * Pre-condition: A valid stack object exists.
	 * Postcondition: The item is placed at the top of the stack and the stack size increases by 1.
	 * 
	 * @param item the item to be pushed onto this stack.
	 * @return the <code>item</code> argument.
	 */
	public E push(E item);

	/**
	 * Accessor method that returns the depth of the object from the top of the stack.
	 * 
	 * Precondition: A valid stack object exists, and a non-null object is passed.
	 * Postcondition: The stack remains unchanged.
	 * 
	 * @param o the desired object.
	 * @return the depth of the object from the top of the stack,
	 * or -1 if the object is not found in the stack.
	 */
	public int search(Object o);

	public boolean equals(StackADT<E> otherStack);
	
	public Iterator<E> iterator();
	
	public Object[] toArray();
	
	public E[] toArray(E[] copy);
	
	public boolean contains(E obj);
	
	public int size();
	
	public void clear();
}