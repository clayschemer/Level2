package queue;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last = null;
	private int size = 0;

	public FifoQueue() {
		super();
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}

	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	x the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E x) {
		QueueNode<E> n = new QueueNode<E>(x);
		if (size == 0) {
			last = n;
			last.next = n;
		} else {
			n.next = last.next;
			last.next = n;
			last = n;
		}
		size++;
		return true;
	}
	
	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if (size == 0) {
			return null;
		}
		QueueNode<E> firstNode = last.next;
		E firstElement = firstNode.element;
		last.next = firstNode.next;
		size--;
		return firstElement;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if (size == 0) {
			return null;
		}
		return last.next.element;
	}
	
	/**
	 * Appends the specified queue to this queue
	 * 
	 * @param q
	 *            the queue to append
	 * @post: all elements from the specified queue are appended to this queue.
	 *        The specified queue (q) is empty
	 */
	public void append(FifoQueue<E> q) {
		if ((q != null && q.size > 0) && last != null) {
			size += q.size();
			QueueNode<E> tmp = last.next;
			last.next = q.last.next;
			q.last.next = tmp; 
			last = q.last;
			q = null;
		} else if (q != null && last == null) {
			size = q.size();
			last = q.last;
			q.size = 0;
		}
	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;
		
		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}
	
	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;

		private QueueIterator() {
			pos = last;
		}

		public boolean hasNext() {
			return !isEmpty();
		}

		public E next() {
			if (hasNext()) {
				pos = pos.next;
				return pos.element;
			} else {
				throw new NoSuchElementException();
			}
		}

		public void remove() {
			if (pos == null) {
				throw new UnsupportedOperationException();
			} else {
				QueueNode<E> temp = pos.next;
				pos.next = temp.next;
				size--;
			}

		}

	}

}
