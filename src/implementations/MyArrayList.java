package implementations;

import utilities.ListADT;

import java.util.NoSuchElementException;

import utilities.Iterator;

/**
 * MyArrayList.java
 * 
 * @author Jordi Usen
 * @version 1.0
 * 
 * MyArrayList is a generic dynamic array implementation of the ListADT interface.
 * This class stores elements in an internal array that automatically resizes
 * when capacity is reached. Elements are stored in order and indexed from 0.
 */
public class MyArrayList<E> implements ListADT<E> {

    /**
     * Default initial capacity of the list.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Internal array used to store elements.
     */
    private E[] data;

    /**
     * Current number of elements in the list.
     */
    private int size;

    /**
     * Constructs an empty list with default capacity.
     */
    @SuppressWarnings("unchecked")
    public MyArrayList() {
        data = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Returns the number of elements in the list.
     * 
     * @return the size of the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all elements from the list.
     * 
     * Postcondition: size is reset to 0.
     */
    @Override
    public void clear() {
        size = 0;
    }

    /**
     * Inserts an element at a specific index.
     * 
     * @param index the position to insert the element
     * @param toAdd the element to insert
     * @return true if insertion is successful
     * @throws NullPointerException if toAdd is null
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @Override
    public boolean add(int index, E toAdd) {
        if (toAdd == null)
            throw new NullPointerException();
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        ensureCapacity();

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = toAdd;
        size++;
        return true;
    }

    /**
     * Appends an element to the end of the list.
     * 
     * @param toAdd the element to add
     * @return true if successful
     * @throws NullPointerException if toAdd is null
     */
    @Override
    public boolean add(E toAdd) {
        if (toAdd == null)
            throw new NullPointerException();

        ensureCapacity();
        data[size++] = toAdd;
        return true;
    }

    /**
     * Adds all elements from another list.
     * 
     * @param toAdd the list containing elements to add
     * @return true if successful
     * @throws NullPointerException if toAdd is null
     */
    @Override
    public boolean addAll(ListADT<? extends E> toAdd) {
        if (toAdd == null)
            throw new NullPointerException();

        for (int i = 0; i < toAdd.size(); i++) {
            add(toAdd.get(i));
        }

        return true;
    }

    /**
     * Retrieves the element at a given index.
     * 
     * @param index the index of the element
     * @return the element at index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    /**
     * Removes the element at a given index.
     * 
     * @param index the index to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @Override
    public E remove(int index) {
        checkIndex(index);

        E removed = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[--size] = null;
        return removed;
    }

    /**
     * Removes the first occurrence of a specified element.
     * 
     * @param toRemove the element to remove
     * @return the removed element, or null if not found
     * @throws NullPointerException if toRemove is null
     */
    @Override
    public E remove(E toRemove) {
        if (toRemove == null)
            throw new NullPointerException();

        for (int i = 0; i < size; i++) {
            if (data[i].equals(toRemove)) {
                return remove(i);
            }
        }

        return null;
    }

    /**
     * Replaces the element at a specified index.
     * 
     * @param index the index to modify
     * @param toChange the new value
     * @return the old value
     * @throws NullPointerException if toChange is null
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @Override
    public E set(int index, E toChange) {
        if (toChange == null)
            throw new NullPointerException();
        checkIndex(index);

        E old = data[index];
        data[index] = toChange;
        return old;
    }

    /**
     * Checks if the list is empty.
     * 
     * @return true if size is 0, otherwise false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if the list contains a given element.
     * 
     * @param toFind the element to search for
     * @return true if found, false otherwise
     * @throws NullPointerException if toFind is null
     */
    @Override
    public boolean contains(E toFind) {
        if (toFind == null)
            throw new NullPointerException();

        for (int i = 0; i < size; i++) {
            if (data[i].equals(toFind))
                return true;
        }

        return false;
    }

    /**
     * Ensures the internal array has enough capacity.
     * Doubles the array size if full.
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (size == data.length) {
            E[] newArray = (E[]) new Object[data.length * 2];

            for (int i = 0; i < data.length; i++) {
                newArray[i] = data[i];
            }

            data = newArray;
        }
    }

    /**
     * Validates an index.
     * 
     * @param index the index to check
     * @throws IndexOutOfBoundsException if invalid
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    /**
     * Returns an iterator over the elements in the list.
     * 
     * @return an iterator for the list
     */
    @Override
    public Iterator<E> iterator() { 
        return new ArrayListIterator();

    }

    /**
     * Private inner class that implements the Iterator interface for MyArrayList.
     * It provides methods to iterate over the elements of the list.
     */
    private class ArrayListIterator implements Iterator<E> {
        
        // Aatributes
        private E[] copyOfElements;
        private int current = 0;

        /**
         * Creates a new ArrayListIterator and initializes the copyOfElements array with the elements of the list.
         */
        @SuppressWarnings("unchecked")
		public ArrayListIterator() {
			copyOfElements = (E[]) new Object[size()];
			for (int i = 0; i < size(); i++) {
				copyOfElements[i] = data[i];
			}
		}

        /**
         * Checks if there are more elements to iterate over.
         * 
         * @return true if there are more elements to iterate over, otherwise false
         */
        @Override
        public boolean hasNext() {
            return current < size;
        }

        /**
         * Returns the next element in the iteration.
         * 
         * @return the next element in the iteration
         * @throws NoSuchElementException if there are no more elements to return
         */
        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return data[current++];
        }
    }
}