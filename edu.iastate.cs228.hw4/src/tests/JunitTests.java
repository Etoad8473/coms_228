package tests;

import edu.iastate.cs228.hw4.*;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

// /**
//  * 
//  * @author Ben Przybyszewski
//  *
//  */
public class JunitTests {

	SplayTree<Integer> st;

	public void init1() {
		st = new SplayTree(10);
		
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
	
	public void init4() {
		st = new SplayTree();
		st.addBST(30);
		st.addBST(15);
		st.addBST(45);
	}
	
	public void rotateTest1Init() {
		st = new SplayTree(10);
		st.addBST(11);
		st.addBST(9);
	}
	
	public void rotateTest2Init() {
		st = new SplayTree(10);
		st.addBST(12);
		st.addBST(11);
		st.addBST(13);
		st.addBST(9);
	}
	
	public void rotateTest3Init(){
		st = new SplayTree(10);
		st.addBST(8);
		st.addBST(9);
		st.addBST(7);
		st.addBST(12);
		st.addBST(11);
		st.addBST(13);
	}
	
	@Test
	public void toStringTestInit1() {

		init1();

		String expected = "10\n    6\n        4\n            3\n            5\n        8\n            7\n            9\n    14\n        12\n            11\n            13\n        16\n            15\n            17";
		String actual = st.toString();

		assertEquals(expected, actual);

	}

	@Test
	public void toStringTestInit2() {

		init2();

		String expected = "10\n    7\n        4\n            3\n            5\n        null\n    24";
		String actual = st.toString();

		assertEquals(expected, actual);

	}

	@Test
	public void toStringTestInit3() {

		init3();

		String expected = "314\n    60\n        2\n        65\n            64\n            169\n    1729";
		String actual = st.toString();
		assertEquals(expected, actual);

	}
	
	@Test
	public void toStringTestInit4() {
		
		init4();
		
		String expected = "30\n    15\n    45";
		String actual = st.toString();
		assertEquals(expected, actual);
		
	}

	@Test
	public void successorTest() {

		init1();

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
	public void splayZigZigTest1Init2() {
		init2();

		st.splay(4);

		String expected = "4\n    3\n    7\n        5\n        10\n            null\n            24";
		String actual = st.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void splayZigTest1Init2() {
		init2();

		st.splay(7);

		String expected = "7\n    4\n        3\n        5\n    10\n        null\n        24";
		String actual = st.toString();

		assertEquals(expected, actual);
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
	public void addTest1Init3() {

		init3();

		//Not sure about this junk here
//		String expected = "66\n    65\n        60\n            2\n            64\n    314\n        169\n        1729";
		String expected = "66\n    60\n        2\n        65\n            64\n            null\n    314\n        169\n        1729";
		
		
		assertTrue(st.add(66));
		String actual = st.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void addTest1SeperateInit3() {

		init3();

		//Not sure about this junk here
//		String expected = "66\n    65\n        60\n            2\n            64\n    314\n        169\n        1729";
		String expected = "66\n    60\n        2\n        65\n            64\n            null\n    314\n        169\n        1729";
		
		assertTrue(st.addBST(66));
		st.splay(66);
		String actual = st.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void rotateTest1InitTest() {
		rotateTest1Init();

		String expected = "10\n    9\n    11";
		String actual = st.toString();

		assertEquals(expected, actual, "Initial Test failed");
	}

	@Test
	public void rightRotateTest1() {
		
		rotateTest1Init();
		
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
	public void leftRotateTest1() {
		rotateTest1Init();

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
	public void rotateTest2InitTest() {
		rotateTest2Init();
		
		

		String expected = "10\n    9\n    12\n        11\n        13";
		String actual = st.toString();

		assertEquals(expected, actual, "Initial Test failed");
	}

	@Test
	public void rightRotateTest2() {
		// With children
		rotateTest2Init();

		st.rightRotate(st.findEntry(9));

		String expected = "9\n    null\n    10\n        null\n        12\n            11\n            13";
		String actual = st.toString();

		assertEquals(expected, actual, "Post rotation test failed");
	}

	@Test
	public void leftRotateTest2() {
		// With children

		rotateTest2Init();

		st.leftRotate(st.findEntry(12));

		String expected = "12\n    10\n        9\n        11\n    13";
		String actual = st.toString();

		assertEquals(expected, actual, "Post rotation test failed");
	}
	
	@Test
	public void rightRotateTest3() {
		// Full BST
		rotateTest3Init();

		st.rightRotate(st.findEntry(8));

		String expected = "8\n    7\n    10\n        9\n        12\n            11\n            13";
		String actual = st.toString();

		assertEquals(expected, actual, "Post rotation test failed");
	}

	@Test
	public void leftRotateTest3() {
		// Full BST

		rotateTest3Init();

		st.leftRotate(st.findEntry(12));

		String expected = "12\n    10\n        8\n            7\n            9\n        11\n    13";
		String actual = st.toString();

		assertEquals(expected, actual, "Post rotation test failed");
	}
	
	@Test
	public void removeTest() {
		
		init2();
		
		String expected = "10\n    5\n        4\n            3\n            null\n        null\n    24";
		
		st.remove(7);
		
		String actual = st.toString();
		
		assertEquals(expected, actual);
	}

	@Test
	public void recursiveConstructorTest2() {
		init2();
		
		SplayTree<Integer> clone = new SplayTree(st);
		
		String expected = "10\n    7\n        4\n            3\n            5\n        null\n    24";
		String actual = clone.toString();

		assertEquals(expected, actual);
	}
	
	@Test
	public void recursiveConstructorTest1() {
		init1();
		
		SplayTree<Integer> clone = new SplayTree(st);
		
		String expected = "10\n    6\n        4\n            3\n            5\n        8\n            7\n            9\n    14\n        12\n            11\n            13\n        16\n            15\n            17";
		String actual = clone.toString();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void recursiveConstructorTest3() {
		init3();
		
		SplayTree<Integer> clone = new SplayTree(st);
		
		String expected = "314\n    60\n        2\n        65\n            64\n            169\n    1729";
		String actual = clone.toString();
		
		assertEquals(expected, actual);
	}

	@Test
	public void unlinkBSTTest1() {
		init1();
		
		st.unlinkBST(st.findEntry(3));
		
		String expected = "10\n    6\n        4\n            null\n            5\n        8\n            7\n            9\n    14\n        12\n            11\n            13\n        16\n            15\n            17";
		String actual = st.toString();
		assertEquals(expected, actual);		
	}
	
	@Test
	public void unlinkBSTTest2() {
		init1();
		
		st.unlinkBST(st.findEntry(3));
		st.unlinkBST(st.findEntry(4));
		
		String expected = "10\n    6\n        5\n        8\n            7\n            9\n    14\n        12\n            11\n            13\n        16\n            15\n            17";
		String actual = st.toString();
		assertEquals(expected, actual);		
	}
	@Test
	public void unlinkBSTTest3() {
		init1();
		
		st.unlinkBST(st.findEntry(3));
		st.unlinkBST(st.findEntry(4));
		st.unlinkBST(st.findEntry(6));

		String expected = "10\n"
				+ "    7\n"
				+ "        5\n"
				+ "        8\n"
				+ "            null\n"
				+ "            9\n"
				+ "    14\n"
				+ "        12\n"
				+ "            11\n"
				+ "            13\n"
				+ "        16\n"
				+ "            15\n"
				+ "            17";
		String actual = st.toString();
		assertEquals(expected, actual);		
	}
	
	@Test
	public void itertatorTest1() {
		init1();
		
		Iterator<Integer> it = st.iterator();
		
		assertTrue(it.hasNext());
		assertEquals(3, it.next());
		
		assertTrue(it.hasNext());
		assertEquals(4, it.next());
		
		assertTrue(it.hasNext());
		assertEquals(5, it.next());
		
		assertTrue(it.hasNext());
		assertEquals(6, it.next());
		
		assertTrue(it.hasNext());
		assertEquals(7, it.next());
		
		assertTrue(it.hasNext());
		assertEquals(8, it.next());
		
		assertTrue(it.hasNext());
		assertEquals(9, it.next());
		
		assertTrue(it.hasNext());
		assertEquals(10, it.next());
		
		assertTrue(it.hasNext());
		assertEquals(11, it.next());
		
		assertTrue(it.hasNext());
		assertEquals(12, it.next());
		
		assertTrue(it.hasNext());
		assertEquals(13, it.next());
		
		assertTrue(it.hasNext());
		assertEquals(14, it.next());
		
		assertTrue(it.hasNext());
		assertEquals(15, it.next());
		
		assertTrue(it.hasNext());
		assertEquals(16, it.next());
		
		assertTrue(it.hasNext());
		assertEquals(17, it.next());	
		
		assertFalse(it.hasNext());
	}
	
	
	//The iterator doc does not mention throwing any exceptions
	/*
	@Test
	public void iteratorRemoveTest1() {
		
		init4();
		
		Iterator<Integer> it = st.iterator();
		
		assertThrows(Exception.class, ()->{it.remove();});
		
	} */
	
	@Test
	public void iteratorRemoveTest2() {
		
		//The iterator doc does not mention throwing any exceptions
		init4();
		Iterator<Integer> it = st.iterator();
		
		it.remove();
		
		String expected = "30\n    null\n    45";
		String actual = st.toString();
		
		assertEquals(expected, actual);
		assertTrue(it.hasNext());
		assertEquals(30, it.next());
	}
	
	@Test
	public void videoTest() {
		Video v = new Video("Forrest Gump", 2);
		Video v2 = new Video("Star Wars");
		try {
			v.rentCopies(1);			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		assertEquals("Forrest Gump (2:1)", v.toString());
		
		assertThrows(IllegalArgumentException.class, () -> {
			v.rentCopies(0);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			v.addNumCopies(0);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			v.returnCopies(0);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Video("Illegal McArgumentException III: Revenge of the Catch", 0);
		});
		assertThrows(AllCopiesRentedOutException.class, () -> {
			v.rentCopies(10);
		});
		
		assertEquals("Forrest Gump".compareTo("Star Wars"), v.compareTo(v2));
		
		
		
	}
	
	
	
	
	
}