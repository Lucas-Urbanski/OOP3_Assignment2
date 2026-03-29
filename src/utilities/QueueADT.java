package utilities;
/**
 * QueueADT.java
 * 
 * Defines the standard operations for a First-In-First-Out (FIFO) queue.
 * Elements are added to the rear and removed from the front.
 * 
 * @param <E> the type of elements held in this queue
 */
public interface QueueADT<E>{
	/**
	 * Inserts the specified element into the queue.
	 *
	 * Pre-condition:
	 * - A valid queue object exists.
	 * - The element to add is valid for this queue.
	 *
	 * Post-condition:
	 * - If successful, the element is added to the rear of the queue.
	 * - The size of the queue increases by 1.
	 *
	 * @param e the element to add to the queue
	 * @return true if the element was added successfully
	 */
	public boolean add(E e);
	/**
	 * Retrieves, but does not remove, the head of the queue.
	 *
	 * Pre-condition:
	 * - The queue must not be empty.
	 *
	 * Post-condition:
	 * - The queue remains unchanged.
	 *
	 * @return the element at the head of the queue
	 * @throws java.util.NoSuchElementException if the queue is empty
	 */
	public E element();
	/**
	 * Inserts the specified element into the queue.
	 *
	 * Pre-condition:
	 * - A valid queue object exists.
	 * - The element to add is valid for this queue.
	 *
	 * Post-condition:
	 * - If successful, the element is added to the rear of the queue.
	 * - The size of the queue increases by 1.
	 *
	 * @param e the element to add to the queue
	 * @return true if the element was added successfully, false otherwise
	 */
	public boolean offer(E e);
	/**
	 * Retrieves, but does not remove, the head of the queue.
	 *
	 * Pre-condition:
	 * - A valid queue object exists.
	 *
	 * Post-condition:
	 * - The queue remains unchanged.
	 *
	 * @return the head of the queue, or null if the queue is empty
	 */
	public E peek();
	/**
	 * Retrieves and removes the head of the queue.
	 *
	 * Pre-condition:
	 * - A valid queue object exists.
	 *
	 * Post-condition:
	 * - If the queue is not empty, the head element is removed.
	 * - The size of the queue decreases by 1.
	 *
	 * @return the head of the queue, or null if the queue is empty
	 */
	public E poll();
	/**
	 * Retrieves and removes the head of the queue.
	 *
	 * Pre-condition:
	 * - The queue must not be empty.
	 *
	 * Post-condition:
	 * - The head element is removed from the queue.
	 * - The size of the queue decreases by 1.
	 *
	 * @return the element removed from the head of the queue
	 * @throws java.util.NoSuchElementException if the queue is empty
	 */
	public E remove();
}
