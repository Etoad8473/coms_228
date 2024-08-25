package edu.iastate.cs228.hw1;

/**
 *  
 * @author ezra odole
 *
 */

/**
 * A badger eats a rabbit and competes against a fox. 
 */
public class Badger extends Animal
{
	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Badger (Plain p, int r, int c, int a) 
	{
		super(p,r,c,a);
	}
	
	/**
	 * A badger occupies the square. 	 
	 */
	public State who()
	{
		return State.BADGER; 
	}
	
	/**
	 * A badger dies of old age or hunger, or from isolation and attack by a group of foxes. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		/*
		    a) Empty if the Badger is currently at age 4;
			b) otherwise, Fox, if there is only one Badger but there are more than one Fox in the neighborhood;
			c) otherwise, Empty, if Badgers and Foxes together outnumber Rabbits in the neighborhood;
			d) otherwise, Badger (the badger will live on).
		 */
		int[] neighbors = new int[5];
		this.census(neighbors);
		
		Living nextOutput = this;
		
		if(this.myAge() >= 4)
			nextOutput = new Empty(pNew, row, column);
		else if(neighbors[0] == 1 && neighbors[2] > 1)
			nextOutput = new Fox(pNew, row, column, 0);
		else if((neighbors[0] + neighbors[2]) > neighbors[4])
			nextOutput = new Empty(pNew, row, column);
		else
			nextOutput = new Badger(pNew, row, column, age + 1);
		
		return nextOutput; 
	}
}
