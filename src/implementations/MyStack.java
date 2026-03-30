package implementations;

import java.util.EmptyStackException;

import utilities.Iterator;
import utilities.StackADT;

public class MyStack<E> implements StackADT<E>{

	private MyArrayList<E> List;

	public MyStack() {
		List = new MyArrayList<>();
	}

	@Override
	public void push(E toAdd) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException("Cannot add null element to the stack.");
		}
		List.add(toAdd);
		
	}

	@Override
	public E pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return List.remove(List.size() - 1);
	}

	@Override
	public E peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return List.get(List.size() - 1);
	}

	@Override
	public void clear() {
		List.clear();
	}

	@Override
	public boolean isEmpty() {
		return List.isEmpty();
	}

	@Override
	public Object[] toArray() {
		return List.toArray();
	}

	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		return List.toArray(holder);
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null) {
			throw new NullPointerException("Cannot search for null element in the stack.");
		}
		return List.contains(toFind);
	}

	@Override
	public int search(E toFind) {
		for (int i = 1; i <= List.size(); i++) {
			E element = List.get(List.size() - i);
			 if (element.equals(toFind)) {
				 return i;
			 }
		}
		return -1;
	}

	@Override
	public Iterator<E> iterator() {
		return List.iterator();
	}

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

	@Override
	public int size() {
		return List.size();
	}

	@Override
	public boolean stackOverflow() {
		return false;
	}
}
