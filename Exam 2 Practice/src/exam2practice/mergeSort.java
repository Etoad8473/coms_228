package exam2practice;

import java.util.Arrays;

public class mergeSort {
	
	
	public static void splitter(int[] arr, int low, int high) 
	{
		if(low<high) 
		{
				
			//calc mid
			int mid = low + (high-low)/2;
			//if array is splittable
		
			//recursive call on first and second half
			splitter(arr, low, mid);
			splitter(arr, mid +1, high);
			
			//merger() from smaller arrays
			merger(arr, low, mid, high);
		}
	}
	
	public static void merger(int[] arr, int low, int mid, int high) 
	{
		//go through and add them together with two pointers
		
		//get the size of each array
		int lSize = mid-low+1;
		int rSize = high-mid;
		
		//create left and right arrays and fill them
		int[] left = new int[lSize];
		for(int i = 0; i < lSize; i++)
			left[i] = arr[low+i];
		int[] right = new int[rSize];
		for(int j = 0; j < rSize; j++) 
			right[j] = arr[mid + 1 + j];
		
		//merge them together
		//create pointers for the left and right arrays
		int l = 0;
		int r = 0;
		//pointer for insertion point of original array
		int x = low;
		while (l<lSize && r<rSize) 
		{
			if(left[l]<right[r])
			{
				arr[x] = left[l];
				l++;
			}
			else
			{
				arr[x] = right[r];
				r++;
			}
			x++;
		}
		
		while (l<lSize) 
		{
			arr[x] = left[l];
			x++;
			l++;
		}		
		while (r<rSize) 
		{
			arr[x] = right[r];
			x++;
			r++;
		}
		
	}
	
	public static void main (String[]args) 
	{
		int[] arr = {0,4,6,7,1,2,5};
		splitter(arr, 0, arr.length-1);
		System.out.println(Arrays.toString(arr));
	}
}
