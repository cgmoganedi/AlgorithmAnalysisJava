//package sudoku_solution;

import java.util.Scanner;

public class Program {

	private static int [][]sudoku; 	// the sudoku grid
	
	//------------------------------------------------------------------------

	// function to check for clash on y-axis (row), x-axis (column) and sub-grid
	static boolean isValid(Point point, int v)
	{
		// position has none-zero value
		if (sudoku[point.y][point.x] != 0)
			return false;

		// if v present row, return false
		for (int c = 0; c < 9; c++)
			if (sudoku[point.y][c] == v)
				return false;

		// if v present in column, return false
		for (int r = 0; r < 9; r++)
			if (sudoku[r][point.x] == v)
				return false;

		// if v present in sub-grid, return false
		int x1 = 3 * (point.x / 3);
		int y1 = 3 * (point.y / 3);

		for (int y = y1; y < y1+3; y++)
			for (int x = x1; x < x1+3; x++)
				if (sudoku[y][x] == v)
					return false;

		// if value not present in column, row and sub-grid, return true
		return true;
	}
	//------------------------------------------------------------------------

	// function to get the next point
	static Point getNextPoint(Point cur)
	{
		int y = cur.y;
		int x = cur.x;

		// reached end of column, got to next row
		if (++x > 8)
		{
			x = 0;
			++y;
		}
		
		if (y < 9)
			return new Point(y, x);

		return null; // reached end of grid
	}
	//------------------------------------------------------------------------
	
	// must return true, if the sudoku is solved, return false otherwise
	static boolean solve(Point cur)
	{
		// if the point is null, we have reached the end
		if (cur == null)
			return true;

		// if sudoku[cur] already has a given value,
		// simply continue on to next point
		if ( sudoku[cur.y][cur.x] != 0 )
			return solve(getNextPoint(cur));

		// if sudoku[cur] doesn't have a value, try each value from 1 to 9
		for ( int i = 1; i < 10 ; i++ )
		{
			if ( !isValid(cur, i) ) // if not valid for this point, move on to next value
				continue;

			sudoku[cur.y][cur.x] = i; // assign the value i to the current position

			// continue with next point, if solved, return,
			// else move on to the next possible value
			if ( solve(getNextPoint(cur)) ) // the entire magic lies here
				return true;
			else
				sudoku[cur.y][cur.x] = 0; // go pick the next possible value
		}
		
		return false;
	}
	//------------------------------------------------------------------------

	// to print the sudoku grid
	static void printPuzzle(int sudoku[][])
	{
		for (int y = 0; y < 9; y++)
		{
			for (int x = 0; x < 9; x++)
				System.out.print(sudoku[y][x]+" ");
			
			System.out.println();
		}
	}
	//------------------------------------------------------------------------
	
	//read from std input and store in sudoku[][]
	public static boolean readPuzzle()
	{
		Scanner in = new Scanner(System.in);
		sudoku = new int[9][9];
		String []tmp;
		
		int i;
		for(i = 0; i< 9; ++i)
		{
			String row = in.nextLine();
			tmp  = row.split(" ");
			
			for(int k = 0; k < 9; ++k)
				sudoku[i][k] = Integer.parseInt(tmp[k]);
			
		}
		return true;
	}
	//------------------------------------------------------------------------

	public static void main(String[] args)
	{
		// read in the grid from std input
		if(!readPuzzle())
		{
			System.out.println("Could not read in puzzle.");
			return;
		}
		// solve the puzzle sequentially beginning at top left
		if (!solve(new Point(0, 0)))
			System.out.println("Your Sudoku can not be solved.");
		
		else
			System.out.println("\tSOLUTION:");
		
		printPuzzle(sudoku);
	}
	//------------------------------------------------------------------------

	// A static struct to store coordinates of a grid point.
	static class Point
	{
		int x, y; // the coordinates of the point in the sudoku grid

		public Point(int y, int x)
		{
			this.y = y;
			this.x = x;
		}
		public String toString()
		{
			return "("+ x + ", " + y +")";
		}
	};
}