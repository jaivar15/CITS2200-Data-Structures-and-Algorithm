import CITS2200.Overflow;
import CITS2200.Stack;
import CITS2200.Underflow;

/**
 * implementing stack using arrays
 * private variable top shows the index of the top element
 *@author Varun Jain
 */

public class StackBlock implements Stack {

	private Object[] stack;
	private static int top;

/**
 * create an empty stack of size s
 * @param s the size of the array
 */
	public StackBlock(int s)
	{
		stack = new Object[s];
		top = -1;
	}

/**
 * examine the top of the stack
 * @return the top of the stack
 * @exception underflow if the stack is empty
 */

	public Object examine() throws Underflow
	{
		if(!isEmpty())
		{
			return stack[top];
		}else
			throw new Underflow("Stack is empty");
	}

/**
 * check whether the stack is full or not
 * @return true if the stack is full, else false
 */

	public boolean isFull()
	{
		return (top == (stack.length - 1));
	}

/**
 * check whether the stack is empty or not
 * if empty, return true else return false;
 */

	public boolean isEmpty()
	{
		return top == -1;
	}

/**
 * Remove the top of the stack
 * return the top object in the stack
 * @exception underflow if the stack is empty
 */
	public Object pop() throws Underflow {
		if(!isEmpty())
		{
			Object a = stack[top--];
			return a;
		} else throw new Underflow("Stack is Empty");
	}

/**
 * add object onto the top of the stack
 * @exception Overflow when the stack if full
 */

	public void push(Object arg0) throws Overflow
	{
		if(!isFull())
		{
			stack[++top] = arg0;
		} else throw new Overflow("Stack is Full");
	}
}
