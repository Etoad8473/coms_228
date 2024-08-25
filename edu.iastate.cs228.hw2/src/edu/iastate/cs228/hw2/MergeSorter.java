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
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "mergesort";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		nanoTimeStart = System.nanoTime();
		mergeSortRec(points);
		nanoTimeEnd = System.nanoTime();

	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		split(pts, 0, pts.length -1);
	}
	
	
	//recursive element that does the splitting, then merging
	private void split(Point[] pts, int first, int end) 
	{
		if(first < end)//once sub array has 1 element this will stop
		{
			int mid = (first + end)/2;//find middle
			
			//split down both halves until smallest element is one element
			split(pts, first, mid);
			split(pts, mid+1, end);
			
			merge(pts, first, mid, end); //merge them back together
			//will begin executing once the sub arrays are single elements, merging back from there
			//then every time merge is called the previous layer will already be sorted correctly
		}
	}
	
	
	//merges sub arrays of points
	//first half begin:mid, second half mid+1:end
	private void merge(Point[] pts, int first, int mid, int end)
	{
		int size1 = 1 + mid-first;//get size of left subarray
		Point[] left = new Point[size1];//create temporary array for sub array
		for(int i = 0; i < size1; i++)//fill out temporary array
			 left[i] = pts[first + i];
		
		//do the same for the right subarray
		int size2 = end-mid;
		Point[] right = new Point[size2];
		for(int i = 0; i < size2; i++)
			 right[i] = pts[mid+1 + i];
		
		
		int l = 0;//left subarray merge index
		int r = 0;//riht subarray merge index
		int index = first;//parent array merge index
		
		while(l < size1 && r < size2)//while both arrays still have elements
		{
			//add the smallest element between the two arrays at their merge indexes
			if(pointComparator.compare(left[l], right[r]) <= 0)
			{
				pts[index] = left[l];
				l++;
			}
			else 
			{
				pts[index] = right[r];
				r++;
			}
			index++;
		}
		
		//whichever subarray runs out first, the other finishes filling its elements in
		while(l < size1) 
		{
			pts[index] = left[l];
			l++;
			index++;
		}
		while(r < size2) 
		{
			pts[index] = right[r];
			r++;
			index++;
		}
		
	}


}
