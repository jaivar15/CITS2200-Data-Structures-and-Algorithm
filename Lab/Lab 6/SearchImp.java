import java.util.LinkedList;
import java.util.Queue; 
import java.util.Arrays; 

import CITS2200.*;

/**
 * Class implements interface Search
 * @author Varun Jain 
 * SearchImp will search in the graph
 * find parent vertices and distances of each vertex 
 * References: CITS2200 Lecture Slides from Topic 12 
 */
public class SearchImp implements Search
{
	//linked version of queue 
	private java.util.Queue<Integer> queue = new LinkedList<Integer>();

	boolean[] visited;
	
	/**
	 * runs a breath search graph 
	 * Method return all parent vertex for each vertex in the graph 
	 * @param graph- graph to be searched
	 * @param arg1- starting vertex
	 * @return an array of integers listing the parent 
	 * vertex for each vertex or -1 if there is no parent
	 * 
	 */
	
	public int[] getConnectedTree(Graph graph, int arg1)
	{
		//return the number of vertices 
		int num_verticies = graph.getNumberOfVertices(); 
		
		//Initiate a parent array
		int[] parent = new int[num_verticies]; 

		visited = new boolean[num_verticies];
		
		//fill up the parent array with -1 
		// -1 indicating no parent found
		Arrays.fill(parent, -1); 
		
		//add starting vertex to the queue 
		queue.add(arg1); 
	
		visited[arg1] = true;
		
	    int head; 
		while(!queue.isEmpty())
		{
			head = queue.poll(); 
			for(int i = 0; i < parent.length; i++)
			{
		
				if(graph.getWeight(head, i) != 0 && visited[i]==false)
				{
					queue.add(i); 
					parent[i] = head;
					visited[i] = true;
				}
			}
			visited[head] = true;
		}
		return parent;
	}
	
	
	/**
	 * runs a breath search graph 
	 * Method finds the distances of each vertex
	 * @param graph- graph to be searched
	 * @param arg1- starting vertex
	 * @return an array of integers listing the distance of each vertex  
	 * from the starting vertex or -1 if not reachable 
	 */
	
	public int[] getDistances(Graph graph, int arg1) 
	{
		int num_verticies = graph.getNumberOfVertices(); 
		

		visited = new boolean[num_verticies];
		
		int[] distance = new int[num_verticies]; 
		
		for(int i = 0; i < num_verticies; i++) distance[i] = -1; 
		
		distance[arg1] = 0; 
		
		queue.add(arg1);
		visited[arg1] = true;
		
		int head; 
		while(!queue.isEmpty())
		{
			head = queue.poll(); 
			for(int i = 0; i < distance.length; i++)
			{
				if(graph.getWeight(head, i) != 0 && visited[i]==false)
				{
					queue.add(i); 
					distance[i] = distance[head] + 1; 
					visited[i] = true;
				}
			}
			visited[head] = true;
		}
		return distance; 
	}	

	@Override
	public int[][] getTimes(Graph arg0, int arg1) {
		
		return null;
	}

}
