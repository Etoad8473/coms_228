package edu.iastate.cs228.hw1;

/**
 *  
 * @author ezra odole
 *
 */

/** 
 * Empty squares are competed by various forms of life.
 */
public class Empty extends Living 
{
	public Empty (Plain p, int r, int c) 
	{
		super(p,r,c);
	}
	
	public State who()
	{
		return State.EMPTY; 
	}
	
	/**
	 * An empty square will be occupied by a neighboring Badger, Fox, Rabbit, or Grass, or remain empty. 
	 * @param pNew     plain of the next life cycle.
	 * @return Living  life form in the next cycle.   
	 */
	public Living next(Plain pNew)
	{
		/*
		a) Rabbit, if more than one neighboring Rabbit;
		b) otherwise, Fox, if more than one neighboring Fox;
		c) otherwise, Badger, if more than one neighboring Badger;
		d) otherwise, Grass, if at least one neighboring Grass;
		e) otherwise, Empty.
		*/
		int[] neighbors = new int[5];
		this.census(neighbors);
		
		Living nextOutput = this;
		
		if(neighbors[4] > 1)
			nextOutput = new Rabbit(pNew, row, column, 0);
		else if(neighbors[2] > 1)
			nextOutput = new Fox(pNew, row, column, 0);
		else if(neighbors[0] > 1)
			nextOutput = new Badger(pNew, row, column, 0);
		else if(neighbors[3] >= 1)
			nextOutput = new Grass(pNew, row, column);
		else
			nextOutput = new Empty(pNew, row, column);
		
		return nextOutput;
	}
}
