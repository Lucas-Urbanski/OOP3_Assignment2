package implementations;

import utilities.ListADT;
import utilities.Iterator;

// Generic ArrayList implementation (can store any type E)
public class MyArrayList<E> implements ListADT<E> {

    // Default starting capacity of the array
    private static final int DEFAULT_CAPACITY = 10;

    // Underlying array to store elements
    private E[] data;

    // Current number of elements in the list
    private int size;

    // Constructor: creates array with default capacity
    @SuppressWarnings("unchecked")
    public MyArrayList() {
        data = (E[]) new Object[DEFAULT_CAPACITY]; // create array
        size = 0; // list starts empty
    }

    // Returns number of elements in list
    @Override
    public int size() {
        return size;
    }

    // Clears the list (does NOT delete array, just resets size)
    @Override
    public void clear() {
        size = 0;
    }

    // Adds element at a specific index
    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        // Cannot add null values
        if (toAdd == null) {
            throw new NullPointerException();
        }

        // Check if index is valid
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        // Ensure there's space in array
        ensureCapacity();

        // Shift elements to the right to make space
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        // Insert new value
        data[index] = toAdd;
        size++;
        return true;
    }

    // Adds element to the end of the list
    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException();
        }

        ensureCapacity(); // make sure there's room
        data[size] = toAdd; // add at end
        size++;
        return true;
    }

    // Adds all elements from another list
    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException();
        }

        // Loop through incoming list and add each element
        for (int i = 0; i < toAdd.size(); i++) {
            add(toAdd.get(i));
        }

        return true;
    }

    // Returns element at given index
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        checkIndex(index); // validate index
        return data[index];
    }

    // Removes element at given index
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        checkIndex(index);

        E removed = data[index]; // store removed value

        // Shift elements left to fill gap
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null; // clear last element
        size--;
        return removed;
    }

    // Removes first occurrence of a value
    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) {
            throw new NullPointerException();
        }

        // Find matching element
        for (int i = 0; i < size; i++) {
            if (data[i].equals(toRemove)) {
                return remove(i); // reuse index-based remove
            }
        }

        return null; // not found
    }

    // Replaces element at index and returns old value
    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) {
            throw new NullPointerException();
        }

        checkIndex(index);

        E oldValue = data[index];
        data[index] = toChange;
        return oldValue;
    }

    // Returns true if list is empty
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Checks if list contains a value
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) {
            throw new NullPointerException();
        }

        // Loop through list and compare values
        for (int i = 0; i < size; i++) {
            if (data[i].equals(toFind)) {
                return true;
            }
        }

        return false;
    }

    // Converts list into array (typed version)
    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) {
            throw new NullPointerException();
        }

        E[] result;

        // Use given array if it's big enough
        if (toHold.length >= size) {
            result = toHold;
        } else {
            // Otherwise create a new array of correct type
            result = (E[]) java.lang.reflect.Array.newInstance(
                    toHold.getClass().getComponentType(), size);
        }

        // Copy elements
        for (int i = 0; i < size; i++) {
            result[i] = data[i];
        }

        // Add null terminator if extra space exists
        if (result.length > size) {
            result[size] = null;
        }

        return result;
    }

    // Converts list into Object array
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];

        for (int i = 0; i < size; i++) {
            result[i] = data[i];
        }

        return result;
    }

    // Returns iterator for looping through list
    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    // Doubles array size if full
    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (size == data.length) {
            E[] newArray = (E[]) new Object[data.length * 2];

            // Copy old elements into new array
            for (int i = 0; i < data.length; i++) {
                newArray[i] = data[i];
            }

            data = newArray; // replace old array
        }
    }

    // Checks if index is valid
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    // Inner class: Iterator implementation
    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex = 0;

        // Checks if more elements exist
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        // Returns next element
        @Override
        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            E value = data[currentIndex];
            currentIndex++;
            return value;
        }
    }
}