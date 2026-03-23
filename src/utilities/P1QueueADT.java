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
public interface P1QueueADT<E> {

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
	 * Mutator method to add an element to the back of the queue.
	 * 
	 * Precondition: A Queue exists and the item is not null.
	 * 
	 * Postcondition: The item is added to the back of the queue.
	 * 
	 * @param item: The item that is being added to the back of the queue.
	 */
	public void enqueue(E item);

	/**
	 * Accessor method to return the first element in the queue.
	 * 
	 * Precondition: A Queue exists and is not empty.
	 * 
	 * Postcondition: The first element of the queue is returned.
	 * 
	 * @return the item that is at the front of the queue.
	 * 
	 * @throws NoSuchElementException if the queue is empty.
	 */
	public E peek() throws NoSuchElementException;

	/**
	 * Mutator method to remove the first element in the queue.
	 * 
	 * Precondition: A Queue exists.
	 * 
	 * Postcondition: The first element of the queue is removed.
	 * 
	 * @return the item that was removed.
	 * 
	 * @throws NoSuchElementException if the queue is empty.
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
	public boolean equals(P1QueueADT<E> otherQueue);

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

	public Object[] toArray();

	public E[] toArray(E[] copy);

	public boolean isFull();

	/**
	 * Accessor method to return the queue size.
	 * 
	 * Precondition: A Queue exists.
	 * 
	 * Postcondition: A integer of the size of the queue.
	 * 
	 * @return a integer that is representation of the size of the queue.
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
