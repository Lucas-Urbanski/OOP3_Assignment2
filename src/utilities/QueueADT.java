package utilities;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * QueueADT.java
 * 
 * @author Xander Mulligan & Alessandro Pirela
 * @version 1.1
 * 
 * Class Definition: This interface is designed to be implemented by a queue class.
 * All methods must be implemented by the class.
 * 
 * @param <E> The type of elements that the queue stores.
 */
public interface QueueADT<E> {

	/**
	 * Mutator method to create a queue of a certain size.
	 * 
	 * Precondition: A Queue does not exist.
	 * 
	 * Postcondition: A Queue is created with the specified size.
	 * 
	 * @param size: The size of the queue that is being created.
	 */
	public void createQueue(int size);

	/**
	 * Mutator method used to add an element to the rear of the queue.
	 * 
	 * Precondition: A queue exists, the queue is not full, and the item
	 * being added is valid.
	 * 
	 * Postcondition: The given item is inserted at the back of the queue.
	 * 
	 * @param item the element to be added to the rear of the queue
	 */
	public void enqueue(E item);

	/**
	 * Accessor method used to view the element at the front of the queue
	 * without removing it.
	 * 
	 * Precondition: A queue exists and contains at least one element.
	 * 
	 * Postcondition: The queue remains unchanged and the front element
	 * is returned.
	 * 
	 * @return the element currently at the front of the queue
	 * 
	 * @throws NoSuchElementException if the queue is empty
	 */
	public E peek() throws NoSuchElementException;

	/**
	 * Mutator method used to remove and return the element at the front
	 * of the queue.
	 * 
	 * Precondition: A queue exists and contains at least one element.
	 * 
	 * Postcondition: The front element is removed from the queue and returned.
	 * 
	 * @return the element removed from the front of the queue
	 * 
	 * @throws NoSuchElementException if the queue is empty
	 */
	public E dequeue() throws NoSuchElementException;

	/**
	 * Transformer method to check if two queues contain the
	 * same items in the same order.
	 * 
	 * Precondition: Two Queues exist.
	 * 
	 * Postcondition: An boolean value representing if the queues
	 * are the same.
	 * 
	 * @param otherQueue:
	 * @return true if the queues are the same else false.
	 */
	public boolean equals(QueueADT<E> otherQueue);

	/**
	 * Transformer method that returns the queue as a iterator.
	 * 
	 * Precondition: A Queue exists.
	 * 
	 * Postcondition: A iterator of the queue.
	 * 
	 * @return the iterator representation of the queue.
	 */
	public Iterator<E> iterator();

	/**
	 * Transformer method used to return all elements in the queue as an Object array.
	 * The front of the queue appears at index 0 of the returned array.
	 * 
	 * Precondition: A queue exists.
	 * 
	 * Postcondition: A new array containing all queue elements in queue order
	 * is returned.
	 * 
	 * @return an Object array containing all elements in the queue
	 */
	public Object[] toArray();

	/**
	 * Transformer method used to copy the elements of the queue into a typed array.
	 * The front of the queue appears at index 0 of the returned array.
	 * 
	 * Precondition: A queue exists.
	 * 
	 * Postcondition: An array containing all queue elements in queue order
	 * is returned.
	 * 
	 * @param copy the array into which the elements of the queue are to be stored,
	 *             if it is large enough
	 * @return an array containing all elements in the queue
	 */
	public E[] toArray(E[] copy);

	/**
	 * Accessor method used to determine whether the queue has reached
	 * its maximum capacity.
	 * 
	 * Precondition: A queue exists.
	 * 
	 * Postcondition: A boolean result is returned indicating whether the
	 * queue is full.
	 * 
	 * @return true if the queue is full; false otherwise
	 */
	public boolean isFull();

	/**
	 * Accessor method used to return the number of elements currently
	 * stored in the queue.
	 * 
	 * Precondition: A queue exists.
	 * 
	 * Postcondition: The current number of elements in the queue is returned.
	 * 
	 * @return the number of elements in the queue
	 */
	public int size();

	/**
	 * Transformer method to check if the queue is empty
	 * 
	 * Precondition: A Queue exists.
	 * 
	 * Postcondition: A boolean value that represents is the queue
	 * is empty.
	 * 
	 * @return true if the queue is empty else false.
	 */
	public boolean isEmpty();

	/**
	 * Mutator method to remove all of the elements in the queue.
	 * 
	 * Precondition: A Queue exists.
	 * 
	 * Postcondition: The all of the elements of the queue is removed.
	 */
	public void dequeueAll();
}
