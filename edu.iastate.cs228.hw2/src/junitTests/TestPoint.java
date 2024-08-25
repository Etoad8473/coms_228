package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw2.Point;

class TestPoint {

	@Test
	void testxORy() {
		Point.setXorY(true);
		assertEquals(Point.xORy, true);
		Point.setXorY(false);
		assertEquals(Point.xORy, false);
	}

	@Test
	void testCompareTo() {
		Point a = new Point(1,2);
		Point b = new Point(2,2);
		Point c = new Point(3,1);
		Point d = new Point(3,1);
		Point e = new Point(1,3);
		Point f = new Point(1,-1);
		Point g = new Point(5,-1);
		
		Point.setXorY(true);	// check on x
		
		assertEquals(a.compareTo(b), -1);
		assertEquals(c.compareTo(b), 1);
		assertEquals(a.compareTo(e), -1);
		assertEquals(a.compareTo(f), 1);
		assertEquals(c.compareTo(d), 0);
		
		Point.setXorY(false);
		
		assertEquals(b.compareTo(c), 1);
		assertEquals(d.compareTo(e), -1);
		assertEquals(a.compareTo(b), -1);
		assertEquals(g.compareTo(f), 1);
	}
	
	@Test
	void testToString() {
		Random r = new Random();
		for(int i = 0; i < 100; i++) {
			int x = r.nextInt(100) - 50;
			int y = r.nextInt(100) - 50;
			
			Point p = new Point(x, y);
			
			assertTrue(("(" + x + ", " + y + ")").equals(p.toString()));
			
		}
	}
	
	@Test 
	void testDefaultValues(){
		Point p = new Point();
		assertEquals(p.getX(), 0);
		assertEquals(p.getY(), 0);
	}
	
}





