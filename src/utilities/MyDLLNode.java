package utilities;

/**
 * Node class used by MyDLL.
 * Each node stores one data value and references to both the next
 * and previous node in the list.
 *
 * @param <E> the type of element stored in the node
 */
class MyDLLNode<E> {
	E data;
	MyDLLNode<E> next;
	MyDLLNode<E> prev;

	/**
	 * Creates a node containing the given data.
	 *
	 * @param data the element to store in the node
	 */
	public MyDLLNode(E data) {
		this.data = data;
		this.next = null;
		this.prev = null;
	}
}