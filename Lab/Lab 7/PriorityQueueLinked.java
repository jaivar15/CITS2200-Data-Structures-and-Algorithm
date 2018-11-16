import CITS2200.IllegalValue;

/**
 * Class Implementation of the PriorityQueue Implementation
 * @author - Varun Jain
 * Referenced from Lecture 13 - Priority Queues
 */
import CITS2200.Iterator;
import CITS2200.OutOfBounds;
import CITS2200.PriorityQueue;
import CITS2200.Underflow;

public class PriorityQueueLinked implements PriorityQueue {

	/**
	 * @param element -  element in the pointed link
	 * @param priority - Elements Order of Importance - descending order
	 * @param next - points to the next link
	 * @param front points to the front of the queue with the highest priority
	 */

		private Link<Object> front;

		public PriorityQueueLinked() { front = null; }

	private class Link<Object>
	{
		Object element;
		int priority;
		Link<Object> next;

		public Link(Object e, int p, Link<Object> n)
		{
			this.element = e;
			this.priority = p;
			this.next = n;
		}
	}

	/**
	 * Removes the element at the front of the queue and returns it, or
	 * throws an exception if the queue is empty.
	 */
	public Object dequeue() throws Underflow {
		if(!isEmpty())
		{
			Object temp = front.element;
			front = front.next;
			return temp;
		} else throw new Underflow("Empty Queue");
	}

	/**
	 *  @param e - object that will be enqueued
	 *  @param p - importance of the object e in the prioity queue
	 *  Places e in the priority queue with key (or priority) k, or throws an exception if k is negative.
	 *  The item is placed in front of all elements with lesser priority, but behind all others.
	 */

	public void enqueue(Object e, int p) throws IllegalValue {
		if(isEmpty() || p > front.priority)
		{
			front = new Link<Object>(e,p,front);
		}
		else
		{
			Link<Object> l = front;
			while(l.next != null && l.next.priority >= p) {
				l = l.next;
			}
			l.next = new Link<Object>(e,p,l.next);
		}
	}

	/**
	 * examine whether the queue is empty or not
	 * @return the element in front of the queue, if queue not empty.
	 * @return an exception Underflow, if queue is empty.
	 */

	public Object examine() throws Underflow {
		if(!isEmpty()) { return front.element; }
		else throw new Underflow("Empty Queue");
	}

	/**
	 * @return true if the priority queue is empty
	 * or false if the priority queue is not empty
	 */

	public boolean isEmpty() { return front == null; }

	/**
	 * returns iterator inferface
	 */
	public Iterator iterator() { return new PQIterater(this); }



	public class PQIterater implements Iterator
	{
		private Link p;

		public PQIterater(PriorityQueueLinked it) { p = it.front; }


	/**
	 * @return boolean result
	 * 	- returns true if there are items in the priority queue
	 * 	- returns false if there are no items in the priority queue.
	 */

		public boolean hasNext() { return p != null; }



	/**
	 * @return object o and moves link to the next element
	 * if the end of the queue is reached meaning there is no next value
	 * then thrown OutOfBounds Exception
	*/

		public java.lang.Object next() throws OutOfBounds
		{
			Object o;
			if(hasNext())
			{
				o = p.element;
				p = p.next;
				return o;
			}
			else throw new OutOfBounds("no more items");
		}

	}


}
