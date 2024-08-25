package edu.iastate.cs228.hw1;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 
import static org.junit.Assert.assertTrue; 
import static org.junit.Assert.assertFalse; 
import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.fail;

import java.util.Arrays;

public class WildlifeTest {

	
	Plain p0;
	Plain alt = new Plain(3);
	Plain p1 = new Plain(3);
	Plain p2 = new Plain(3);
	Plain p3 = new Plain(3);
	Plain p4 = new Plain(3);
	Plain p5 = new Plain(3);
	
	@Before
	public void setup() 
	{
		String file = "public1-3x3.txt";
		p0 = new Plain(file);
	}
	
	//manual updatePlain for 5 cycles (written prior to cycle method)
	@Test
	public void simulateTest() 
	{
		System.out.println(p0);
		
		Wildlife.updatePlain(p0, p1);
		
		System.out.println(p1);
		
		Wildlife.updatePlain(p1, p2);
		
		System.out.println(p2);		
		
		Living e = p2.grid[2][0];
		int[] census = new int[5];
		e.census(census);
		System.out.println(Arrays.toString(census));
		
		Wildlife.updatePlain(p2, p3);
		
		System.out.println(p3);		
		
		Wildlife.updatePlain(p3, p4);
		
		System.out.println(p4);
		
		Wildlife.updatePlain(p4, p5);
		
		System.out.println(p5);
	}

	//tests helper method simulatePlainCycles against public1 files
	@Test
	public void testMultipleCycleSim() 
	{
		String correctSolution = (new Plain("public1-5cycles.txt").toString());
		String simulationOutput = Wildlife.simulatePlainCycles(5, p0, alt).toString();
		assertEquals(correctSolution,simulationOutput);
	}
	
	//tests simulation against public 2 files
	@Test
	public void testCycleSimAgainstPublic2() 
	{
		p0 = new Plain("public2-6x6.txt");
		alt = new Plain(6);
		String correctSolution = (new Plain("public2-8cycles.txt").toString());
		String simulationOutput = Wildlife.simulatePlainCycles(8, p0, alt).toString();
		
		System.out.println(correctSolution + "\n\n\n" + simulationOutput);
		
		assertEquals(correctSolution,simulationOutput);
	}
	
	//tests wildlife helper function simulatePlainCycles works for given plains
	@Test
	public void nextCycle() 
	{
		p0 = new Plain("public2-6x6.txt");
		alt = new Plain(6);
		System.out.println("pre\n"+p0);

		String simulationOutput = Wildlife.simulatePlainCycles(1, p0, alt).toString();
		System.out.println("after\n"+simulationOutput);

	}
	
	//testing public3 generation against correct  8 cycle generation
	@Test
	public void testCycleSimAgainstPublic3() 
	{
		p0 = new Plain("public3-10x10.txt");
		alt = new Plain(10);
		String correctSolution = (new Plain("public3-6cycles.txt").toString());
		String simulationOutput = Wildlife.simulatePlainCycles(6, p0, alt).toString();
		
		System.out.println(correctSolution + "\n\n\n" + simulationOutput);
		
		assertEquals(correctSolution,simulationOutput);
	}
	
}
