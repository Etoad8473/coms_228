package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class FoxTest 
{
	Plain e;
	Fox fox;
	
	
	@Before
	public void setup() 
	{
		e = new Plain("empty.txt");
	}
	
	//tests .myAge and .who
	@Test
	public void GeneralTest() 
	{		
		fox = new Fox(e,1,1,4);

		assertEquals(fox.myAge(), 4);
		assertEquals(fox.who(), State.FOX);
	}
	
	//tests old age
	@Test
	public void oldAge() 
	{
		//test if badger dies of old age
		fox = new Fox(e,1,1,6);
		Living l = fox.next(e);
		
		assertEquals(l.who(), State.EMPTY);
	}
	
	//tests aging process
	@Test
	public void ageUp() 
	{
		fox = new Fox(e,1,1,0);
		Animal a = (Animal)fox.next(e);
		
		assertEquals(a.myAge(), 1);
		assertEquals(a.who(), State.FOX);
	}
	
	//tests dies of hunger
	@Test
	public void hungerDeath() 
	{
		Plain f = new Plain("2fox.txt");
		fox = new Fox(f,1,0,0);
		Living a = fox.next(f);
		
		assertEquals(a.who(), State.EMPTY);
	}
	
}
