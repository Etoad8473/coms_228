package edu.iastate.cs228.hw2;

import java.util.Comparator;

public class YComparator implements Comparator<Point>
{
	@Override
	public int compare(Point first, Point second) 
	{
		Point.xORy = false;
		
		return first.compareTo(second);
	}
}
