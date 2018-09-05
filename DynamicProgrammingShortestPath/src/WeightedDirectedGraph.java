import java.util.LinkedList;

public class WeightedDirectedGraph {
	
	private int vertices; // number of vertices in the graph
	private LinkedList<Edge> []adjList; // the list of adjacent nodes of the graph
	// static class to be used to make each edge
	

	// Graph constructor
	WeightedDirectedGraph(int vertices)
	{
		this.vertices = vertices;
		this.adjList = new LinkedList[vertices];
		
		//initialize the adjacency lists for all the vertices
		 for (int i = 0; i <vertices ; i++) {
			 adjList[i] = new LinkedList<>();
         }
	}
	
	// build graph by adding edges
	public void addEdge(int source, int destination, int weight)
	{
		Edge edge = new Edge(source, destination, weight);
		adjList[source].addFirst(edge); // since well this is a directed graph
		//System.out.println(adjList[ source].getClass().toString());
	}
	
	public LinkedList<Edge>[] getAdjList()
	{
		return this.adjList;
	}
	
	public void printGraph()
	{
		for(int i = 0; i <vertices;  ++i)
		{
			LinkedList<Edge> list = adjList[i];
			for(Edge edge: list)
			{
				System.out.println("from " + i + " to " +
						edge.destination + " costs "+ edge.weight);
			}
		}
	}
	

}
