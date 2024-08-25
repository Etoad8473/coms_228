package fExamPractice;

import java.util.HashMap;
import java.util.Queue;

public class BSFClass {

	

	
	public class Vert{}
	
	/*
	public int[] BSF(Graph g, Vert s, int[] color, int[] pred)
	{
		int[] dist = new int[g.size()];
		Queue<E> <Vert> q = new Queue<Vert>();
		
		for(Vert x: g)
			if (x.compareTo(s)!=0)
				color(x) = white;
				pred(x) = null;
				dist(x) = -1;
				
		color(s) = grey;
		pred(s) = null;
		dist(s) = 0;
		
		q.enqueue(s)
		
		while(!q.isEmpty()) 
		{
			Vert v = q.peek();
			for(Vert u: v.getNeighbors)
			{
				if(color(u) == green) 
				{
					color(u) = grey;
					pred(u) = v;
					distance(u) = v+1;
					q.enqueue(u)
				}
			}
			q.dequeue(v);
			color.v = black;
		}
		
		return dist;
	}
	*/
	
	public HashMap<V,V> <V>DFS(Graph g) 
	{
		
		HashMap<V,String> color = new HashMap<V,String>();
		HashMap<V,V> pred = new HashMap<V,V>();
		
		for(V v: g.getVerts()) 
		{
			color.set(v, "white");
			pred.set(v, null);
		}
		
		for(V v: g.getVerts()) 
		{
			if(color.get(v) == "white")
			{DFSVisit(g,v,color,pred);}
		}
		
		return pred;
	}
	
	public void DFSVisit(Graph g, V v, HashMap<V,String> color, HashMap<V,V> pred) 
	{
		color.v = grey;
		
		for(V u: g.getNeighbors(v)) 
		{
			if(color.u == green) 
			{
				pred.u = v;
				DFSVisit(g,u,color,pred);
			}
		}
		
		color.v = black;
	}
	
	
}
