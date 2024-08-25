package edu.iastate.cs228.hw1;

/**
 *  
 * @author ezra odole
 *
 */

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner; 
import java.util.Random; 

/**
 * 
 * The plain is represented as a square grid of size width x width. 
 *
 */
public class Plain 
{
	private int width; // grid size: width X width 
	
	public Living[][] grid; 
	
	/**
	 *  Default constructor reads from a file 
	 */
	public Plain(String inputFileName)
	{		
        // TODO 
		// 
		// Assumption: The input file is in correct format. 
		// 
		// You may create the grid plain in the following steps: 
		// 
		// 1) Reads the first line to determine the width of the grid.
		// 
		// 2) Creates a grid object. 
		// 
		// 3) Fills in the grid according to the input file. 
		// 
		// Be sure to close the input file when you are done. 
		try {
		File f = new File(inputFileName);
		width = 0;
		
		Scanner checkWidth = new Scanner(f);
		while(checkWidth.hasNextLine()) 
		{
			checkWidth.nextLine();
			width++;
		}
		checkWidth.close();
		
		grid = new Living[width][width];
		
		Scanner scan = new Scanner(f);
		for(int y = 0; y < width; y++) 
		{
			for(int x = 0; x < width; x++) 
			{
				grid[y][x] = stringToLiving(scan.next(), y, x);
			}
		}
		scan.close();
		}
		catch(FileNotFoundException e) 
		{}
	}
	
	/**
	 * Constructor that builds a w x w grid without initializing it. 
	 * @param width  the grid 
	 */
	public Plain(int w)
	{
		width = w;
		grid  = new Living[width][width];
	}
	
	
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Initialize the plain by randomly assigning to every square of the grid  
	 * one of BADGER, FOX, RABBIT, GRASS, or EMPTY.  
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit()
	{
		Random generator = new Random(); 
		 
		//for each grid element
		for(int y = 0; y < width; y++) 
		{
			for(int x = 0; x < width; x++) 
			{
				//generate 1 of 5 type numbers
				int type = generator.nextInt(5);
				
				//for the diff random nums generated, instantiate a baby living object
				if(type == 0)
					grid[y][x] = new Badger(this, y, x, 0);
				if(type == 1)
					grid[y][x] = new Empty(this, y, x);
				if(type == 2)
					grid[y][x] = new Fox(this, y, x, 0);
				if(type == 3)
					grid[y][x] = new Grass(this, y, x);
				if(type == 4)
					grid[y][x] = new Rabbit(this, y, x, 0);
					
			}
		}
	}
	
	
	/**
	 * Output the plain grid. For each square, output the first letter of the living form
	 * occupying the square. If the living form is an animal, then output the age of the animal 
	 * followed by a blank space; otherwise, output two blanks.  
	 */
	public String toString()
	{
		String out = "";
		
		for(int y = 0; y < width; y++) 
		{
			for(int x = 0; x < width; x++) 
			{
				Living thing = grid[y][x];
				char first = thing.who().toString().charAt(0);
				
				out += first;
				
				if("BFR".indexOf(first) == -1)//if its not an animal
					out += " ";//add a space
				else
					out += ((Animal)thing).myAge();//add its age
				
				out += " ";
			}
			out += "\n";
		}
		return out; 
	}
	

	/**
	 * Write the plain grid to an output file.  Also useful for saving a randomly 
	 * generated plain for debugging purpose. 
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException
	{
		// 1. Open the file. 
		// 
		// 2. Write to the file. The five life forms are represented by characters 
		//    B, E, F, G, R. Leave one blank space in between. Examples are given in
		//    the project description. 
		// 
		// 3. Close the file. 
		try 
		{
		
			File f = new File(outputFileName);
			f.createNewFile();
			FileWriter w = new FileWriter(f);
			w.write(this.toString());
			w.close();
			
		}
		catch(IOException e)  {}
		
		
	}
	
	/**
	 * @return Living object from character notation F1 = Fox 1
	 * @param s - input string (eg "F1")
	 * @param y - row
 	 * @param x - column
	 */
	private Living stringToLiving(String s, int y, int x) 
	{
		char first = s.charAt(0);
		if(s.length() < 2) 
		{
			if(first == 'E') 
				return new Empty(this, y, x);
			else
				return new Grass(this, y, x);
		}
		else
		{
			char second = s.charAt(1);
			int age = Character.getNumericValue(second);
			
			if(first == 'B')
				return new Badger(this, y, x, age);
			else if(first == 'F')
				return new Fox(this, y, x, age);
			else
				return new Rabbit(this, y, x, age);
		}
	}
}
