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
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		algorithm = "selection sort";
	}	

	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
		nanoTimeStart = System.nanoTime();

		//for i<n
		//min = arr[i]
		//for j=i<n
			//if arr[j] < min
				//min = arr[j]
		//swap
		//temp = [i]
		//[i] = [j]
		//[j] = temp;
	
		for(int i = 0; i < points.length; i++) 
		{
			int mindex = i;
			int j;
			for(j = i; j < points.length; j++) 
			{
				if(pointComparator.compare(points[j], points[mindex]) < 0)
					mindex = j;
			}
			//swap
			swap(i,mindex);
			
		}
		
		nanoTimeEnd = System.nanoTime();

	}
}
