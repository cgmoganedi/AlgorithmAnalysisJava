package lab4_closest_pair;

import java.util.ArrayList;

public class Program {
	
	public static void main(String []args)
	{
		Question1 q1 = new Question1();
		ArrayList<Point> P = new ArrayList<>();
		
		for (int i=0 ; i < 10; ++i)
		{
			P.add(new Point((int)(Math.random()*20), (int)(Math.random()*20)));
		}
		
		for(Point p: P)
			p.print();
		
		System.out.println("Simple closest: "+q1.simpleClosestPair(P));
		System.out.println("Recursive closest: "+q1.recursiveClosestPair(P, 10));
	}
}
