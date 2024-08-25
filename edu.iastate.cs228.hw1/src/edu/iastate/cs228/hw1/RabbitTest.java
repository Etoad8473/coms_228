package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RabbitTest {
	
	Plain e;
	Rabbit r;
	
	
	@Before
	public void setup() 
	{
		e = new Plain("empty.txt");
	}
	
	//tests .myAge and .who
	@Test
	public void GeneralTest() 
	{		
		r = new Rabbit(e,1,1,4);

		assertEquals(r.myAge(), 4);
		assertEquals(r.who(), State.RABBIT);
	}
	
	//tests old age
	@Test
	public void oldAge() 
	{
		//test if badger dies of old age
		r = new Rabbit(e,1,1,3);
		Living l = r.next(e);
		
		assertEquals(l.who(), State.EMPTY);
	}
	
	//tests aging process
	@Test
	public void ageUp() 
	{
		e = new Plain("rabbit.txt");
		r = new Rabbit(e,1,1,0);
		Living a = r.next(e);
		
		assertEquals(a.who(), State.RABBIT);
	}
	
	//tests dies of hunger
	@Test
	public void hungerDeath() 
	{
		r = new Rabbit(e,1,0,0);
		Living a = r.next(e);
		
		assertEquals(a.who(), State.EMPTY);
	}
	
}
