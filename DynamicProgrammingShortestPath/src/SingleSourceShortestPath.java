import java.util.LinkedList;

public class SingleSourceShortestPath
{
	static int [] D;		 	// to store the distance of the shortest path from each vertex to the destination vertex
	static int [][] W;			// adjacency matrix to store the distances between two vertices that are adjacent.
	
	static int source;
	static int dest;
	
	static LinkedList<Edge> []adjList;
	
	
	public static void main(String[] args)
	{
		WeightedDirectedGraph graph = new WeightedDirectedGraph(8);
		
		graph.addEdge(1, 3, 5);
		graph.addEdge(1, 4, 3);
		graph.addEdge(1, 7, 14);
		
		graph.addEdge(3, 5, 3);
		graph.addEdge(3, 6, 2);
		
		graph.addEdge(4, 3, 11);
		graph.addEdge(4, 5, 7);
		graph.addEdge(4, 7, 6);
		
		graph.addEdge(7, 5, 7);
		graph.addEdge(7, 2, 16);
		
		graph.addEdge(5, 2, 5);
		
		graph.addEdge(6, 2, 7);
		
		graph.printGraph();
		
		source = 1;
		dest = 2;
		
		adjList = graph.getAdjList();
		
		calculateIt(8);
	}

	
	public static void calculateIt(int numOfNodes) 
	{
		D = new int[numOfNodes];
		W = new int[numOfNodes][numOfNodes];
		
		for(int d : D)
		{
			d = Integer.MAX_VALUE;
		}
		
		D[dest] = 0;
		
		for(int row = 0; row < numOfNodes; ++row)
		{
			LinkedList<Edge> listOfEdges = adjList[row];
			
			
			for(int col = 0; col < numOfNodes; ++col)
			{
				/*//if edge i to j exist
				//then assign its weight
				if(edge.destination == col)
				{
					
				}
				//else assign that weight i to j as infinite
				else
				{
					W[row][col] = Integer.MAX_VALUE;
				}*/
			}
		}
	}

}
