package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw2.Point;
import edu.iastate.cs228.hw2.QuickSorter;

class TestQuickSorter {

	QuickSorter s;
	
	@BeforeEach
	void setup() {
		Point[] pts = {new Point(8,5), new Point(3,7), new Point(3,4), new Point(9,5), new Point(1,2)};
		s = new QuickSorter(pts);
	}
	
	@Test
	void testInit() {
		Point[] other = new Point[5];
		s.getPoints(other);
		
		assertArrayEquals(other, new Point[] {new Point(8,5), new Point(3,7), new Point(3,4), new Point(9,5), new Point(1,2)});
	}
	
	@Test
	void testSortOverX() {
		s.setComparator(0);
		s.sort();
		Point[] pts = new Point[5];
		s.getPoints(pts);
		assertArrayEquals(new Point[] { new Point(1,2), new Point(3,4), new Point(3,7), new Point(8,5), new Point(9,5)}, pts);
	}

	@Test
	void testSortOverY() {
		s.setComparator(1);
		s.sort();
		Point[] pts = new Point[5];
		s.getPoints(pts);
		assertArrayEquals(new Point[] { new Point(1,2), new Point(3,4), new Point(8,5), new Point(9,5), new Point(3,7)}, pts);
	}
	
	@Test
	void testSortOverXEvenLength() {
		Point[] pts = {new Point(8,5), new Point(3,7), new Point(3,4), new Point(9,5), new Point(1,2), new Point(6,4)};
		s = new QuickSorter(pts);
		s.setComparator(0);
		s.sort();
		s.getPoints(pts);
		assertArrayEquals(new Point[] { new Point(1,2), new Point(3,4), new Point(3,7), new Point(6,4), new Point(8,5), new Point(9,5)}, pts);
	}

	@Test
	void testSortOverYEvenLength() {
		Point[] pts = {new Point(8,5), new Point(3,7), new Point(3,4), new Point(9,5), new Point(1,2), new Point(6,4)};
		s = new QuickSorter(pts);
		s.setComparator(1);
		s.sort();
		s.getPoints(pts);
		assertArrayEquals(new Point[] { new Point(1,2), new Point(3,4), new Point(6,4), new Point(8,5), new Point(9,5), new Point(3,7)}, pts);
	}
}
