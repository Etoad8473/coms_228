package fExamPractice;

public class Heapsort 
{

	
	public class Heap
	{
		public int size;
		
		public void heapify(int data, int index) 
		{
			
			//take heap in any order
			//in reverse order from non root element (size/2 -1) to the root
			//percolate down
			
			index = size/2 -1;
			while (index >= 0) 
			{
				percolateDown(data,index);
				--index;
			}
			
		}
		
		public void percolateDown(int data, int index)
		{
			
		}
		
		public int remove() {return 0;}
		
		public boolean isEmpty() {return true;}
	}
	
	public static int[] heapSort(int[] arr) 
	{
		//put elements into a max heap H
		//create empty list L
		//while !H.isEmpty()
			//x = H.remove()
			//put x at the beginning of L
		//return L
		
		Heap h = new Heap();
		
		h.heapify(arr);
		
		int[] sorted = new int[arr.length];
		int ind = 0;
		
		while(!h.isEmpty())
		{
			int x = h.remove();
			sorted[ind] = x;
			ind++;
		}
		
		return sorted;
	}
	
	public static void main(String args[]) 
	{
		
	}
	
}
