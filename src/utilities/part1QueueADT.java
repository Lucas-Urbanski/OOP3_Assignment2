package utilities;

public interface part1QueueADT<E> {
	
	public void createQueue();

	public void enqueue(E item);
	
	public E peek();
	
	public E dequeue();
	
	public boolean equals(part1QueueADT<E> otherQueue);
	
	public Iterator<E> iterator();
	
	public Object[] toArray();
	
	public E[] toArray(E[] copy);
	
	public boolean isFull();
	
	public int size();
	
	public boolean isEmpty();
	
	public void dequeueAll();
	
}
