package edu.iastate.cs228.hw1;

/**
 *  
 * @author ezra odole
 *
 */

/*
 * This class is to be extended by the Badger, Fox, and Rabbit classes. 
 */
public abstract class Animal extends Living implements MyAge
{
	protected int age;   // age of the animal 

	
	/**
	 * constructor base for animal constuctors
	 */
	protected Animal(Plain p, int r, int c, int a) //possible bug, private prevents animals from overriding?
	{
		super(p,r,c);
		age = a;
	}
	
	
	@Override
	/**
	 * 
	 * @return age of the animal 
	 */
	public int myAge()
	{
		return age; 
	}
}
