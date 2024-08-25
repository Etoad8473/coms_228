package tests;

import edu.iastate.cs228.hw4.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.*;
import org.junit.jupiter.api.Test;

public class TESTS {
	
	SplayTree<Integer> st;

	public void initFullCorrect() {
		st = new SplayTree<Integer>(10);
		
		/*
		 * <pre>
		 * 
		 * 		       10
		 *           /     \ 
		 * 	     6             14
		 * 	  /    \         /     \	
		 * 	 4      8      12       16
		 *  / \    / \    /  \     /  \
		 * 3   5  7   9  11   13  15   17 
		 * 
		 * </pre>
		 * 
		 */
        
        
		st.addBST(6);
		st.addBST(14);
		st.addBST(4);
		st.addBST(8);
		st.addBST(12);
		st.addBST(16);
		st.addBST(3);
		st.addBST(5);
		st.addBST(7);
		st.addBST(9);
		st.addBST(11);
		st.addBST(13);
		st.addBST(15);
		st.addBST(17);
	}
	
	@Test
	public void toStringTest1() {

		initFullCorrect();

		String expected = "10\n    6\n        4\n            3\n            5\n        8\n            7\n            9\n    14\n        12\n            11\n            13\n        16\n            15\n            17";
		String actual = st.toString();

		assertEquals(expected, actual);

	}
	
	public void init2() {
		st = new SplayTree(10);
		st.addBST(7);
		st.addBST(24);
		st.addBST(7);
		st.addBST(4);
		st.addBST(3);
		st.addBST(5);
		/*
		 <pre>
		  
		      10
		     /  \
		    7    24
		   /
		  4
		 / \
		3   5
		
		</pre>
		
		 */
		
	}
	
	@Test
	public void toStringTestInit2() {

		init2();

		String expected = "10\n    7\n        4\n            3\n            5\n        null\n    24";
		String actual = st.toString();

		assertEquals(expected, actual);

	}
	
	@Test
	public void successorTest() {

		initFullCorrect();

		String expected = "11";
		String actual = st.successor(st.findEntry(10)).toString();

		assertEquals(expected, actual);

		
		expected = "7";
		actual = st.successor(st.findEntry(6)).toString();

		assertEquals(expected, actual);

		
		expected = "9";
		actual = st.successor(st.findEntry(8)).toString();

		assertEquals(expected, actual);

		expected = "6";
		actual = st.successor(st.findEntry(5)).toString();

		assertEquals(expected, actual);

		expected = "10";
		actual = st.successor(st.findEntry(9)).toString();

		assertEquals(expected, actual);
		

	}
	
	
	@Test
	public void leftRotateTest1() {
		st = new SplayTree<Integer>(10);
		st.addBST(11);
		st.addBST(9);

		st.leftRotate(st.findEntry(11));

		String expected = "11\n    10\n        9\n        null\n    null";
		String actual = st.toString();

		assertEquals(expected, actual, "Post rotation test failed");
		
		assertNull(st.findEntry(11).parent);
		
		assertEquals(st.findEntry(11), st.findEntry(10).parent);
		
		assertEquals(st.findEntry(10),st.findEntry(9).parent);
		
		assertEquals(st.findEntry(11), st.root);
		assertNull(st.findEntry(11).right);
		assertNull(st.findEntry(10).right);
		assertNull(st.findEntry(9).right);
		assertNull(st.findEntry(9).left);
		
	}
	
	@Test
	public void rightRotateTest1() {
		
		st = new SplayTree<Integer>(10);
		st.addBST(11);
		st.addBST(9);
		
		st.rightRotate(st.findEntry(9));

		String expected = "9\n    null\n    10\n        null\n        11";
		String actual = st.toString();

		assertEquals(expected, actual, "Post rotation test failed");
		
		assertNull(st.findEntry(9).parent);
		assertEquals(st.findEntry(9), st.findEntry(10).parent);
		assertEquals(st.findEntry(10), st.findEntry(11).parent);
		assertEquals(st.findEntry(9), st.root);
		assertNull(st.findEntry(9).left);
		assertNull(st.findEntry(10).left);
		assertNull(st.findEntry(11).right);
		assertNull(st.findEntry(11).left);
		
	}
	
	@Test
	public void testToStringWithRightLeaningTree() {
		SplayTree<Integer> st2 = new SplayTree<Integer>(9);
		st2.addBST(10);
		st2.addBST(11);

		String expected = "9\n    null\n    10\n        null\n        11";
		String actual = st2.toString();

		assertEquals(expected, actual, "Post rotation test failed");
		
		
	}
	
	@Test
	public void rightRotateTestFromSplayTree() {
		
		st = new SplayTree<Integer>(10);
		st.addBST(11);
		st.addBST(9);
		
		st.rightRotate(st.findEntry(9));

		String expected = "9\n    null\n    10\n        null\n        11";
		String actual = st.toString();

		assertEquals(expected, actual, "Post rotation test failed");
		
		assertNull(st.findEntry(9).parent);
		assertEquals(st.findEntry(9), st.findEntry(10).parent);
		assertEquals(st.findEntry(10), st.findEntry(11).parent);
		assertEquals(st.findEntry(9), st.root);
		assertNull(st.findEntry(9).left);
		assertNull(st.findEntry(10).left);
		assertNull(st.findEntry(11).right);
		assertNull(st.findEntry(11).left);
		
		
	}
	
	
	public void init3() {
		st = new SplayTree(314);
		st.addBST(60);
		st.addBST(1729);
		st.addBST(2);
		st.addBST(65);
		st.addBST(64);
		st.addBST(169);
		
		/*
		 *     314
		 * 	  /   \
		 * 	 60   1729
		 *  /  \
		 * 2    65
		 *     /  \
		 *    64   169
		 */   
	}
	
	@Test
	public void splayZigZagTest1Init3() {
		init3();

		st.splay(65);

		String expected = "65\n    60\n        2\n        64\n    314\n        169\n        1729";
		String actual = st.toString();

		assertEquals(expected, actual);
	}
	
	@Test
	public void removeTest() {
		
		init2();
		
		String expected = "10\n    5\n        4\n            3\n            null\n        null\n    24";
		
		st.remove(7);
		
		String actual = st.toString();
		
		assertEquals(expected, actual);
	}
	
}
