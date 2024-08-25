package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author
 *
 */

/**
 * 
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter 
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 * 
	 * @param pts  
	 */
	public InsertionSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "insertion sort";

	}	

	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 */
	@Override 
	public void sort()
	{
		nanoTimeStart = System.nanoTime();
		
		//for length of array (starting at index 1)
		for(int i = 1; i < points.length; i++) 
		{
			//value to check against [i], before it
			int j = i-1;
			//hold point at i in temp
			Point temp = points[i];
			
				//if j isnt negative & value before i is larger than i
				while(j >= 0 && pointComparator.compare(points[j], temp) > 0) 
				{
					//move large value onto i
					points[j+1] = points[j];
					//repeat for each previous value
					j--;
				}
				
			//insert original value at i into position after j (which stops at a value smaller than [i])
			points[j+1] = temp;
		}
		
		nanoTimeEnd = System.nanoTime();
	}		
}
