package assign9;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Class that represents a binary search tree and methods associated with it
 * @param <Type>
 * @author Hanna Larsen & Romney Doria
 *uID: 		u0741837		u0592859
 *CADE:		hannal			doria
 *Date Modified: 11/04/15
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements
		SortedSet<Type> {
	
	// root node
	private BinaryNode<Type> root;
	// parent of current node
	private BinaryNode<Type> parent;
	// size of BST
	private int size;
	private static boolean removalCheck = false;
	private static boolean addCheck = false;
	private static boolean containsCheck = false;
	
	/**
	 * Constructs a new binary search tree
	 */
	public BinarySearchTree() {
		this.root = null;
		this.size = 0;
	}
	
	/**
	 * Ensures that this set contains the specified item.
	 * 
	 * @param item
	 *            - the item whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if the input item was actually inserted); otherwise, returns
	 *         false
	 * @throws NullPointerException
	 *             if the item is null
	 */
	@Override
	public boolean add(Type item){
		addCheck = false;
		if(item == null)
			throw new NullPointerException();
		
		// BST's can't contain duplicates
		if(this.contains(item)){
			addCheck = false;
			return false;
		}
		
		if(this.root == null)
		{
			this.root = new BinaryNode<Type>(item);
			this.parent = this.root;
			this.size++;
			addCheck = true;
			return true;
		}
		else if(addCheck == false)
		{
			addItem(item, this.root);
			if(addCheck == true){
				size++;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Helper method for add because add is recursive
	 * @param item - item to add
	 * @param node - node where new item is being added 
	 * (either as a right or left child)
	 */
	private void addItem(Type item, BinaryNode<Type> node)
 {
		if (item.compareTo(node.getData()) < 0) {
			if (node.getLeftChild() == null) {
				node.setLeftChild(new BinaryNode<Type>(item));
				node.getLeftChild().setData(item);
				addCheck = true;
				return;
			} else
				addItem(item, node.getLeftChild());
		} else {
			if (node.getRightChild() == null) {
				node.setRightChild(new BinaryNode<Type>(item));
				node.getRightChild().setData(item);
				addCheck = true;
				return;
			} else
				addItem(item, node.getRightChild());
		}
	}

	/**
	 * Ensures that this set contains all items in the specified collection.
	 * 
	 * @param items
	 *            - the collection of items whose presence is ensured in this
	 *            set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if any item in the input collection was actually inserted);
	 *         otherwise, returns false
	 * @throws NullPointerException
	 *             if any of the items is null
	 */
	@Override
	public boolean addAll(Collection<? extends Type> items) {
		int listCounter = 0;
		if(items == null)
			throw new NullPointerException();
		
		for (Type t : items) {
			if (t == null)
				throw new NullPointerException();
			add(t);
			if(addCheck == true){
				listCounter++;
			}
			
		}
		if(listCounter > 0) 
			return true;
		else return false;
	}

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item
	 *            - the item sought in this set
	 * @return true if there is an item in this set that is equal to the input
	 *         item; otherwise, returns false
	 * @throws NullPointerException
	 *             if the item is null
	 */
	@Override
	public boolean contains(Type item) {
		containsCheck = false;
		if (item == null)
			throw new NullPointerException();

		if (root == null)
			return false;

		if (root.getData().compareTo(item) == 0){
			containsCheck = true;
			return true;
		}

		return containsItem(item, this.root);
	}
	
	/**
	 * Helper method for contains because contains is recursive
	 * @param item - item in question
	 * @param node - current node being looked at
	 * @return true if BST contains the item, false otherwise
	 */
	private boolean containsItem(Type item, BinaryNode<Type> node) {
		// if the item in question is less than that of the current node
		if (item.compareTo(node.getData()) < 0) {
			if (node.getLeftChild() == null)
				return false;
			// checks if the left child of current node is equal to the item in question
			if (node.getLeftChild().getData().compareTo(item) == 0) {
				containsCheck = true;
				return true;
			}
			else
				return containsItem(item, node.getLeftChild());
		} 
		// if the item in question is greater than or equal to that of the current node
		else {
			if (node.getRightChild() == null)
				return false;
			if (node.getRightChild().getData().compareTo(item) == 0) {
				containsCheck = true;
				return true;
			} 
			else
				return containsItem(item, node.getRightChild());
		}
	}
	
	/**
	 * Determines if for each item in the specified collection, there is an item
	 * in this set that is equal to it.
	 * 
	 * @param items
	 *            - the collection of items sought in this set
	 * @return true if for each item in the specified collection, there is an
	 *         item in this set that is equal to it; otherwise, returns false
	 * @throws NullPointerException
	 *             if any of the items is null
	 */
	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		if(items == null)
			throw new NullPointerException();
		
		for(Type t : items)
		{
			if(t == null)
				throw new NullPointerException();
			// if the item in question is not in the tree
			if(this.contains(t) == false)
				return false;
		}
		return true;
	}

	/**
	 * Returns the first (i.e., smallest) item in this set.
	 * 
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public Type first() throws NoSuchElementException {
		if(this.isEmpty())
			throw new NoSuchElementException();
		// leftmost node is always the smallest
		return root.getLeftmostNode().getData();
	}

	/**
	 * Returns true if this set contains no items.
	 */
	@Override
	public boolean isEmpty() {
		if(root == null)
			return true;
		return false;
	}

	/**
	 * Returns the last (i.e., largest) item in this set.
	 * 
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public Type last() throws NoSuchElementException {
		if(this.isEmpty())
			throw new NoSuchElementException();
		// rightmost node is always the biggest
		return root.getRightmostNode().getData();
	}

	/**
	 * Ensures that this set does not contain the specified item.
	 * 
	 * @param item
	 *            - the item whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if the input item was actually removed); otherwise, returns
	 *         false
	 * @throws NullPointerException
	 *             if the item is null
	 */
	@Override
	public boolean remove(Type item) {
		removalCheck = false;
		
		if(item == null)
			throw new NullPointerException();
		
		// if the item is not in the BST
		if(!this.contains(item))
			return false;
		// otherwise remove item
		removeItem(item, this.root);
		
		if(removalCheck)
			return true;
		else return false;
	}
	
	/**
	 * Helper method that removes an item
	 * Called by remove
	 * @param item - data of node being removed
	 * @param node - node being removed
	 */
	private void removeItem(Type item, BinaryNode<Type> node)
	{
		if (item.compareTo(node.getData()) == 0) {
			// Leaf
			if (node.getLeftChild() == null && node.getRightChild() == null) {
				node.setData(null);
				node = null;
				size--;
				removalCheck = true;
				return;
			}
			// node that will replace the node that is getting removed
			BinaryNode<Type> temp;
			// Node with single child on left
			while (node.getLeftChild() != null && node.getRightChild() == null) {
				if (parent.getLeftChild().getData().compareTo(item) == 0) {
					temp = node.getLeftChild();
					parent.setLeftChild(temp);
					size--;
					removalCheck = true;
					return;
				} 
				else
					parent = parent.getLeftChild();
			}

			// Node with single child on right
			while (node.getLeftChild() == null && node.getRightChild() != null) {
				if (parent.getRightChild().getData().compareTo(item) == 0) {
					temp = node.getRightChild();
					parent.setRightChild(temp);
					size--;
					removalCheck = true;
					return;
				} 
				else
					parent = parent.getRightChild();
			}

			// Node w/ 2 children
			while(node.getLeftChild() != null && node.getRightChild()!= null){
				if(parent.getData().compareTo(item) == 0){
					// node that replaces the one to be removed
					node.setData(node.getSuccessor().getData());
					if(node.getSuccessor().getRightChild() == null){
						temp = node.getSuccessor();
						temp = null;
					}
					// if the successor has a right child
					// replace current successor data with that of right child
					// delete right child
					if(node.getSuccessor().getRightChild() != null){
						node.getSuccessor().setData(node.getSuccessor().getRightChild().getData());
						temp = node.getSuccessor().getRightChild();
						temp = null;
					}
					removalCheck = true;
					size--;
					parent = this.root;
					return;
				}
				if(parent.getLeftChild().getData().compareTo(item) == 0){
					// node that replaces the one to be removed
					node.setData(node.getSuccessor().getData());
					if(node.getSuccessor().getRightChild() == null){
						temp = node.getSuccessor();
						temp = null;
					}
					// if the successor has a right child
					// replace current successor data with that of right child
					// delete right child
					if(node.getSuccessor().getRightChild() != null){
						node.getSuccessor().setData(node.getSuccessor().getRightChild().getData());
						temp = node.getSuccessor().getRightChild();
						temp = null;
					}
					removalCheck = true;
					size--;
					parent = this.root;
					return;
				}
				else if(item.compareTo(parent.getLeftChild().getData()) < 0)
					parent = parent.getLeftChild();
				
				if(parent.getRightChild().getData().compareTo(item) == 0){
					// node that replaces the one to be removed
					node.setData(node.getSuccessor().getData());
					if(node.getSuccessor().getRightChild() == null){
						temp = node.getSuccessor();
						temp = null;
					}
					// if the successor has a right child
					// replace current successor data with that of right child
					// delete right child
					if(node.getSuccessor().getRightChild() != null){
						node.getSuccessor().setData(node.getSuccessor().getRightChild().getData());
						temp = node.getSuccessor().getRightChild();
						temp = null;
					}
					removalCheck = true;
					size--;
					parent = this.root;
					return;
				}
				else if(item.compareTo(parent.getRightChild().getData()) > 0)
					parent = parent.getRightChild();
			}

		}
		
		// if item is smaller than that of the current node
		else if (item.compareTo(node.getData()) < 0) {
			node = node.getLeftChild();
			removeItem(item, node);
		}
		// if item is greater than that of the current node
		else if (item.compareTo(node.getData()) > 0) {
			node = node.getRightChild();
			removeItem(item, node);
		}
	}

	/**
	 * Ensures that this set does not contain any of the items in the specified
	 * collection.
	 * 
	 * @param items
	 *            - the collection of items whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if any item in the input collection was actually removed);
	 *         otherwise, returns false
	 * @throws NullPointerException
	 *             if any of the items is null
	 */
	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		int listCounter = 0;
		if(items == null)
			throw new NullPointerException();
		
		for (Type t : items) {
			if (t == null)
				throw new NullPointerException();
				
			remove(t);
			// checks to make sure item was actually removed
			if(removalCheck == true)
				listCounter++;
			if (listCounter > 0)
				return true;
		}
		return false;
	}

	/**
	 * Returns the number of items in this set.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns an ArrayList containing all of the items in this set, in sorted
	 * order.
	 */
	@Override
	public ArrayList<Type> toArrayList() {
		ArrayList<Type> list = new ArrayList<Type>();
		return inOrder(root, list);
	}
	
	/**
	 * Helper method that does an inorder traversal
	 */
	private ArrayList<Type> inOrder(BinaryNode<Type> n,ArrayList<Type> _list){
		ArrayList<Type> list = _list;
		if(n == null)
			return list;
		inOrder(n.getLeftChild(), list);
		list.add(n.getData());
		inOrder(n.getRightChild(), list);
		return list;
	}

}
