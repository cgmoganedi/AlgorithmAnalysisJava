package sudoku_solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
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
	public static boolean readPuzzle(String asbolutePath)
	{
		try
		{
			File file = new File(asbolutePath);
			Scanner in = new Scanner(file);
			String []tmp;
			
			int i;
			for(i = 0; i< 9; ++i)
			{
				String row = in.nextLine();
				tmp  = row.split(" ");
				
				for(int k = 0; k < 9; ++k)
				{
					sudoku[i][k] = Integer.parseInt(tmp[k]);
				}
			}
		
		}
		catch (FileNotFoundException e)
		{
	        e.printStackTrace();
	    }
		return true;
	}
	//------------------------------------------------------------------------
	
	// append the time measured to the csv file
	public static void appendToCSV(String path, long time) throws IOException
	{
		FileWriter writer = new FileWriter(path, true);
		System.out.println(time);
		writer.append(String.valueOf(time));
		writer.append('\n');
		writer.flush();
		writer.close();
		
		if(path.contains("17"))
			easy17Times.push(time);
		else if(path.contains("22"))
			easy22Times.push(time);
		else if(path.contains("27"))
			easy27Times.push(time);
		else if(path.contains("32"))
			easy32Times.push(time);
		else if(path.contains("37"))
			easy37Times.push(time);
		
	}
	//------------------------------------------------------------------------
	public static void readFromDirectory(final File directory)
	{
		for(final File directoryEntry:directory.listFiles())
		{
			if(directoryEntry.isDirectory())
				readFromDirectory(directoryEntry);
			
			else if (directoryEntry.isFile())
				readSolveTime(directoryEntry.getAbsolutePath(), directoryEntry.getParentFile().getName());

			else
				continue;
		}
	}
	//------------------------------------------------------------------------

	public static void readSolveTime(String absolutePath, String parentPath)
	{
		// read in the grid from std input
		if(!readPuzzle(absolutePath))
		{
			System.out.println("Could not read in puzzle.");
			return;
		}
		
		// begin timing here
		long startTime = System.nanoTime();

		// solve the puzzle sequentially beginning at top left
		if (!solve(new Point(0, 0)))
			System.out.println("Your Sudoku can not be solved.");
		else
			System.out.println("\tSOLUTION:");
		
		// stop timing here
		long endTime = System.nanoTime();
		
		// append the time measured to the csv file
		try {
			appendToCSV("C:\\Users\\716705\\Documents\\GitHub\\SudokuCode\\sudoku_solution\\output\\"+parentPath +"_times.csv", endTime - startTime);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		// print the puzzle solution to the std console.
		printPuzzle(sudoku);
	}

	public static void main(String[] args)
	{
		sudoku = new int[9][9];
		
		final File inputDirectory = new File("C:\\Users\\716705\\Documents\\GitHub\\SudokuCode\\sudoku_solution\\input");
		
		readFromDirectory(inputDirectory);
		
		long ave = 0;
		for(long a: easy17Times) {
			ave += a;
		}
		System.out.println("easy17Times: "+ave/50);
		
		ave = 0;
		for(long a: easy22Times) {
			ave += a;
		}
		System.out.println("easy22Times: "+ave/50);
		
		ave = 0;
		for(long a: easy27Times) {
			ave += a;
		}
		System.out.println("easy27Times: "+ave/50);
		
		ave = 0;
		for(long a: easy32Times) {
			ave += a;
		}
		System.out.println("easy32Times: "+ave/50);
		
		ave = 0;
		for(long a: easy37Times) {
			ave += a;
		}
		System.out.println("easy37Times: "+ave/50);
	}
	static LinkedList<Long> easy17Times = new LinkedList<Long>();
	static LinkedList<Long> easy22Times = new LinkedList<Long>();
	static LinkedList<Long> easy27Times = new LinkedList<Long>();
	static LinkedList<Long> easy32Times = new LinkedList<Long>();
	static LinkedList<Long> easy37Times = new LinkedList<Long>();

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
	//-----------------------------------------------------------------------
	
	//function for timing
	static long measureTime()
	{
		long startTime = System.nanoTime();
		//methodToTime();
		long endTime = System.nanoTime();

		return (endTime - startTime);  //divide by 1000000 to get milliseconds.
	}
}