   import CITS2200.Deque;
import CITS2200.Underflow;
import CITS2200.Overflow;

/**
 * Cyclic Block Representation of a Deque
 * @author Varun Jain
 **/

public class DequeCyclic implements Deque {

	private Object[] block;
	private int front;             // index of the left-most object in the deque
	private int rear;	          // index of the right-most object in the deque
	private int size;
	private int elementCount;   

	/**
	 * create an empty deque
	 * @param s the size of the deque
	 */

	public DequeCyclic(int s)
	{
		block = new Object[s];
		front = 0;
		rear = -1;
		size = s;
		elementCount = 0;
	}
	/**
	 * @return boolean result to indicate whether the deque is empty or not
	*/
	public boolean isEmpty()
	{
		return  elementCount == 0;
	}

	/**
	 * @return boolean result to indicate whether the deque is full or not
	*/
	public boolean isFull()
	{
		return elementCount == size;
	}


	/**
	 * @return the left-most object in the deque, or throw an Underflow
	   exception if the deque is empty.
	*/
	public Object peekLeft() throws Underflow {

		if(!isEmpty())
		{
			return block[front];
		}
		else
		{
			throw new Underflow("empty deque");
		}
	}

	/**
	 * @return return the right-most object in the deque, or throw an
	   Underflow exception if the deque is empty.
	*/
	@Override
	public Object peekRight() throws Underflow {

		if(!isEmpty())
		{
			return block[rear];
		}
		else
		{
			throw new Underflow("empty deque");
		}
	}

	/**
	 *
		remove and return the left-most object in the deque, or throw an
		Underflow exception if the deque is empty.
	*/

	public Object popLeft() throws Underflow {
		Object o;
		if(!isEmpty())
		{
			o = block[front];
			front = (front+1)%block.length;
			elementCount--;
		}
		else
		{
			throw new Underflow("empty deque");
		}
		return o;
	}

	/**
	 *
		remove and return the right-most object in the deque, or throw an
		Underflow exception if the deque is empty.
	*/

	public Object popRight() throws Underflow {
		Object o;
		if(!isEmpty())
		{
			o = block[rear];
			rear = (rear-1+block.length)%block.length;
			elementCount--;

		}
		else
		{
			throw new Underflow("empty deque");
		}
	return o;
	}


	/**
	 * add object arg0 as the left-most object in the deque, or throw an
	   Overflow exception if the deque is full.
	*/

	public void pushLeft(Object arg0) throws Overflow {

		if(!isFull())
		{
			front = (front - 1 + block.length) % block.length;
			block[front] = arg0;
			elementCount++;
		}
		else
		{
			throw new Overflow("FULL");
		}

	}

	/**
	 *  add object arg0 as the right-most object in the deque, or throw an
		Overflow exception if the deque is full.
	*/

	public void pushRight(Object arg0) throws Overflow
	{
		if(!isFull())
		{
			rear = (rear + 1) % block.length;
			block[rear] = arg0;
			elementCount++;
		}
		else throw new Overflow("full deque");
	}

}
