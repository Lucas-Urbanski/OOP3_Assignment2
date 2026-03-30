package implementations;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import static java.util.Arrays.copyOf;
import utilities.Iterator;
import utilities.StackADT;

public class MyStack<E> implements StackADT<E> {

	private MyArrayList<E> list;

	public MyStack() {
		list = new MyArrayList<>();
	}

	@Override
	public void push(E toAdd) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException("Cannot add null element to the stack.");
		}
		list.add(toAdd);

	}

	@Override
	public E pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return list.remove(list.size() - 1);
	}

	@Override
	public E peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return list.get(list.size() - 1);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(list.size() - 1 - i);
		}
		return array;
	}

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

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null) {
			throw new NullPointerException("Cannot search for null element in the stack.");
		}
		return list.contains(toFind);
	}

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

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int current = size() - 1;

			@Override
			public boolean hasNext() {
				return current >= 0;
			}

			@Override
			public E next() throws NoSuchElementException {
				if (!hasNext())
					throw new NoSuchElementException();
				return list.get(current--);
			}
		};
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
		return list.size();
	}

	@Override
	public boolean stackOverflow() {
		return false;
	}
}