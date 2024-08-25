package edu.iastate.cs228.hw1;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 
import static org.junit.Assert.assertTrue; 
import static org.junit.Assert.assertFalse; 
import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.fail;

import java.io.FileNotFoundException; 

public class PlainTest {

	Plain p;
	
	@Before
	public void setup() 
	{
		p = new Plain(3);
		p.randomInit();
	}
	
	//checks functionality of random generated plain
	//asserts none of the plain is null after generation
	@Test
	public void randomGenerationTest() 
	{
		//for each grid element
		for(int x = 0; x < 3; x++) 
		{
			for(int y = 0; y < 3; y++) 
			{
				assertFalse(p.grid[x][y].who() == null);
			}
		}
	}
	
	//tests the print function
	@Test
	public void printTest() 
	{
		System.out.println(p.toString());
	}
	
	/*
	//HELPER METHOD IS NOW PRIVATE
	//tests helper method converting the symbols into the Living Objects
	@Test
	public void stringToAnimal() 
	{
		String s = "F1";
		Living l = p.stringToLiving(s, 0, 0);
		System.out.println(l.who() +" "+ ((Animal)l).myAge());
	}
	*/
	
	//tests import function
	//and tests that the import is rotated correctly and not imported off axis
	@Test
	public void fromFileInstantiationLocation() 
	{
		String file = "public1-3x3.txt";
		p = new Plain(file);
		Living empty = p.grid[1][2];
		System.out.println(empty.who());
		System.out.println(p.toString());
	}
	
	//tests write to file function
	@Test
	public void writeFileTest() throws FileNotFoundException
	{
		p.write("C:\\Users\\Ezra8\\Desktop\\test.txt");
	}
	
}
