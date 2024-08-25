package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GrassTest {
	
	
	Plain e;
	Grass g;
	
	
	@Before
	public void setup() 
	{
		e = new Plain("empty.txt");
	}
	
	//tests .who
	@Test
	public void GeneralTest() 
	{		
		g = new Grass(e,1,2);

		assertEquals(g.who(), State.GRASS);
	}
	
	//tests .next()
	@Test
	public void TestNext() 
	{
		e = new Plain("rabbit.txt");
		g = new Grass(e,1,2);
		Living a = g.next(e);
		
		assertEquals(a.who(), State.GRASS);
	}
}
