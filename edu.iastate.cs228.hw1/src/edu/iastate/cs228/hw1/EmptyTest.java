package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class EmptyTest {
	
	
	Plain e;
	Empty mt;
	
	
	@Before
	public void setup() 
	{
		e = new Plain("empty.txt");
	}
	
	//tests .who
	@Test
	public void GeneralTest() 
	{		
		mt = new Empty(e,1,0);

		assertEquals(mt.who(), State.EMPTY);
	}
	
	//tests .next()
	@Test
	public void TestNext() 
	{
		e = new Plain("rabbit.txt");
		mt = new Empty(e,1,0);
		Living a = mt.next(e);
		
		assertEquals(a.who(), State.EMPTY);
	}
	
	
}
