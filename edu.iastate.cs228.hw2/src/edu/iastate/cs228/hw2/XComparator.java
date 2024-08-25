package edu.iastate.cs228.hw2;

import java.util.Comparator;

public class XComparator implements Comparator<Point>
{
	@Override
	public int compare(Point first, Point second) 
	{
		Point.xORy = true;
		
		return first.compareTo(second);
	}
}
