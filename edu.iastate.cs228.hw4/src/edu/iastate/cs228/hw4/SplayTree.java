package edu.iastate.cs228.hw4;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @Author Ezra Odole 
 *
 */

/**
 * 
 * This class implements a splay tree. Add any helper methods or implementation
 * details you'd like to include.
 *
 */

public class SplayTree<E extends Comparable<? super E>> extends AbstractSet<E> {
	public Node root;
	protected int size;

	public class Node // made public for grading purpose
	{
		public E data;
		public Node left;
		public Node parent;
		public Node right;

		public Node(E data) {
			this.data = data;
		}

		@Override
		public Node clone() {
			return new Node(data);
		}

		@Override
		public String toString() {
			return data.toString();
		}
	}

	/**
	 * Default constructor constructs an empty tree.
	 */
	public SplayTree() {
		size = 0;
	}

	/**
	 * Needs to call addBST() later on to complete tree construction.
	 */
	public SplayTree(E data) {
		size = 0;
		addBST(data);
	}

	/**
	 * Copies over an existing splay tree. The entire tree structure must be copied.
	 * No splaying. Calls cloneTreeRec().
	 * 
	 * @param tree
	 */
	public SplayTree(SplayTree<E> tree) {

		root = cloneTreeRec(tree.root);

	}

	/**
	 * Recursive method called by the constructor above.
	 * 
	 * @param subTree
	 * @return
	 */
	public Node cloneTreeRec(Node subTree) {

		// base case
		if (subTree == null)
			return null;

		// copy subtree root
		Node subRootCopy = new Node(subTree.data);

		// recurse for left and right tree
		subRootCopy.left = cloneTreeRec(subTree.left);
		subRootCopy.right = cloneTreeRec(subTree.right);

		// return copied element
		return subRootCopy;
	}

