package edu.iastate.cs228.hw1;

/**
 *  
 * @author ezra odole
 *
 */

/**
 * Grass remains if more than rabbits in the neighborhood; otherwise, it is eaten. 
 *
 */
public class Grass extends Living 
{
	public Grass (Plain p, int r, int c) 
	{
		super(p,r,c);
	}
	
	public State who()
	{
		return State.GRASS; 
	}
	
	/**
	 * Grass can be eaten out by too many rabbits. Rabbits may also multiply fast enough to take over Grass.
	 */
	public Living next(Plain pNew)
	{
		/*
		a) Empty if at least three times as many Rabbits as Grasses in the neighborhood;
		b) otherwise, Rabbit if there are at least three Rabbits in the neighborhood;
		c) otherwise, Grass.
		 */
		int[] neighbors = new int[5];
		this.census(neighbors);
		
		Living nextOutput = this;
		
		if(neighbors[4] >= neighbors[3]*3)
			nextOutput = new Empty(pNew, row, column);
		else if(neighbors[4] >= 3)
			nextOutput = new Rabbit(pNew, row, column,0);
		else
			nextOutput = new Grass(pNew, row, column);
		
		return nextOutput;
	}
}
