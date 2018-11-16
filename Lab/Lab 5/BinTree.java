import java.util.LinkedList;

import CITS2200.*;

/**
 * Sub Class of ADT BinaryTree Class
 * References: CITS2200 Lecture Slides from Topic 8-12 
 * Checks whether the two binary trees are the same 
 * 
 * @author Varun Jain 
 */

public class BinTree extends BinaryTree{

/**
 * Constructors for the BinTree.class.
 * Initialize a new empty binary tree
 */
	
	public BinTree(){ super(); }
	
/**
 * Constructors for the BinTree.class.
 * @param item the object that will be inserted in the node 
 * @param b1 the left binary subtree 
 * @param b2 the right binary subtree
 */

	public BinTree(Object item, BinaryTree b1, BinaryTree b2){ super(item,b1,b2); }
	
/**
  * Check whether the binary tree has the same structure 
  * if both trees are empty then they are equal return true
  * have the same left and right subtrees then return true 
  * instanceof checks whether the object is an instance of the class
  * @return true if object arg0 is a binary tree and has the same structure as the instance binary tree, 
  * in other words, @return true if both trees are equal
*/
	
	public boolean equals(Object arg0) {
		//checks if arg0 is null or is a BinaryTree
		if(arg0 == null || !(arg0 instanceof BinaryTree)) { return false; }
		//casts object arg0 as subclass BinTree
		BinaryTree<Object> bt = (BinaryTree<Object>) arg0;
		//same structure if they are both empty
		if(this.isEmpty() && bt.isEmpty()) { return true; }
		//one is empty while the other is not therefore structure isn't the same 
		if((this.isEmpty() && !bt.isEmpty()) || (!this.isEmpty() && bt.isEmpty())) { return false;}
		return check(this.getItem(), bt.getItem()) && check(this.getLeft(), bt.getLeft()) && check(this.getRight(), bt.getRight());
	}
	
/**
  * check where object 
  * @param a is the object which the binary tree will get compared 
  * @param b is the object which the binary tree will get compared too
  * @return a boolean result whether object a equals object b
*/
	
	private boolean check(Object a, Object b)
	{
		if (a==null) return b==null;
		return a.equals(b);
	}
	
/**
 * @returns iterator interface BinTreeIterator() for binary tree 
 * basically checks everything in the binary tree
 * used to traverse the binary tree through each element once. 
*/
	
	public Iterator iterator() {
		return new BinTreeIterator(this); 
	}
	
	
/**
 * class implements iterator interface 
*/
	
	public class BinTreeIterator implements Iterator 
	{
		//Initializing a Queue 
		private java.util.Queue<BinaryTree<Object>> q;
		
/**
  * @param bin binary tree to be inserted in the queue
*/
		
		public BinTreeIterator(BinTree bin)
		{
			//construct a LinkList Queue to hold objects in the binary tree
			q = new LinkedList<BinaryTree<Object>>(); 
			if(q != null)
			{
				q.add(bin);
			}
		}


/**
 * checks whether there is an item in the queue 
 * if empty, returns the iterator as false
*/
		
		public boolean hasNext() 
		{
			return !q.isEmpty(); 
		}
		
/**
 * returns the next object in the queue
 * take the iterator to the next spot
 * @return the next object in the queue
 * @throws OutOfBounds if there is no next object
*/

		public Object next() throws OutOfBounds 
		{
			//Creating a temporary variable called temp 
			BinaryTree<Object> temp; 
			//hasNext() checks whether the queue is empty or not 
			if(hasNext())
			{
				//if queue is not empty, q.poll() will retrieve and remove the head of the queue
				//in other words, will pop the leftmost element
				temp = q.poll(); 
				//check if the right subtree is empty or not
				if(!temp.getRight().isEmpty())
				{
					//if not, then enqueue the right subtree
					q.add(temp.getRight());
				}
				//check if the left subtree is empty or not
				if(!temp.getLeft().isEmpty())
				{
					//if not, then enqueue the left subtree. 
					q.add(temp.getLeft());
				}
			}
			else 
			{
				//if the queue is empty, it will throw new OutOfBounds
				throw new OutOfBounds("exit not found");
			}
			//return the item 
			return temp.getItem();  
		} 
	}

}
