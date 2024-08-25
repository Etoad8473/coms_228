package edu.iastate.cs228.hw3;

import java.util.AbstractSequentialList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Implementation of the list interface based on linked nodes that store
 * multiple items per node. Rules for adding and removing elements ensure that
 * each node (except possibly the last one) is at least half full.
 * @author Ezra Odole
 */
public class MultiList<E extends Comparable<? super E>> extends AbstractSequentialList<E> {
	/**
	 * Default number of elements that may be stored in each node.
	 */
	private static final int DEFAULT_NODESIZE = 4;

	/**
	 * Number of elements that can be stored in each node.
	 */
	private final int nodeSize;

	/**
	 * Dummy node for head. It should be private but set to public here only for
	 * grading purpose. In practice, you should always make the head of a linked
	 * list a private instance variable.
	 */
	public Node head;

	/**
	 * Dummy node for tail.
	 */
	private Node tail;

	/**
	 * Number of elements in the list.
	 */
	private int size;

	/**
	 * Constructs an empty list with the default node size.
	 */
	public MultiList() {
		this(DEFAULT_NODESIZE);
	}

	/**
	 * Constructs an empty list with the given node size.
	 * 
	 * @param nodeSize number of elements that may be stored in each node, must be
	 *                 an even number
	 */
	public MultiList(int nodeSize) {
		if (nodeSize <= 0 || nodeSize % 2 != 0)
			throw new IllegalArgumentException();

		// dummy nodes
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.previous = head;
		this.nodeSize = nodeSize;
	}

