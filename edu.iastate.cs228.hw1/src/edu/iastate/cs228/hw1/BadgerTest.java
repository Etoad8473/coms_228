package edu.iastate.cs228.hw1;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 
import static org.junit.Assert.assertTrue; 
import static org.junit.Assert.assertFalse; 
import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.fail; 

public class BadgerTest 
{
	Plain e;
	Badger b;
	
	@Before
	public void setup() 
	{
		e = new Plain("empty.txt");
		
	}
	
	//tests badger old age, and age++
	@Test
	public void bagderNextAge() 
	{
		//test if badger dies of old age
		b = new Badger(e,1,1,4);
		Living l = b.next(e);
		
		assertEquals(l.who(), State.EMPTY);
		
		
		//test that badger ages up 1
		b = new Badger(e,1,1,0);
		Animal a = (Animal)b.next(e);
		
		assertEquals(a.myAge(), 1);
		assertEquals(a.who(), State.BADGER);
	}
	
	//tests that the fox attack works
	@Test
	public void foxAttack() 
	{
		Plain f = new Plain("2fox.txt");
		b = new Badger(f,1,1,0);
		Animal a = (Animal)b.next(f);
		
		assertEquals(a.myAge(), 0);
		assertEquals(a.who(), State.FOX);
	}
	
	//tests .myAge and .who
	@Test
	public void badgerGeneralTest() 
	{		
		b = new Badger(e,1,1,4);

		assertEquals(b.myAge(), 4);
		assertEquals(b.who(), State.BADGER);
	}
}


