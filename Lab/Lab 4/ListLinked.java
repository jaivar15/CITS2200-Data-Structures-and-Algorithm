import CITS2200.WindowLinked;
import CITS2200.Link;
import CITS2200.List;
import CITS2200.OutOfBounds;
/**
 * Implements List ADT using Linklists 
 * 
 * @author Varun Jain 
 */
public class ListLinked implements List {
	
	//Before Link is the position before the first element in the list 
	private Link before; 
	
	//After Link is the after last Link 
	private Link after; 
	
/**
 * Initializes an empty list with two associated window positions 
 * Set values for links before and after 
 */
	
	public ListLinked()
	{
		after = new Link(null, null); 
		before = new Link(null, after); 
	}
/**
 * Initializes w to the after last position
*/
	public void afterLast(WindowLinked w) 
	{
		w.link = after; 
	}
/**
 * Initializes w to the before first position 
 */
	@Override
	public void beforeFirst(WindowLinked w) 
	{
		w.link = before; 
		
	}
/** 
 * Throws an exception if w is in the before first or after last position, 
 * otherwise deletes and returns the element under w, and places w over the 
 * next element. 
 */
	@Override
	public Object delete(WindowLinked w) throws OutOfBounds 
	{   
		Object obj = null; 
		if(!isBeforeFirst(w) && !isAfterLast(w))
		{
			obj = w.link.item; 
			//successor is after the last position, then the last position will be made the current one 
			if(w.link.successor == after)
			{
				after = w.link; 
			}
			//item link passed to the next successor 
			w.link.item = w.link.successor.item; 
			//successor link passed to the next successor 
			w.link.successor = w.link.successor.successor; 
		}
		else 
		{
			throw new OutOfBounds("can not be deleted");
		} return obj; 
	}
/**
 * Throws an exception if w is in the before first or after last 
 * position, otherwise returns the element under w.
 */
	@Override
	public Object examine(WindowLinked w) throws OutOfBounds 
	{
		if(!isBeforeFirst(w) && !isAfterLast(w))
		{
			return w.link.item; 
		}
		else 
		{
			throw new OutOfBounds("List Cannot Be Examined"); 
		}
	}
/**
 * Throws an exception if w is over the after last position, otherwise an
 * extra element e is added to the list after w. 
 */
	@Override
	public void insertAfter(Object obj, WindowLinked w) throws OutOfBounds 
	{
		if(!isAfterLast(w))
		{
			//new link object will be created 
			//insert obj
			w.link.successor = new Link(obj, w.link.successor); 
		}
		else 
		{
			throw new OutOfBounds("over the after last position"); 
		}
	}
/**
 * Throws an exception if w is over the before the first position, 
 * otherwise an extra element e is added to the list before w
 */
	@Override
	public void insertBefore(Object obj, WindowLinked w) throws OutOfBounds 
	{
		if(!isBeforeFirst(w))
		{
			//creating a node at the last position of the list
			w.link.successor = new Link(w.link.item, w.link.successor);
			if(isAfterLast(w))  //after last position
				after = w.link.successor; 
				//store the element that you want to insert 
				w.link.item = obj; 
				//update the position of the window
				w.link = w.link.successor; 
		}
		else 
		{
			throw new OutOfBounds("over the after last position"); 
		}
	}
/**
 * True if w is over the after last position
 */
	@Override
	public boolean isAfterLast(WindowLinked w) 
	{
		return (w.link == after);
	}
/**
 * True if w is over the before last position
 */
	@Override
	public boolean isBeforeFirst(WindowLinked w) 
	{
		return (w.link == before); 
	}
/**
 * Return true if the list is empty
 */ 
	@Override
	public boolean isEmpty() 
	{
		return before.successor == after;
	}
/**
 * Throws an exception if w is over the last position, otherwise 
 * moves w to the next window position 
 */
	@Override
	public void next(WindowLinked w) throws OutOfBounds 
	{
		if(!isAfterLast(w)) 
		{
			w.link = w.link.successor; 
		}
		else 
		{
			throw new OutOfBounds("list has already ended");
		}
			
	}
/**
 * Throws an exception if w over if the first before position, otherwise 
 * moves w to the previous window position. 
 */
	@Override
	public void previous(WindowLinked w) throws OutOfBounds 
	{
		Link cursor = before; 
		if(!isBeforeFirst(w))
		{
			
			while(cursor.successor != w.link)
			{
				 cursor = cursor.successor; 
			}
			w.link = cursor; 
		}
		else 
		{
			throw new OutOfBounds("calling previous before start"); 
		}
	}

	/**
	 * Throws an exception if w is the before first or after last position, 
	 * otherwise replaces the element under w with e and return the old element. 
	 */
	@Override
	public Object replace(Object obj, WindowLinked w) throws OutOfBounds 
	{
		Object temp; 
		if(!isBeforeFirst(w) && !isAfterLast(w))
		{
			temp = w.link.item; 
			w.link.item = obj; 
		}
		else 
		{
			throw new OutOfBounds("out of the list"); 
		}
		return temp;
	}

}
