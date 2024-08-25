package exam2practice;

import java.util.Arrays;

public class quicksort {

	public static void quicks(int[] arr, int start, int end) 
	{
		//set pivot
		//as long as arr is longer than 1
		
		//sort
			//linearly check each element against pivot
			//if it is less, then swap it at the swap index, increment swap index
		//recursively call quicks on each half
		if(start >= end)
			return;
		
		int pivot = arr[end];
		int swapID = start;
		int checkID = start;
		while (checkID < end) 
		{
			checkID++;
			if(checkID < pivot) 
			{
				swap(arr, checkID, swapID);
				swapID++;
			}
		}
		
		swap(arr, end, (swapID + 1));
		
		quicks(arr, swapID + 1, end);
		quicks(arr, 0, swapID - 1);
		
	}
	
	public static void swap(int[] arr, int firstID, int secondID) 
	{
		int temp = arr[firstID];
		arr[firstID] = arr[secondID];
		arr[secondID] = temp;
	}
	
	
	
	public static void quickSort2(int[] arr, int low, int high) 
	{
		//only if the array is longer than 1
		if (low < high) 
		{
			//does the correct partialSorting and returns the splitPoint
			int mid = sort(arr, low, high);
			
			//sorts the lower half without the mid
			quickSort2(arr, low, mid - 1);
			//sorts teh upper half without the mid
			quickSort2(arr, mid + 1, high);
		}
	}
	
	public static int sort(int[] arr, int low, int high)
	{
		//last element of the array is the pivot
		int pivot = arr[high];
		
		int swapID = low - 1;
		
		for(int check = low; check < high; check++) 
		{
			if(arr[check]<pivot) 
			{
				swapID++;
				swap(arr, check, swapID);
			}
		}
		
		//swap the pivot value at the end to the split index
		swap(arr, swapID + 1, high);
		
		//return the index past the swapID
		return swapID + 1;
		
	}
	
	public static void main (String[] args) 
	{
		int[] arr = {0,4,6,7,1,2,5};
		quickSort2(arr, 0, arr.length-1);
		System.out.println(Arrays.toString(arr));
	}
	
}