	/**
	 * This function is here for grading purpose. It is not a good programming
	 * practice.
	 * 
	 * @return element stored at the tree root
	 */
	public E getRoot() {
		if (root == null)
			return null;
		else
			return root.data;
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * Clear the splay tree.
	 */
	@Override
	public void clear() {
		size = 0;
		root = null;
	}

	// ----------
	// BST method
	// ----------

	/**
	 * Adds an element to the tree without splaying. The method carries out a binary
	 * search tree addition. It is used for initializing a splay tree.
	 * 
	 * Calls link().
	 * 
	 * @param data
	 * @return true if addition takes place false otherwise (i.e., data is in the
	 *         tree already)
	 */
	public boolean addBST(E data) {
		if (root == null) {
			root = new Node(data);
			size++;
			return true;
		}

		Node current = root;

		while (true) {
			int comp = current.data.compareTo(data);

			if (comp == 0) {
				return false;
			} else if (comp > 0) {
				if (current.left != null) {
					current = current.left;
				} else {
					link(current, new Node(data));
					++size;
					return true;
				}
			} else // comp < 0
			{
				if (current.right != null) {
					current = current.right;
				} else {
					link(current, new Node(data));
					++size;
					return true;
				}
			}
		}
	}

	// ------------------
	// Splay tree methods
	// ------------------

	/**
	 * Inserts an element into the splay tree. In case the element was not
	 * contained, this creates a new node and splays the tree at the new node. If
	 * the element exists in the tree already, it splays at the node containing the
	 * element.
	 * 
	 * Calls link().
	 * 
	 * @param data element to be inserted
	 * @return true if addition takes place false otherwise (i.e., data is in the
	 *         tree already)
	 */
	@Override
	public boolean add(E data) {

		System.out.println("Adding: " + data);

		Node newN = new Node(data);

		if (root == null) // empty tree
		{
			setRoot(newN);
			size++;
			return true;
		}

		Node nodeOrLast = findEntryOrLast(data);

		if (nodeOrLast.data.compareTo(data) != 0) // data does NOT exist in tree
		{
			link(nodeOrLast, newN);
			splay(newN);
			size++;
			return true;
		} else // data exists in tree
		{
			splay(nodeOrLast);
			return false;
		}

	}

	/**
	 * Determines whether the tree contains an element. Splays at the node that
	 * stores the element. If the element is not found, splays at the last node on
	 * the search path.
	 * 
	 * @param data element to be determined whether to exist in the tree
	 * @return true if the element is contained in the tree false otherwise
	 */
	public boolean contains(E data) {

		Node nodeOrLast = findEntryOrLast(data);

		if (nodeOrLast == null)// empty tree
			return false;

		splay(nodeOrLast);

		if (nodeOrLast.data.compareTo(data) == 0)
			return true;
		else
			return false;

	}

	/**
	 * Finds the node that stores the data and splays at it.
	 *
	 * @param data
	 */
	public void splay(E data) {
		Node toSplay = findEntry(data);

		splay(toSplay);
	}

	/**
	 * HELPER - sets root for code readability
	 * 
	 * @param n
	 */
	private void setRoot(Node n) {
		// FOR DEBUGGING
		// System.out.println("Setting Root from: " + getRoot() + "\nTo: " +
		// n.toString());
		root = n;
		// System.out.println(" Finished! New root is now: " + getRoot() + "\n\n");
	}

	/**
	 * Removes the node that stores an element. Splays at its parent node after
	 * removal (No splay if the removed node was the root.) If the node was not
	 * found, the last node encountered on the search path is splayed to the root.
	 * 
	 * Calls unlink().
	 * 
	 * @param data element to be removed from the tree
	 * @return true if the object is removed false if it was not contained in the
	 *         tree
	 */
	public boolean remove(E data) {
		Node toRemove = findEntryOrLast(data);
		if (toRemove.data.compareTo(data) == 0) {
			unlink(toRemove);
			if (toRemove != root)
				splay(toRemove.parent);

			return true;
		} else // if "toRemove" isn't the data,, it is the last interacted element
		{
			if (toRemove != null) // only false if tree is empty or just the root and search element is wrong
				splay(toRemove);
			return false;
		}

	}

	/**
	 * This method finds an element stored in the splay tree that is equal to data
	 * as decided by the compareTo() method of the class E. This is useful for
	 * retrieving the value of a pair <key, value> stored at some node knowing the
	 * key, via a call with a pair <key, ?> where ? can be any object of E.
	 * 
	 * Calls findEntry(). Splays at the node containing the element or the last node
	 * on the search path.
	 * 
	 * @param data
	 * @return element such that element.compareTo(data) == 0
	 */
	public E findElement(E data) {
		Node check = findEntry(data);

		if (check != null)
			return check.data;
		else
			return null;
	}

	/**
	 * Finds the node that stores an element. It is called by methods such as
	 * contains(), add(), remove(), and findElement().
	 * 
	 * No splay at the found node.
	 *
	 * @param data element to be searched for
	 * @return node if found or the last node on the search path otherwise null if
	 *         size == 0.
	 */
	public Node findEntry(E data) {

		Node found = findEntryOrLast(data);

		// if found is null or not the desired node
		if (found == null || found.data.compareTo(data) != 0)
			return null;
		else // is the desired node
			return found;
	}

	/**
	 * HELPER - returns entry if found, else the last interacted element during
	 * search
	 * 
	 * @param data
	 * @return entry or last
	 */
	private Node findEntryOrLast(E data) {
		Node last = null;
		Node current = root;
		while (current != null) {
			last = current;
			int comp = current.data.compareTo(data);
			if (comp == 0) {
				return current;
			} else if (comp > 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return last;
	}

	/**
	 * Removes a node n by replacing the subtree rooted at n with the join of the
	 * node's two subtrees.
	 * 
	 * Called by remove().
	 * 
	 * @param n
	 */
	private void unlink(Node n) {
		Node replacement = join(n.left, n.right);

		if (n == root)
			setRoot(replacement);

		if (replacement != null)
			link(n.parent, replacement);
		else {
			if (n.parent.left == n)
				n.parent.left = null;
			else
				n.parent.right = null;
		}

		--size;
	}

	/**
	 * Join the two subtrees T1 and T2 rooted at root1 and root2 into one. It is
	 * called by remove().
	 * 
	 * Precondition: All elements in T1 are less than those in T2.
	 * 
	 * Access the largest element in T1, and splay at the node to make it the root
	 * of T1. Make T2 the right subtree of T1. The method is called by remove().
	 * 
	 * @param root1 root of the subtree T1
	 * @param root2 root of the subtree T2
	 * @return the root of the joined subtree
	 */
	public Node join(Node root1, Node root2) {

		Node joinedRoot = null;

		if (root1 != null) {
			// access largest element in T1
			Node leftMax = findMaxNode(root1);
			// splay to make it the root
			splayToGivenRoot(leftMax, root1);

			// make t2 the right subtree if it exists
			if (root2 != null)
				link(leftMax, root2);
			else
				joinedRoot = leftMax;
		} else // if left node is null,, return root2
			joinedRoot = root2;

		// root of subtree
		return joinedRoot;
	}

	/**
	 * Splay at the current node. This consists of a sequence of zig, zigZig, or
	 * zigZag operations until the current node is moved to the root of the tree.
	 * 
	 * @param current node to splay
	 */
	protected void splay(Node current) {

		splayToGivenRoot(current, root);

	}

	/**
	 * splays item until item is in the place of the givenRoot
	 * 
	 * @param current   starting node
	 * @param givenRoot where starting node will end
	 */
	private void splayToGivenRoot(Node current, Node givenRoot) {
		if (current == givenRoot)
			return;

		Node rootParent = givenRoot.parent;

		// Splaying operation. It moves current to the root of the tree
		while (current.parent != rootParent) {
			if (current.parent.parent == rootParent) {
				zig(current);
			} else if (current == current.parent.left && current.parent == current.parent.parent.left
					|| current == current.parent.right && current.parent == current.parent.parent.right) {
				zigZig(current);
			} else {
				zigZag(current);
			}
		}
	}

	/**
	 * This method performs the zig operation on a node. Calls leftRotate() or
	 * rightRotate().
	 * 
	 * @param current node to perform the zig operation on
	 */
	protected void zig(Node current) {
		// assumed current is not the root

		// if current is the left child of parent
		// rotate right
		if (current == current.parent.left)
			rightRotate(current);
		// if curr is the right
		// rotate left
		else
			leftRotate(current);
	}

	/**
	 * This method performs the zig-zig operation on a node. Calls leftRotate() or
	 * rightRotate().
	 * 
	 * @param current node to perform the zig-zig operation on
	 */
	protected void zigZig(Node current) {
		// assumed current is not the root

		// if current is left child of parent,, assumed the same for parent
		if (current == current.parent.left) {
			rightRotate(current.parent);
			rightRotate(current);
		} else {
			leftRotate(current.parent);
			leftRotate(current);
		}
	}

	/**
	 * This method performs the zig-zag operation on a node. Calls leftRotate() and
	 * rightRotate().
	 * 
	 * @param current node to perform the zig-zag operation on
	 */
	protected void zigZag(Node current) {
		// assumed current is not the root

		// if current is left child of parent,, assumed the same for parent
		if (current == current.parent.left) {
			rightRotate(current);
			leftRotate(current);
		} else {
			leftRotate(current);
			rightRotate(current);
		}
	}

	/**
	 * Carries out a left rotation at a node such that after the rotation its former
	 * parent becomes its left child.
	 * 
	 * Calls link().
	 * 
	 * @param current
	 */
	public void leftRotate(Node current) {

		Node prevLeft = current.left;
		Node prevParent = current.parent;

		if (prevParent.parent != null) {
			Node prevGrandParent = prevParent.parent;

			if (prevParent == prevGrandParent.left)
				prevGrandParent.left = current;
			else if (prevParent == prevGrandParent.right)
				prevGrandParent.right = current;

			current.parent = prevGrandParent;
		} else
			current.parent = null;

		// FOR DEBUGGING
		// System.out.println(this.toString() + "right before");

		if (prevParent == root) {
			setRoot(current);
		}

		// FOR DEBUGGING
		// System.out.println(this.toString() + "right after");

		if (prevParent.right != current)
			throw new IllegalStateException("trying to left rotate from incorrect start position");

		// prev parent becomes current.left
		link(current, prevParent);
		// prev left becomes prevParent.right
		if (prevLeft != null)
			link(prevParent, prevLeft);
		else
			prevParent.right = null;

	}

	/**
	 * Carries out a right rotation at a node such that after the rotation its
	 * former parent becomes its right child.
	 * 
	 * Calls link().
	 * 
	 * @param current
	 */
	public void rightRotate(Node current) {

		Node prevRight = current.right;
		Node prevParent = current.parent;
		if (prevParent.parent != null) {
			Node prevGrandParent = prevParent.parent;

			if (prevParent == prevGrandParent.left)
				prevGrandParent.left = current;
			if (prevParent == prevGrandParent.right)
				prevGrandParent.right = current;

			current.parent = prevGrandParent;
		} else
			current.parent = null;

		// FOR DEBUGGING
		// System.out.println(this.toString() + "left before");

		if (prevParent == root) {
			setRoot(current);
		}

		// FOR DEBUGGING
		// System.out.println(this.toString() + "left after");

		if (prevParent.left != current)
			throw new IllegalStateException("trying to right rotate from incorrect start position");

		// prev parent becomes current.right
		link(current, prevParent);
		// prev right becomes prevParent.left
		if (prevRight != null)
			link(prevParent, prevRight);
		else
			prevParent.left = null;

	}

	/**
	 * Establish the parent-child relationship between two nodes.
	 * 
	 * Called by addBST(), add(), leftRotate(), and rightRotate().
	 * 
	 * @param parent
	 * @param child
	 */
	private void link(Node parent, Node child) {

		child.parent = parent;

		if (child.data.compareTo(parent.data) < 0) {
			parent.left = child;
		} else if (child.data.compareTo(parent.data) > 0) {
			parent.right = child;
		} else {
			throw new Error("child and parent are same value when linking");
		}

	}

	/**
	 * Perform BST removal of a node.
	 * 
	 * Called by the iterator method remove().
	 * 
	 * @param n
	 */
	public void unlinkBST(Node n) {

		if (n.left != null && n.right != null) {
			Node succ = successor(n);
			n.data = succ.data;
			n = succ;
		}

		Node replacement = null;
		if (n.left != null) {
			replacement = n.left;
		} else if (n.right != null) {
			replacement = n.right;
		}

		if (n == root)
			root = replacement;
		else {
			if (n.parent.left == n)
				n.parent.left = replacement;
			else
				n.parent.right = replacement;
		}

		if (replacement != null)
			replacement.parent = n.parent;

		--size;
	}

	/**
	 * Called by unlink() and the iterator method next().
	 * 
	 * @param n
	 * @return successor of n
	 */
	public Node successor(Node n) {

		// finds next node in order

		Node succ = null;

		if (n == null)
			return null;

		// Start from root and search for
		// successor down the tree
		if (n.right != null) {
			succ = findMinNode(n.right);
		} else {
			Node curr = root;

			while (curr != null) {
				int comp = n.data.compareTo(curr.data);

				if (comp < 0) {
					succ = curr;
					curr = curr.left;
				} else if (comp > 0) {
					curr = curr.right;
				} else
					break;
			}
		}

		return succ;
	}

	/**
	 * finds least node in subtree
	 * 
	 * @param n root of subtree
	 * @return least
	 */
	private Node findMinNode(Node n) {
		Node curr = n;

		while (curr.left != null) {
			curr = curr.left;
		}

		return curr;
	}

	/**
	 * find max node in subtree
	 * 
	 * @param n root of subtree
	 * @return max
	 */
	private Node findMaxNode(Node n) {
		Node curr = n;

		while (curr.right != null) {
			curr = curr.right;
		}

		return curr;
	}

	@Override
	public Iterator<E> iterator() {
		return new SplayTreeIterator();
	}

	/**
	 * Write the splay tree according to the format specified in Section 2.2 of the
	 * project description.
	 * 
	 * Calls toStringRec().
	 *
	 */
	@Override
	public String toString() {

		if (root == null)
			return "null\n";

		String out = toStringRec(root, 0);

		int sSize = out.length();

		out = out.substring(0, sSize - 1);

		return out;
	}

	/**
	 * recursive to string funct
	 * 
	 * @param n     node
	 * @param depth
	 * @return
	 */
	private String toStringRec(Node n, int depth) {

		String s = "";

		s += printElem(n, depth);

		if (n.left != null) {
			// Traverse the left subtree
			s += toStringRec(n.left, depth + 1);
		}

		if (n.right == null && n.left != null) {
			s += depthToString(depth + 1) + "null" + "\n";
		} else if (n.right != null && n.left == null) {
			s += depthToString(depth + 1) + "null" + "\n";
		}

		if (n.right != null) {
			// Traverse the right subtree
			s += toStringRec(n.right, depth + 1);
		}

		return s;
	}

	/**
	 * HELPER - turns depth to string of spaces
	 * 
	 * @param depth
	 * @return spaces
	 */
	private String depthToString(int depth) {
		String s = "";
		for (int i = 0; i < depth; i++) {
			s += "    ";
		}

		return s;
	}

	/**
	 * HELPER - prints the string line of the node with its depth spacing
	 * 
	 * @param n     node
	 * @param depth
	 * @return formated node string
	 */
	private String printElem(Node n, int depth) {
		return depthToString(depth) + n.data + "\n";
	}

	/**
	 *
	 * Iterator implementation for this splay tree. The elements are returned in
	 * ascending order according to their natural ordering. The methods hasNext()
	 * and next() are exactly the same as those for a binary search tree --- no
	 * splaying at any node as the cursor moves. The method remove() method should
	 * not splay.
	 */
	private class SplayTreeIterator implements Iterator<E> {
		Node cursor;
		Node pending;

		public SplayTreeIterator() {
			cursor = null;
			pending = findMinNode(root);
		}

		@Override
		public boolean hasNext() {
			return pending != null;
		}

		@Override
		public E next() {
			cursor = pending;
			pending = successor(cursor);
			return cursor.data;
		}

		/**
		 * This method will join the left and right subtrees of the node being removed,
		 * but will not perform a splay operation.
		 * 
		 * Calls unlinkBST().
		 * 
		 */
		@Override
		public void remove() {

			next();
			unlinkBST(cursor);

		}
	}
}
