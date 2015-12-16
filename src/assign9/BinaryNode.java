package assign9;

/**
 * Represents a generically-typed binary tree node. Each binary node contains
 * data, a left child, and a right child.
 * 
 * @author Erin Parker and Hanna Larsen & Romney Doria
 *uID: 						u0741837		u0592859
 *CADE:						hannal			doria
 *Date Modified:  11/04/15
 */
public class BinaryNode<Type> {

  private Type data;

  private BinaryNode<Type> leftChild;

  private BinaryNode<Type> rightChild;

  public BinaryNode(Type _data, BinaryNode<Type> _leftChild,
      BinaryNode<Type> _rightChild) {
    data = _data;
    leftChild = _leftChild;
    rightChild = _rightChild;
  }

  public BinaryNode(Type _data) {
    this(_data, null, null);
  }

  /**
   * Getter method.
   * 
   * @return the node data.
   */
  public Type getData() {
    return data;
  }

  /**
   * Setter method.
   * 
   * @param _data
   *          - the node data to be set.
   */
  public void setData(Type _data) {
    data = _data;
  }

  /**
   * Getter method.
   * 
   * @return the left child node.
   */
  public BinaryNode<Type> getLeftChild() {
    return leftChild;
  }

  /**
   * Setter method.
   * 
   * @param _leftChild
   *          - the left child node to be set.
   */
  public void setLeftChild(BinaryNode<Type> _leftChild) {
    leftChild = _leftChild;
  }

  /**
   * Getter method.
   * 
   * @return the right child node.
   */
  public BinaryNode<Type> getRightChild() {
    return rightChild;
  }

  /**
   * Setter method.
   * 
   * @param _rightChild
   *          - the right child node to be set.
   */
  public void setRightChild(BinaryNode<Type> _rightChild) {
    rightChild = _rightChild;
  }

  /**
   * Returns the leftmost node in the binary tree rooted at this node.
   */
	public BinaryNode<Type> getLeftmostNode() {
		BinaryNode<Type> lc = getLeftChild();
		if (lc == null)
			return this;
		return lc.getLeftmostNode();
	}

	
	/**
	 * Gets the successor that will replace the node being removed
	 */
	public BinaryNode<Type> getSuccessor(){
		return getRightChild().getLeftmostNode();
	}
  /**
   * Returns the rightmost node in the binary tree rooted at this node.
   */
	public BinaryNode<Type> getRightmostNode() {
		BinaryNode<Type> rc = getRightChild();
		if (rc == null)
			return this;
		return rc.getRightmostNode();
	}

  /**
   * Returns the height of the binary tree rooted at this node. The height of a
   * tree is the length of the longest path to a leaf node. Consider a tree with
   * a single node to have a height of zero. 
   */
	public int height() {
		BinaryNode<Type> rc = getRightChild();
		BinaryNode<Type> lc = getLeftChild();
		if (lc == null && rc == null)
			return 0;
		if (lc == null)
			return rc.height() + 1;
		if (rc == null)
			return lc.height() + 1;
		return 1 + Math.max(lc.height(), rc.height());
	}

}
