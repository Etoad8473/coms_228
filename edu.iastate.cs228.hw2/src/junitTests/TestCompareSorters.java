package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw2.CompareSorters;
import edu.iastate.cs228.hw2.Point;

class TestCompareSorters {

	@Test
	void testMain() 
	{
		
	}
	
	@Test
	void testGenerateRandomPoints() 
	{
		Point[] pts = CompareSorters.generateRandomPoints(50, new Random());
		for(Point p : pts) {
			if(p == null) {
				fail("list not filled");
			}
		}
		if(pts.length != 50) {
			fail("incorrect array length");
		}
		
		pts = CompareSorters.generateRandomPoints(100, new Random());
		if(pts.length != 100) {
			fail("incorrect array length");
		}
		for(Point p : pts) {
			if(p == null) {
				fail("list not filled");
			}
		}
	}

}
