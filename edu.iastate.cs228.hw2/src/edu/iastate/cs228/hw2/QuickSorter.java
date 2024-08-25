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
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{
	
	// Other private instance variables if you need ... 
		
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{
		super(pts);
		algorithm = "quicksort";

	}
		

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 */
	@Override 
	public void sort()
	{
		nanoTimeStart = System.nanoTime();

		quickSortRec(0, points.length-1);
		
		nanoTimeEnd = System.nanoTime();

	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		if(first < last) //stops recursion when there's only one element
		{
			// get partition index after the partition sorting
			int part = partition(first, last);
			
			quickSortRec(first, part - 1); //recursion on first half
			quickSortRec(part + 1, last); //recursion on second half
		}
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last)
	{
		Point pivPoint = points [last]; // creates pivot from last element
		
		int i = (first - 1); //insert point for smaller elements
		
		for(int j = first; j < last; j++) //goes through all (excluding pivot) checking against the pivot
		{
			if(pointComparator.compare(points[j], pivPoint) < 0) 
			{
				i++; //increment the insert point
				swap(i,j);
			}
		}
		
		swap(i+1, last);//puts the partition into correct position
		
		return i+1; //returns the split point (partition) after sorting
	}	
		
}
