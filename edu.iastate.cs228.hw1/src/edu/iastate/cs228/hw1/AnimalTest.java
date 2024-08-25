package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AnimalTest {
	
	//tests .myAge and .who
	@Test
	public void AnimalTest() 
	{		
		Plain e = new Plain(3);
		Animal b = new Badger(e,1,1,4);

		assertEquals(b.myAge(), 4);
		assertEquals(b.who(), State.BADGER);
	}
}
