package implementations;
import utilities.ListADT;
import utilities.Iterator;

public class MyDLL<E> implements ListADT<E> {

    private MyDLLNode<E> head;
    private MyDLLNode<E> tail;
    private int size;

    public MyDLL() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
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

        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);

        // add into empty list
        if (size == 0) {
            head = newNode;
            tail = newNode;
            size++;
            return true;
        }

        // add at front
        if (index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            size++;
            return true;
        }

        // add at end
        if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            size++;
            return true;
        }

        // add in middle
        MyDLLNode<E> current = getNode(index);
        MyDLLNode<E> previous = current.prev;

        previous.next = newNode;
        newNode.prev = previous;
        newNode.next = current;
        current.prev = newNode;

        size++;
        return true;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException();
        }

        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);

        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

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
        return getNode(index).data;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        MyDLLNode<E> current = getNode(index);
        E removedData = current.data;

        // only one node
        if (size == 1) {
            head = null;
            tail = null;
        }
        // removing head
        else if (current == head) {
            head = head.next;
            head.prev = null;
        }
        // removing tail
        else if (current == tail) {
            tail = tail.prev;
            tail.next = null;
        }
        // removing middle
        else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        size--;
        return removedData;
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) {
            throw new NullPointerException();
        }

        MyDLLNode<E> current = head;
        int index = 0;

        while (current != null) {
            if (current.data.equals(toRemove)) {
                return remove(index);
            }
            current = current.next;
            index++;
        }

        return null;
    }

    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) {
            throw new NullPointerException();
        }

        MyDLLNode<E> current = getNode(index);
        E oldData = current.data;
        current.data = toChange;
        return oldData;
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

        MyDLLNode<E> current = head;

        while (current != null) {
            if (current.data.equals(toFind)) {
                return true;
            }
            current = current.next;
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

        MyDLLNode<E> current = head;
        int i = 0;

        while (current != null) {
            result[i] = current.data;
            current = current.next;
            i++;
        }

        // Java collection-style behavior
        if (result.length > size) {
            result[size] = null;
        }

        return result;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        MyDLLNode<E> current = head;
        int i = 0;

        while (current != null) {
            result[i] = current.data;
            current = current.next;
            i++;
        }

        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new DLLIterator();
    }

    private MyDLLNode<E> getNode(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        MyDLLNode<E> current;

        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }

        return current;
    }

    private class DLLIterator implements Iterator<E> {
        private MyDLLNode<E> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            E data = current.data;
            current = current.next;
            return data;
        }
    }
}