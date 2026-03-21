package utilities;

public interface part1StackADT<E> {
	
	public void createStack();

	public boolean isEmpty();
	
	public E peek();
	
	public E pop();
	
	public E push(E item);
	
	public int search(E item);
	
	public boolean equals(part1StackADT<E> otherStack);
	
	public Iterator<E> iterator();
	
	public Object[] toArray();
	
	public E[] toArray(E[] copy);
	
	public boolean contains(E obj);
	
	public int size();
	
	public void clear();
	
}
