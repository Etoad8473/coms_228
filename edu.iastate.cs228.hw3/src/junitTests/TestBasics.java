package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.ListIterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw3.MultiList;

class TestBasics {

	static MultiList<Integer> ml;
	static ListIterator<Integer> iterator;

	@BeforeEach
	void setUpBeforeClass() throws Exception {
		ml = new MultiList<Integer>(4);
		ml.add(1);
		ml.add(2);
		ml.add(3);
	}

	
	@Test
	void testIteratorContains() {
		assertTrue(ml.contains(3));
	}
	
	@Test
	void testIteratorToString() {
		System.out.println(ml.toString());
	}	
	
	@Test
	void testListIteratorToStringInternal() {
		ml.add(1);
		ml.add(2);
		ml.add(3);
		System.out.println(ml.toStringInternal(ml.listIterator()));
	}
	
	@Test
	void testListIteratorindexOf() {
		ml.add(4);
		ml.add(5);
		ml.add(6);
		assertEquals(3, ml.indexOf(4));
	}
	
	@Test
	void testSet() {
		ml.add(4);
		ml.add(5);
		ml.add(6);
		iterator = ml.listIterator();
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.set(7);
		assertEquals(3, ml.indexOf(7));
		
		System.out.println(ml.toStringInternal(iterator));
	}
	
	@Test
	void testListIteratorget() {
		assertEquals(3, ml.get(2));
	}
	
	
	
	
	@Test
	void testSizesAdd() {
		assertEquals(3, ml.size());
	}
	
	@Test
	void testSizesAddOverNode() {
		ml.add(0);	// adds over a node
		ml.add(0);
		ml.add(0);
		assertEquals(6, ml.size());
	}

	@Test
	void testAddingToEmptyList() {
		MultiList<Integer> mul = new MultiList<Integer>();
		assertEquals("[]", mul.toStringInternal());
		mul.add(1);
		assertEquals("[(1, -, -, -)]", mul.toStringInternal());
	}

	@Test
	void testAddingOverflowNode() {
		ml.add(1);
		ml.add(1);
		assertEquals("[(1, 2, 3, 1), (1, -, -, -)]", ml.toStringInternal());
	}
	
	
	@Test
	void testAddSplitLess() {
		ml.add(1);
		ml.add(1);
		ml.add(0,5);
		assertEquals("[(5, 1, 2, -), (3, 1, -, -), (1, -, -, -)]", ml.toStringInternal());
	}
	
	
	@Test
	void testAddSplitGreater() {
		ml.add(1);
		ml.add(1);
		ml.add(3,5);
		assertEquals("[(1, 2, -, -), (3, 5, 1, -), (1, -, -, -)]", ml.toStringInternal());
	}
	
}
