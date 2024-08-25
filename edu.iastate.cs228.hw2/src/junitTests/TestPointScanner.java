package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw2.Algorithm;
import edu.iastate.cs228.hw2.Point;
import edu.iastate.cs228.hw2.PointScanner;

class TestPointScanner {

	private Algorithm[] algs = {Algorithm.InsertionSort, Algorithm.MergeSort, Algorithm.QuickSort, Algorithm.SelectionSort};
	
	private String path = new File("").getAbsolutePath();
	
	class PSTester extends PointScanner{	// so protected method can be accessed by class
		protected PSTester(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException {
			super(inputFileName, algo);
		}
	}
	
	@Test
	void testPointScannerThrowsIllegalArgumentException() {
		for(Algorithm al : algs) {
			try{
				new PointScanner(new Point[] {}, al);
				fail("Point scanner not throwing illegal argument exception on " + al.toString() + " when given an empty list");
			} catch (IllegalArgumentException e) {}
		}
		for(Algorithm al : algs) {
			try{
				new PointScanner(null, al);
				fail("Point scanner not throwing illegal argument exception on " + al.toString() + " when given null");
			} catch (IllegalArgumentException e) {}
		}
	}

	@Test
	void testPointScannerFileNotFound() {
		for(Algorithm al : algs) {
			try {
				new PSTester(path + "/src/junitTests/JUnitTest1-OddNumberCount.txt", al);
				fail("Point scanner should throw inputmismatchexception for odd number count in file");
			} catch (InputMismatchException e) {
				// do nothing, expected
			} catch (FileNotFoundException e) {
				fail("Make sure file JUnitTest1-OddNumberCount.txt exists in src/junitTests/");
			}
		}
	}
	
	@Test
	void testPointScannerFileDoesntExist() {
		for(Algorithm al : algs) {
			try {
				new PSTester(path + "/src/junitTests/JUnitTest1-ThisDoesntExist.txt", al);
				fail("Point scanner should throw inputmismatchexception for odd number count in file");
			} catch (InputMismatchException e) {
				fail("file being read from doesnt exist");
			} catch (FileNotFoundException e) {
				//correct
			}
		}
	}
	@Test
	void testPointScannerScanWithStrangeFormatting() {
		for(Algorithm al : algs) {
			try {
				new PSTester(path + "/src/junitTests/JUnitTest2-InconsistantNumberBreaks.txt", al);
			} catch (InputMismatchException e) {
				fail("Point scanner should not throw inputmismatchexception for various number breaks");
			} catch (FileNotFoundException e) {
				fail("Make sure file JUnitTest1-InconsistantNumberBreaks.txt exists in src/junitTests/");
			}
		}
	}
	
	@Test 
	void testScan(){
		for(Algorithm al : algs) {
			try {
				PSTester pst = new PSTester(path + "/src/junitTests/JUnitTest2-InconsistantNumberBreaks.txt", al);
				pst.scan();
				
			} catch (InputMismatchException e) {
				fail("see: testPointScannerScanWithStrangeFormatting");
			} catch (FileNotFoundException e) {
				fail("Make sure file JUnitTest1-InconsistantNumberBreaks.txt exists in src/junitTests/");
			}
		}
	}
	
	@Test
	void testStats() {}
	
	@Test
	void testToString() {
		List<Character> chars = new ArrayList<Character>();
		System.setOut(new PrintStream(new OutputStream() {	 // throws all System.out.println(); to the void
		    @Override
		    public void write(int arg0) throws IOException {
		    	chars.add((char) arg0);
		    }
		}));
		
		for(Algorithm al : algs) {
			try {
				PSTester pst = new PSTester(path + "/src/junitTests/JUnitTest2-InconsistantNumberBreaks.txt", al);
				pst.scan();
				
				assertEquals("MCP: (3, 6)", pst.toString(), "should return in format MCP: (x, y)");
			} catch (InputMismatchException e) {
				fail("see: testPointScannerScanWithStrangeFormatting");
			} catch (FileNotFoundException e) {
				fail("Make sure file JUnitTest1-InconsistantNumberBreaks.txt exists in src/junitTests/");
			}
		}
	}
	
	@Test
	void testWriteMCPToFile() {
	}
}
