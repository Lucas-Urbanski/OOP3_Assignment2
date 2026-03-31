package implementations;

import utilities.Iterator;
import utilities.QueueADT;
import exceptions.EmptyQueueException;

/**
 * MyQueue.java
 * 
 * @author Xander Mulligan
 * @version 1.0
 * 
 * Class Description: Implementation of the QueueADT interface. Uses MyDLL as a base for the class.
 */
public class MyQueue<E> implements QueueADT<E> {
	
	// Attributes
	private MyDLL<E> queue;
	private int maxSize;
	
	/**
	 * Constructs an Queue with no max size
	 */
	public MyQueue() {
		queue = new MyDLL<E>();
		maxSize = -1;
	}
	
	/**
	 * Constructs an Queue with a inputed max size that is over -1
	 */
	public MyQueue(int size) {
		queue = new MyDLL<E>();
		if (size >= 0) {
			maxSize = size;
		} else {
			maxSize = -1;
		}
	}

	/**
	 * Adds an element to the end of the queue.
	 * 
	 * @throws NullPointerException if the inputed element is null
	 */
	@Override
	public void enqueue(E toAdd) throws NullPointerException {
		if(toAdd == null) {
			throw new NullPointerException("Can not be Null");
		} else {
			if(queue.size() == maxSize && maxSize >= 0) {
				return;
			}
			queue.add(toAdd);
		}
	}

	/**
	 * Removes the first element in the queue.
	 * 
	 * @return the element being removed
	 * @throws EmptyQueueException if used on a empty queue
	 */
	@Override
	public E dequeue() throws EmptyQueueException {
		if (queue.isEmpty()) {
			throw new EmptyQueueException("Can not remove from a empty queue");
		} else {
			return queue.remove(0);
		}
	}

	/**
	 * Look at the first element in the queue.
	 * 
	 * @return the first element in the queue
	 * @throws EmptyQueueException if used on a empty queue
	 */
	@Override
	public E peek() throws EmptyQueueException {
		if (queue.isEmpty()) {
			throw new EmptyQueueException("Can not remove from a empty queue");
		} else {
			return queue.get(0);
		}
	}

	/**
	 * Empties the queue out.
	 */
	@Override
	public void dequeueAll() {
		queue.clear();
	}
	/**
	 * Checks to see if the queue has any element in it.
	 * 
	 * @return true if the queue has nothing else false
	 */
	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	/**
	 * Checks to see if the inputed element is in the queue.
	 * 
	 * @return true if the element is in the queue else false
	 * @throws NullPointerException if the inputed element is null
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if(toFind == null) {
			throw new NullPointerException("Can not be Null");
		} else {
			return queue.contains(toFind);
		}
	}

	/**
	 * Searches for a element in the queue.
	 * 
	 * @return the index of the element if found else -1
	 */
	@Override
	public int search(E toFind) {
		if(toFind == null) {
			throw new NullPointerException("Can not be Null");
		} 
		int i = 1;
		while(i <= queue.size()) {
			E atIndex = queue.get(i - 1);
			if (atIndex == toFind) {
				return i;
			} else {
				i++;
			}
		}
		return -1;
	}

	/**
	 * Makes a Iterator version of the queue.
	 * 
	 * @return the queue as a Iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return queue.iterator();
	}

	/**
	 * Checks if two queues are have the exact same elements at the same index.
	 * 
	 * @return true is the queues are the same else false
	 */
	@Override
	public boolean equals(QueueADT<E> that) {
		if(that == null) {
			return false;
		}
		if(that.size() != queue.size()) {
			return false;
		}
		if(that == queue) {
			return true;
		}
		
		int i = 1;
		while(that.size() >= i && queue.size() >= i) {
			E element = queue.get(i - 1);
			if(that.search(element) != i) {
				return false;
			}
			i++;
		}
		return true;
	}

	/**
	 * Makes a array version of the queue.
	 * 
	 * @return the array with the queue's elements
	 */
	@Override
	public Object[] toArray() {
		return queue.toArray();
	}

	/**
	 * Puts the queue into the array that was past in.
	 * 
	 * @return the array with all of the queue's elements
	 * @throws NullPointerException if the array is null
	 */
	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		if(holder == null) {
			throw new NullPointerException("Can not be Null");
		} 
		return queue.toArray(holder);
	}

	/**
	 * Checks to see if the queue size is at the max size if a size was entered.
	 * 
	 * @return true is max size equals the queue size else false
	 */
	@Override
	public boolean isFull() {
		if (maxSize == queue.size() && maxSize >= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets the current number of elements in the queue.
	 * 
	 * @return int of the size of the queue
	 */
	@Override
	public int size() {
		return queue.size();
	}

}
