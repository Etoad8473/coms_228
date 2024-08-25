package edu.iastate.cs228.hw1;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 
import static org.junit.Assert.assertTrue; 
import static org.junit.Assert.assertFalse; 
import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.fail;

import java.util.Arrays; 

public class LivingTest {

	
	//tests census function for multiple spots in the grid
	@Test
	public void censusTest() 
	{
		//initialize plain
		Plain p = new Plain(3);
		p.randomInit();
		int[] pop = new int[5];

		
		//count number of neighbors (should be 8)
		Living a = p.grid[1][1];
		a.census(pop);
		int neighborhoodSum = 0;
		for(int i = 0; i<5; i++)
			neighborhoodSum += pop[i];
		
		assertEquals(9, neighborhoodSum);
		
		//top left corner should have 3 neighbors
		a = p.grid[0][0];
		pop = new int[5];
		a.census(pop);
		neighborhoodSum = 0;
		for(int i = 0; i<5; i++)
			neighborhoodSum += pop[i];
		
		assertEquals(4, neighborhoodSum);
		
		//middle right should have 5 neighbors
		a = p.grid[2][1];
		pop = new int[5];
		a.census(pop);
		neighborhoodSum = 0;
		for(int i = 0; i<5; i++)
			neighborhoodSum += pop[i];
		
		assertEquals(6, neighborhoodSum);
		
		//check bottom right, should have 3 neighbors
		a = p.grid[2][2];
		pop = new int[5];
		a.census(pop);
		neighborhoodSum = 0;
		for(int i = 0; i<5; i++)
			neighborhoodSum += pop[i];
		
		assertEquals(4, neighborhoodSum);
		
		a = p.grid[2][0];
		pop = new int[5];
		a.census(pop);
		neighborhoodSum = 0;
		for(int i = 0; i<5; i++)
			neighborhoodSum += pop[i];
		
		assertEquals(4, neighborhoodSum);
	}
}
