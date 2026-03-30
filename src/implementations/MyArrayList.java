package implementations;

import utilities.ListADT;
import utilities.Iterator;

public class MyArrayList<E> implements ListADT<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private E[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        data = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) {
            throw new NullPointerException();
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        ensureCapacity();

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = toAdd;
        size++;
        return true;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException();
        }

        ensureCapacity();
        data[size] = toAdd;
        size++;
        return true;
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < toAdd.size(); i++) {
            add(toAdd.get(i));
        }

        return true;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        return data[index];
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        checkIndex(index);

        E removed = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < size; i++) {
            if (data[i].equals(toRemove)) {
                return remove(i);
            }
        }

        return null;
    }

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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < size; i++) {
            if (data[i].equals(toFind)) {
                return true;
            }
        }

        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) {
            throw new NullPointerException();
        }

        E[] result;

        if (toHold.length >= size) {
            result = toHold;
        } else {
            result = (E[]) java.lang.reflect.Array.newInstance(
                    toHold.getClass().getComponentType(), size);
        }

        for (int i = 0; i < size; i++) {
            result[i] = data[i];
        }

        if (result.length > size) {
            result[size] = null;
        }

        return result;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];

        for (int i = 0; i < size; i++) {
            result[i] = data[i];
        }

        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

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

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

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