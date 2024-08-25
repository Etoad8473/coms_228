package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw2.AbstractSorter;
import edu.iastate.cs228.hw2.InsertionSorter;
import edu.iastate.cs228.hw2.MergeSorter;
import edu.iastate.cs228.hw2.Point;
import edu.iastate.cs228.hw2.QuickSorter;
import edu.iastate.cs228.hw2.SelectionSorter;

class TestAbstractSorter {

	private static List<AbstractSorter> sorters;
	private static Point[][] pArr;
	
	@BeforeAll
	static void generateListOfSorters() {
		sorters = new ArrayList<AbstractSorter>();
		pArr = new Point[4][];
		
		Random r = new Random();
		for(int i = 0; i < 4; i++) {
			int len = 5;
			Point[] arr = new Point[len];
			for(int j = 0; j < len; j++) {
				arr[j] = new Point(r.nextInt(101) - 50, r.nextInt(101) - 50);
			}
			pArr[i] = arr;
		}
		
		sorters.add(new QuickSorter(pArr[0]));
		sorters.add(new SelectionSorter(pArr[1]));
		sorters.add(new MergeSorter(pArr[2]));
		sorters.add(new InsertionSorter(pArr[3]));
	}
	
	@Test
	void reminderToMakeSortProtected() {
		fail("Reminder to make the sort method in AbstractSorter private");
	}
	
	@Test
	void reminderToRemoveEmptyConstructor() {
		fail("Reminder to remove empty constructor");
	}
	
	@Test 
	void testConstructorThrowsIllegalArgumentException() {
		try {
			new QuickSorter(null);
			fail("QuickSorter not throwing IllegalArgumentException on invalid constructor");
		} catch (IllegalArgumentException e) {
			// do nothing if caught
		}
		try {
			new SelectionSorter(null);
			fail("SelectionSorter not throwing IllegalArgumentException on invalid constructor");
		} catch (IllegalArgumentException e) {
			// do nothing if caught
		}
		try {
			new MergeSorter(null);
			fail("MergeSorter not throwing IllegalArgumentException on invalid constructor");
		} catch (IllegalArgumentException e) {
			// do nothing if caught
		}
		try {
			new InsertionSorter(null);
			fail("InsertionSorter not throwing IllegalArgumentException on invalid constructor");
		} catch (IllegalArgumentException e) {
			// do nothing if caught
		}
		try {
			new QuickSorter(new Point[0]);
			fail("QuickSorter not throwing IllegalArgumentException on invalid constructor");
		} catch (IllegalArgumentException e) {
			// do nothing if caught
		}
		try {
			new SelectionSorter(new Point[0]);
			fail("SelectionSorter not throwing IllegalArgumentException on invalid constructor");
		} catch (IllegalArgumentException e) {
			// do nothing if caught
		}
		try {
			new MergeSorter(new Point[0]);
			fail("MergeSorter not throwing IllegalArgumentException on invalid constructor");
		} catch (IllegalArgumentException e) {
			// do nothing if caught
		}
		try {
			new InsertionSorter(new Point[0]);
			fail("InsertionSorter not throwing IllegalArgumentException on invalid constructor");
		} catch (IllegalArgumentException e) {
			// do nothing if caught
		}
	}
	
	@Test 
	void testSetComparatorThrowsIllegalArgumentException() {
		for(AbstractSorter s : sorters) {
			s.setComparator(0);
		}
		for(AbstractSorter s : sorters) {
			s.setComparator(1);
		}
		
		for(AbstractSorter s : sorters) {
			try{
				s.setComparator(-1);
				fail("does not fail for invalid order");
			} catch(IllegalArgumentException e) {
				// indended here
			}
		}
		for(AbstractSorter s : sorters) {
			try{
				s.setComparator(2);
				fail("does not fail for invalid order");
			} catch(IllegalArgumentException e) {
				// indended here
			}
		}
	}
	
	@Test
	void testGetPoints() {
		Point[] other = new Point[5];
		
		for(int i = 0; i < 4; i++) {
			sorters.get(0).getPoints(other);
			Assert.assertArrayEquals(pArr[0], other);
		}
	}
}