	/**
	 * Constructor for grading only. Fully implemented.
	 * 
	 * @param head
	 * @param tail
	 * @param nodeSize
	 * @param size
	 */
	public MultiList(Node head, Node tail, int nodeSize, int size) {
		this.head = head;
		this.tail = tail;
		this.nodeSize = nodeSize;
		this.size = size;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean add(E item) {
		boolean success = true;

		if (item == null)
			throw new NullPointerException();

		Node lastNode = this.tail.previous;
		if (lastNode.count < nodeSize && lastNode != this.head) {
			// add node if there is space
			lastNode.addItem(item);
		} else {
			// create new node and add
			Node after = newNodeAtEnd();
			after.addItem(item);
		}

		size++;

		return success;
	}

	/**
	 * HELPER: creates an empty node at the end, to add a new element to
	 * technically redundant because of newNodeAfter but helpful for understanding other code
	 * 
	 * @return returns the new end node
	 */
	private Node newNodeAtEnd() {
		return newNodeAfter(this.tail.previous);
	}

	/**
	 * HELPER: creates an empty node after n, to add a new element to
	 * 
	 * @param n node to add new node after
	 * @return returns the new node
	 */
	private Node newNodeAfter(Node n) {
		Node after = new Node();
		Node priorNextNode = n.next;

		n.next = after;
		after.previous = n;
		after.next = priorNextNode;
		priorNextNode.previous = after;

		return after;
	}

	@Override
	public void add(int pos, E item) {
		if (item == null)
			throw new NullPointerException();

		IndexInfo posInfo = findNodeNOffset(pos);
		int off = posInfo.offset;
		Node n = posInfo.node;
		Node pred = n.previous;

		if (size == 0)
			add(item);
		else if (off == 0 && pred != head && pred.count < nodeSize) {
			pred.addItem(item);
			size++;
		} else if (off == 0 && n == tail && pred.count == nodeSize)
			add(item);
		else if (n.count < nodeSize) {
			n.addItem(off, item);
			size++;
		} else {
			// System.out.println(this.toStringInternal());
			int middle = nodeSize / 2;
			split(n);
			// System.out.println(this.toStringInternal());
			if (off > middle) {
				off -= middle;
				n = n.next;
			}
			n.addItem(off, item);
			// System.out.println(this.toStringInternal());
			size++;
		}

	}

	/**
	 * HELPER: splits based on documentation rules
	 * the split method pre-req is that it has to be splitting a full node
	 * 
	 * @param n node to split
	 */
	private void split(Node n) {
		if (n.count != nodeSize)
			throw new IndexOutOfBoundsException("tryina split a not-full node");

		Node newNode = newNodeAfter(n);
		int middle = nodeSize / 2;

		for (int i = middle; i < nodeSize; i++) {
			newNode.addItem(n.data[i]);
			n.data[i] = null;
			n.count--;
		}
	}

	/**
	 * HELPER: finds the node and offset of a given index
	 * 
	 * @param indexPos index to find
	 * @return an IndexInfo object containing the correct node and offset
	 */
	private IndexInfo findNodeNOffset(int indexPos) {
		if (indexPos > size)
			throw new IndexOutOfBoundsException("trying to find a position out of the multilist");

		Node n = head.next;// first node
		int indexSearch = 0;// essentially the cursors index while searching

		// increment the cursor past the indexPos
		while (indexSearch < indexPos) {
			indexSearch += n.count;
			n = n.next;
		}

		// go back to the previous node if not the last position (tail node, offset of
		// 0)
		if (indexSearch != indexPos) {
			n = n.previous;
			indexSearch -= n.count;
		}

		// get the offset
		int finalOffset = indexPos - indexSearch;

		// return IndexInfo
		return new IndexInfo(n, finalOffset);
	}

	@Override
	public E remove(int pos) {

		IndexInfo info = findNodeNOffset(pos);
		Node n = info.node;
		int off = info.offset;

		E out = n.data[off];
		Node successor = n.next;

		if (n == tail.previous && n.count == 1)// remove
			removeNode(n);
		else if (n == tail.previous || n.count > nodeSize / 2)
			n.removeItem(off);
		else if (successor.count > nodeSize / 2)// small merge
		{
			n.removeItem(off);
			E firstE = successor.data[0];// copy first item from successor
			successor.removeItem(0);// delete (and shift) from successor
			n.addItem(firstE);// paste into last position in n
		} else if (successor.count <= nodeSize / 2)// full merge
		{
			n.removeItem(off);

			for (int i = 0; i < successor.count; i++) {
				n.addItem(successor.data[0]);// add first element of successor to n
				successor.removeItem(0);// remove first element of successor
			}
			removeNode(successor);
		}

		size--;

		return out;
	}

	/**
	 * HELPER: removes node
	 * @param n node to remove
	 */
	private void removeNode(Node n) {
		Node previous = n.previous;
		Node next = n.next;

		previous.next = next;
		next.previous = previous;
	}

	/**
	 * Sort all elements in the Multi list in NON-DECREASING order. You may do the
	 * following. Traverse the list and copy its elements into an array, deleting
	 * every visited node along the way. Then, sort the array by calling the
	 * insertionSort() method. (Note that sorting efficiency is not a concern for
	 * this project.) Finally, copy all elements from the array back to the Multi
	 * list, creating new nodes for storage. After sorting, all nodes but (possibly)
	 * the last one must be full of elements.
	 * 
	 * Comparator<E> must have been implemented for calling insertionSort().
	 */
	public void sort() {

		E[] toSort = thisToArray();
		insertionSort(toSort, new Comparator<E>());
		buildFromArray(toSort);

	}

	/**
	 * HELPER: turns this multilist into array of E
	 * 
	 * @return the array version of this list
	 */
	private E[] thisToArray() {
		E[] arr = (E[]) new Comparable[size];
		ListIterator<E> iter = listIterator();
		int i = 0;
		while (iter.hasNext()) {
			arr[i] = iter.next();
			i++;
		}

		return arr;
	}

	/**
	 * HELPER: recreates this multilist from an array
	 * @param arr array to build from
	 */
	private void buildFromArray(E[] arr) {
		// while n.next != tail, remove n node
		Node n = head.next;
		while (n != tail) {
			Node next = n.next;
			removeNode(n);
			n = next;
		}

		// for i .add to multilist
		for (int i = 0; i < arr.length; i++) {
			add(arr[i]);
		}

	}

	/**
	 * Sort all elements in the Multi list in NON-INCREASING order. Call the
	 * bubbleSort() method. After sorting, all but (possibly) the last nodes must be
	 * filled with elements.
	 * 
	 * Comparable<? super E> must be implemented for calling bubbleSort().
	 */
	public void sortReverse() {
		E[] toSort = thisToArray();
		bubbleSort(toSort);
		buildFromArray(toSort);
	}

	@Override
	public Iterator<E> iterator() {
		return new MultiListIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new MultiListIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return new MultiListIterator(index);
	}

	/**
	 * Returns a string representation of this list showing the internal structure
	 * of the nodes.
	 */
	public String toStringInternal() {
		return toStringInternal(null);
	}

	/**
	 * Returns a string representation of this list showing the internal structure
	 * of the nodes and the position of the iterator.
	 *
	 * @param iter an iterator for this list
	 */
	public String toStringInternal(ListIterator<E> iter) {
		int count = 0;
		int position = -1;
		if (iter != null) {
			position = iter.nextIndex();
		}

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		Node current = head.next;
		while (current != tail) {
			sb.append('(');
			E data = current.data[0];
			if (data == null) {
				sb.append("-");
			} else {
				if (position == count) {
					sb.append("| ");
					position = -1;
				}
				sb.append(data.toString());
				++count;
			}

			for (int i = 1; i < nodeSize; ++i) {
				sb.append(", ");
				data = current.data[i];
				if (data == null) {
					sb.append("-");
				} else {
					if (position == count) {
						sb.append("| ");
						position = -1;
					}
					sb.append(data.toString());
					++count;

					// iterator at end
					if (position == size && count == size) {
						sb.append(" |");
						position = -1;
					}
				}
			}
			sb.append(')');
			current = current.next;
			if (current != tail)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Node type for this list. Each node holds a maximum of nodeSize elements in an
	 * array. Empty slots are null.
	 */
	private class Node {
		/**
		 * Array of actual data elements.
		 */
		// Unchecked warning unavoidable.
		public E[] data = (E[]) new Comparable[nodeSize];

		/**
		 * Link to next node.
		 */
		public Node next;

		/**
		 * Link to previous node;
		 */
		public Node previous;

		/**
		 * Index of the next available offset in this node, also equal to the number of
		 * elements in this node.
		 */
		public int count;

		/**
		 * Adds an item to this node at the first available offset. Precondition: count
		 * < nodeSize
		 * 
		 * @param item element to be added
		 */
		void addItem(E item) {
			if (count >= nodeSize) {
				return;
			}
			data[count++] = item;
			// useful for debugging
			// System.out.println("Added " + item.toString() + " at index " + count + " to
			// node " + Arrays.toString(data));
		}

		/**
		 * Adds an item to this node at the indicated offset, shifting elements to the
		 * right as necessary.
		 * 
		 * Precondition: count < nodeSize
		 * 
		 * @param offset array index at which to put the new element
		 * @param item   element to be added
		 */
		void addItem(int offset, E item) {
			if (count >= nodeSize) {
				return;
			}
			for (int i = count - 1; i >= offset; --i) {
				data[i + 1] = data[i];
			}
			++count;
			data[offset] = item;
			// useful for debugging
			// System.out.println("Added " + item.toString() + " at index " + offset + " to
			// node: " + Arrays.toString(data));
		}

		/**
		 * Deletes an element from this node at the indicated offset, shifting elements
		 * left as necessary. Precondition: 0 <= offset < count
		 * 
		 * @param offset
		 */
		void removeItem(int offset) {
			E item = data[offset];
			for (int i = offset + 1; i < nodeSize; ++i) {
				data[i - 1] = data[i];
			}
			data[count - 1] = null;
			--count;
		}
	}

	private class MultiListIterator implements ListIterator<E> {

		/**
		 * the logical index keeper
		 */
		int cursor;
		/**
		 * current node of the cursor
		 */
		Node currNode;
		/**
		 * offset in the node of the cursor
		 */
		int offset;
		/**
		 * previous direction moved (from next/previous)
		 * 1 if next was called
		 * -1 if previous was called
		 * 0 if no direction (add/set)
		 */
		int previousDir;

		/**
		 * Default constructor
		 */
		public MultiListIterator() {
			this(0);
		}

		/**
		 * Constructor finds node at a given position.
		 * 
		 * @param pos
		 */
		public MultiListIterator(int pos) {
			cursorToIndex(pos);
			previousDir = 0;
		}

		@Override
		public boolean hasNext() {
			return cursor < size;
		}

		@Override
		public E next() {
			E out = currNode.data[offset];

			incrementCursor();

			previousDir = 1;

			return out;
		}

		@Override
		public void remove() {
			if (previousDir == 0)
				throw new IllegalStateException("no direction to delete");

			// if previously did previous, remove that elements index
			if (previousDir == -1) {
				MultiList.this.remove(cursor);
				cursorToIndex(cursor);// updates to make sure correctly moved after possible merge
			}
			// if previously did next, remove that elements index
			else if (previousDir == 1) {
				MultiList.this.remove(cursor - 1);
				cursorToIndex(cursor - 1);
			}

			previousDir = 0;
		}

		/**
		 * HELPER: moves cursor to next position and subsequently correctly updates currentNode
		 * and offset
		 */
		private void incrementCursor() {
			cursor++;
			offset++;

			if (offset >= currNode.count) {
				currNode = currNode.next;
				offset = 0;
			}
		}

		/**
		 * HELPER: moves cursor, offset, and currNode to correct logical index
		 * @param i index
		 */
		private void cursorToIndex(int i) {
			if (size == 0)
				throw new NoSuchElementException("trying move the cursor in an empty multilist");

			// get node of index and retain offset
			IndexInfo cursorInfo = findNodeNOffset(i);

			cursor = i;
			currNode = cursorInfo.node;
			offset = cursorInfo.offset;
		}

		@Override
		public boolean hasPrevious() {

			return cursor > 0;
		}

		@Override
		public E previous() {
			decrementCursor();

			E out = currNode.data[offset];

			previousDir = -1;

			return out;
		}

		/**
		 * HELPER: moves cursor to previous position and
		 * correctly updates currentNode and offset
		 */
		private void decrementCursor() {
			cursor--;
			offset--;

			if (offset < 0) {
				currNode = currNode.previous;
				offset = currNode.count - 1;
			}
		}

		@Override
		public int nextIndex() {
			return cursor;
		}

		@Override
		public int previousIndex() {
			return cursor - 1;
		}

		@Override
		public void set(E e) {

			// throws if previousDir == 0
			if (previousDir == 0)
				throw new IllegalStateException("setting with no direction");

			// references currNode and offset without disrupting them
			Node setNode = currNode;
			int priorOffset = offset - previousDir;

			// if priorOffset was from prevous node, fix node and offset references
			if (priorOffset < 0) {
				setNode = setNode.previous;
				priorOffset = setNode.count - 1;
			}
			// if priorOffst was from next node, fix node and offset references
			else if (priorOffset >= setNode.count) {
				setNode = setNode.next;
				priorOffset = 0;
			}
			// set prior reference to e
			setNode.data[priorOffset] = e;

			previousDir = 0;
		}

		@Override
		public void add(E e) {

			MultiList.this.add(cursor, e);

			cursorToIndex(cursor + 1);

			previousDir = 0;

		}

	}

	/**
	 * HELPER CLASS: a class to contain the currNode and Offset of a given cursor position
	 * 
	 * @author Ezra8
	 *
	 */
	private class IndexInfo {
		public Node node;
		public int offset;

		/**
		 * constructor
		 */
		public IndexInfo(Node node, int offset) {
			this.node = node;
			this.offset = offset;
		}
	}

	/**
	 * Comparator class for insertion sorts comparator
	 */
	private class Comparator<E extends Comparable<? super E>> {
		public int compare(E a, E b) {
			return ((Comparable<? super E>) a).compareTo(b);
		}
	}

	/**
	 * Sort an array arr[] using the insertion sort algorithm in the NON-DECREASING
	 * order.
	 * 
	 * @param arr  array storing elements from the list
	 * @param comp comparator used in sorting
	 */
	private void insertionSort(E[] arr, Comparator<? super E> comp) {

		// for length of array (starting at index 1)
		for (int i = 1; i < arr.length; i++) {
			// value to check against [i], before it
			int j = i - 1;
			// hold value at i in temp
			E temp = arr[i];

			// if j isnt negative & value before i is larger than i
			while (j >= 0 && comp.compare(arr[j], temp) > 0) {
				// move large value onto i
				arr[j + 1] = arr[j];
				// repeat for each previous value
				j--;
			}

			// insert original value at i into position after j (which stops at a value
			// smaller than [i])
			arr[j + 1] = temp;
		}

	}

	/**
	 * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order. For a
	 * description of bubble sort please refer to Section 6.1 in the project
	 * description. You must use the compareTo() method from an implementation of
	 * the Comparable interface by the class E or ? super E.
	 * 
	 * @param arr array holding elements from the list
	 */
	private void bubbleSort(E[] arr) {
		// loop to access each array element
		// loop to compare array elements
		// (if) compare two adjacent elements
		// swap

		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - i - 1; j++) {
				if (arr[j].compareTo(arr[j + 1]) < 0) {
					E temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

}