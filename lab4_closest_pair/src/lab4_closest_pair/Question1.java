package lab4_closest_pair;

import java.util.ArrayList;

public class Question1 {
	
	/* The Problem: 
	 * 		Given a set of n points in 2D plane, find the distance between a pair of the
	 * 		closest points.
	*/
	public double simpleClosestPair(ArrayList<Point> P) throws IllegalStateException
	{
		int n = P.size();
		
		if ( n > 1 )
		{
			double d, dTmp;
			int i, j;
			
			d = dist(P.get(0), P.get(1));
			
			for( i = 1; i<n-1; ++i)
			{
				for( j = i+1; j < n; ++j)
				{
					dTmp = dist (P.get(i), P.get(j));
					System.out.println(dTmp);
					if( dTmp < d )
						d = dTmp;
				}
			}
			return d;
		}
		
		else
		{
			System.out.println("Points need to be greater than 1");
			throw new IllegalStateException();
		}
	}
	
	public double recursiveClosestPair(ArrayList<Point> P, int k)
	{
		if (k < 2)
		{
			System.out.println("Points need to be greater than 1");
			throw new IllegalStateException();
		}
		else
		{
			if(k == 2)
				return dist(P.get(0), P.get(1));
			
			else
			{
				double d, dTmp;
				int i;
				
				d = recursiveClosestPair(P, k-1);
				for( i = 1; i < k-1; ++i)
				{
					dTmp = dist(P.get(i), P.get(k-1));
					
					if( dTmp < d)
						d = dTmp;
				}
				return d;
			}
		}
	}
	
	
	private double dist(Point p1, Point p2)
	{
		return Math.sqrt( (p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y));
	}
}
