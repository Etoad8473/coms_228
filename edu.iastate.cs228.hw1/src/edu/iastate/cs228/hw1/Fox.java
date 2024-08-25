package edu.iastate.cs228.hw1;

/**
 *  
 * @author ezra odole
 *
 */

/**
 * A fox eats rabbits and competes against a badger. 
 */
public class Fox extends Animal 
{
	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Fox (Plain p, int r, int c, int a) 
	{
		super(p,r,c,a);
	}
		
	/**
	 * A fox occupies the square. 	 
	 */
	public State who()
	{
		return State.FOX; 
	}
	
	/**
	 * A fox dies of old age or hunger, or from attack by numerically superior badgers. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		/*
		a) Empty if the Fox is currently at age 6;
		b) otherwise, Badger, if there are more Badgers than Foxes in the neighborhood;
		c) otherwise, Empty, if Badgers and Foxes together outnumber Rabbits in the neighborhood;
		d) otherwise, Fox (the fox will live on).
		 */
		
		int[] neighbors = new int[5];		
		this.census(neighbors);
		
		Living nextOutput = this;
		
		if(this.myAge() >= 6)
			nextOutput = new Empty(pNew, row, column);
		else if(neighbors[0] > neighbors[2])
			nextOutput = new Badger(pNew, row, column, 0);
		else if((neighbors[0] + neighbors[2]) > neighbors[4])
			nextOutput = new Empty(pNew, row, column);
		else
			nextOutput = new Fox(pNew, row, column, age + 1);
		
		return nextOutput;
	}
}
