package lab4_closest_pair;

public class Point implements Comparable<Point>
{
	
	public int x;
	public int y;
	
	Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void print()
	{
		System.out.println("("+x+","+y+")");
	}

	@Override
	public int compareTo(Point p)
	{
		/* return = 0 if they are equal
		 * return < 0 if this.x < p.x
		 * return > 0 if this.x > p.x
		 */
		return this.x - p.x;
	}

}
