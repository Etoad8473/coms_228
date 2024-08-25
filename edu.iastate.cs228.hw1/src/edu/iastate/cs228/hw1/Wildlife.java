package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 *  
 * @author ezra odole
 *
 */

/**
 * 
 * The Wildlife class performs a simulation of a grid plain with
 * squares inhabited by badgers, foxes, rabbits, grass, or none. 
 *
 */
public class Wildlife 
{
	/**
	 * Update the new plain from the old plain in one cycle. 
	 * @param pOld  old plain
	 * @param pNew  new plain 
	 */
	public static void updatePlain(Plain pOld, Plain pNew)
	{

		// For every life form (i.e., a Living object) in the grid pOld, generate  
		// a Living object in the grid pNew at the corresponding location such that 
		// the former life form changes into the latter life form. 
		// 
		// Employ the method next() of the Living class. 
		
		int width = pOld.getWidth();
		
		for(int y = 0; y < width; y++) 
		{
			for(int x = 0; x < width; x++) 
			{
				pNew.grid[y][x] = pOld.grid[y][x].next(pNew);
			}
		}
	}
	
	//HELPER METHOD PUBLIC ONLY FOR TESTING
	/**
	 * Run cycles of update plain, recursively alternating plains
	 * @param cycles
	 * @param pStart plain that starts
	 * @param pAlt alternating plain
	 */
	public static Plain simulatePlainCycles(int numCycles,Plain pStart, Plain pAlt) 
	{
		
		if(numCycles <= 0)
			return pStart;
		
			updatePlain(pStart, pAlt);
			numCycles--;
			return simulatePlainCycles(numCycles, pAlt, pStart);
			
	}
	
	/**
	 * Repeatedly generates plains either randomly or from reading files. 
	 * Over each plain, carries out an input number of cycles of evolution. 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{	
		// Generate wildlife simulations repeatedly like shown in the 
		// sample run in the project description. 
		// 
		// 1. Enter 1 to generate a random plain, 2 to read a plain from an input
		//    file, and 3 to end the simulation. (An input file always ends with 
		//    the suffix .txt.)
		// 
		// 2. Print out standard messages as given in the project description. 
		// 
		// 3. For convenience, you may define two plains even and odd as below. 
		//    In an even numbered cycle (starting at zero), generate the plain 
		//    odd from the plain even; in an odd numbered cycle, generate even 
		//    from odd. 
		
		Plain even;   				 // the plain after an even number of cycles 
		Plain odd;                   // the plain after an odd number of cycles
		
		Scanner scan = new Scanner(System.in);
		int trial = 1;
		// 4. Print out initial and final plains only.  No intermediate plains should
		//    appear in the standard output.  (When debugging your program, you can 
		//    print intermediate plains.)
		// 
		// 5. You may save some randomly generated plains as your own test cases. 
		// 
		// 6. It is not necessary to handle file input & output exceptions for this 
		//    project. Assume data in an input file to be correctly formated. 
		
		System.out.println("Simulation of Wildlife of the Plain");
		System.out.println("keys: 1 (random plain) 2 (file input) 3 (exit)\r\n");
		System.out.print("Trial " + trial + ": ");
		
		int key = scan.nextInt();
		
		while(key==1 || key==2)
		{
			String filename;
			
			if(key == 1)
			{
				System.out.println("Random Plain");
				System.out.print("Enter grid width: ");
				int width = scan.nextInt();
				
				//generate plain w 'width'
				even = new Plain(width);
				even.randomInit();
				
				odd = new Plain(width);

			}
			else
			{
				System.out.print("Plain input from a file\nFile name: ");
				filename = scan.next();
				
				//generate plain from file
				even = new Plain(filename);
				
				odd = new Plain(even.getWidth());
			}
			

			System.out.print("Enter number of cycles: ");
			int cycles = scan.nextInt();
			System.out.println("\nInitial plain:\n");
			
			//print original plain
			System.out.println(even);
			
			//RUN SIM for 'cycles'
			Plain pFinal = Wildlife.simulatePlainCycles(cycles, even, odd);
			
			System.out.println("Final plain:\n");
			//print result
			System.out.println(pFinal);
			
			
			trial++;
			System.out.print("Trial " + trial + ": ");
			key = scan.nextInt();
		}
		
		scan.close();
	}
}
