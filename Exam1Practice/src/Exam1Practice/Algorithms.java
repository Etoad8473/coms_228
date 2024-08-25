package Exam1Practice;

import java.util.Arrays;

public class Algorithms implements Comparable<Algorithms>
{
	
	public int size = 1;
	
	public static void insertionSort(int[] arr) 
	{
		//for length of array (starting at index 1)
		for(int i = 1; i < arr.length; i++) 
		{
			//value to check against [i], before it
			int j = i-1;
			//hold value at i in temp
			int temp = arr[i];
			
				//if j isnt negative & value before i is larger than i
				while(j >= 0 && arr[j] > temp) 
				{
					//move large value onto i
					arr[j+1] = arr[j];
					//repeat for each previous value
					j--;
				}
				
			//insert original value at i into position after j (which stops at a value smaller than [i])
			arr[j+1] = temp;
		}
	}
	
	public static void selectionSort(int[] arr) 
	{
		//for i<n
			//min = arr[i]
			//for j=i<n
				//if arr[j] < min
					//min = arr[j]
		//swap
		//temp = [i]
		//[i] = [j]
		//[j] = temp;
		
		for(int i = 0; i < arr.length; i++) 
		{
			int mindex = i;
			int j;
			for(j = i; j < arr.length; j++) 
			{
				if(arr[j]<arr[mindex])
					mindex = j;
			}
			//swap
			int temp = arr[i];
			arr[i] = arr[mindex];
			arr[mindex] = temp;
		}
	}
	
	public int getSize() {return size;} 
	
	
	public static void insertSortAges(int[] ages) 
	{
		//for loop for the array
		//j = i-1
		//temp = [i]
		//while (if: j is not negative & [j]>[i])
			//j--
			//[i] = [j]
		//[j+1] = temp
		for(int i = 1; i < ages.length; i++) 
		{
			int j = i-1;
			int temp = ages[i];
			while(j >= 0 && ages[j]>temp) 
			{
				ages[i] = ages[j];
				j--;
			}
			ages[j+1] = temp;
		}
	}
	
	public static void selectingAgesSort(int[] ages) 
	{
		//for each element starting at i=0 < n
			//youngestIndex = i
			//for j = youngestIndex + 1 < n
				//if [j] < [youngestIndex]
					//youngestIndex = j
			//swap
			//temp = [youngestIndex]
			//[youngestIndex] = [i]
			//[i] = temp
		
		for(int i = 0; i < ages.length; i++) 
		{
			int min = i;
			for(int j = i + 1; j < ages.length; j++)
			{
				if(ages[j]<ages[min]) 
					min = j;
			}
			int temp = ages[min];
			ages[min] = ages[i];
			ages[i] = temp;
		}
	}
	
	
	public static void insertSortNumKids(int[] num) 
	{
	//for each element starting at i=1<n
		//temp = i
		//j = i-1
		//while(if: j not negative & j is out of order ([j]>temp)
			//[i] = [j]
			//j--
		//[j+1] = temp
		for(int i = 1; i< num.length; i++) 
		{
			int temp = num[i];
			int j = i-1;
			while(j>=0 && num[j]>temp) 
			{
				num[j+1] = num[j];
				j--;
			}
			num[j+1] = temp;
		}
	}

	public static void selectionSortNumKids(int[] num) 
	{
		//for each element in arr starting at i = 0 < n
			//min = [i]
			//for each element after i, j = i+1 < n
				//if [j]<[min]
					//min = j
			//swap
			//temp = [min]
			//[min] = [i]
			//[i] = temp
		
		for(int i = 0; i < num.length; i++) 
		{
			int mindex = i;
			for(int j = i + 1; j < num.length; j++) 
			{
				if(num[j] < num[mindex])
					mindex = j;
			}
			//swap
			int temp = num[mindex];
			num[mindex] = num[i];
			num[i] = temp;			
		}
	}
	
	public static void main(String[]args) 
	{
		
		int[] arr = {5,3,2,1};
		
		//insertionSort(arr);
		//selectionSort(arr);
		
		int[] ages = {11, 18, 5, 7, 13};
		
		//insertSortAges(ages);
		//selectingAgesSort(ages);
		
		int[] numKids = {4,3,5,4,2,4,3,3,2,1,2,2};
		
		//insertSortNumKids(numKids);
		selectionSortNumKids(numKids);
		
		
		System.out.print(Arrays.toString(numKids));
		
		
		
	}
	/*
	@Override
	public boolean equals(Object other) 
	{
		if (other == this)
			return true;
		else if (other == null)
			return false;
		else (other.getClass() == this.getClass())
		{
			Algorithms otherA = (Algorithms)other;
			if(otherA.getSize() == this.size) 
				return true;
		}
		return false;
		
	}
	
	@Override
	public Algorithms clone() 
	{
		Algorithms newA;
		
		try 
		{
			newA = (Algorithms) this.clone();
		}
		catch(CloneNotSupportedException e) 
		{
			throw new Error();
		}
		
		//other objects
		
		return newA;
	}
	
	
	*/
	
	
	@Override
	public boolean equals(Object other) 
	{
		if(other == null)
			return false;
		if(other == this)
			return true;
		
		Algorithms a = (Algorithms)other;
		if(a.getSize() == this.size)
			return true;
		else 
			return false;
	}
	
	@Override
	public Algorithms clone() 
	{
		Algorithms a;
		try {
		 a = (Algorithms) super.clone();}
		catch(CloneNotSupportedException e){
			throw new Error();
		}
		//copy other objects
		//a.setB = this.getB.clone();
		
		return a;
	}
	
	@Override
	public int compareTo(Algorithms other) 
	{
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
