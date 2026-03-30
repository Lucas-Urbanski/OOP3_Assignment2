package implementations;


public class MyDLLNode<E> {

    E data;                   // stores the value
    MyDLLNode<E> next;        // points to next node
    MyDLLNode<E> prev;        // points to previous node

    public MyDLLNode(E data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}