import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;
import java.io.FileNotFoundException;
import java.io.FileReader; 
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Graph Representation of the Twitter Dataset
 * Implemetation of Graphs Centralities: Degree, Betweenness, Closeness, KatZ
 * 
 * @author Varun Jain 21963986
 * @author Joshua Goh 21978677
 */
public class Centrality 
{
	HashMap<Integer,Integer> mapNode = new HashMap<Integer, Integer>(); 
	HashMap<Integer,Integer> inverseMapNode = new HashMap<Integer, Integer>();
	public static int[][] graph;
	int numberOfVertices = 0;
	ArrayList<int[][]> componentsList;

	/*------------------------------------------Read the File Given By the User-----------------------------------------------*/

	/**
	 * Assign every unique node a unique index
	 * Create a 2D Symmetric Matrix containing 1's and 0's 
	 * @param file_name - user argument
	 * @return  undirected graph matrix called Graph
	 */
	
	public int[][] filename(String file_name) 
	{
		Integer vertex1; 
		Integer vertex2;
		try(Scanner sc = new Scanner(new FileReader(file_name)))
		{
			while(sc.hasNext())
			{
				vertex1 = sc.nextInt();
				vertex2 = sc.nextInt(); 
				if(!mapNode.containsKey(vertex1))
				{
					mapNode.put(vertex1, numberOfVertices);
					inverseMapNode.put(numberOfVertices, vertex1); 
					numberOfVertices++; 
				}
				
				if(!mapNode.containsKey(vertex2))
				{
					mapNode.put(vertex2, numberOfVertices);
					inverseMapNode.put(numberOfVertices, vertex2);
					numberOfVertices++; 
				} 
			}	
		}
		catch (FileNotFoundException e) 
		{ 
			e.printStackTrace();
		}

/*-----------------------------------------Undirected Adjacency Matrix Created-----------------------------------------*/


		graph = new int[numberOfVertices][numberOfVertices];
			int node1, node2; 
			try(Scanner sc2 = new Scanner(new FileReader(file_name)))
			{
				while(sc2.hasNext())
				{
					node1 = mapNode.get(sc2.nextInt());
					node2 = mapNode.get(sc2.nextInt());
					graph[node1][node2] = 1;
					graph[node2][node1] = 1; 
				} sc2.close(); 
			} catch(FileNotFoundException e) { e.printStackTrace(); }
			
			return graph;
	}
	
/*----------------------------------------------------------Splitting components-------------------------------------------------------------*/	
	
	public void components() {
		HashMap<Integer, Boolean> visited = new HashMap<>();
		for(int v : mapNode.keySet()) {
			visited.put(v, false);
		}
		
		componentsList=new ArrayList<>();
		for(int v : mapNode.keySet()) {
			if(visited.get(v)==false) {
				ArrayList<Integer> component = new ArrayList<>();
				DFS(v,visited,component);
				System.out.println(component);
				
			}
		}
	}
	
	public void DFS(int v, HashMap<Integer, Boolean> visited, ArrayList<Integer> component) {
		visited.put(v, true);
		component.add(v);
		for(int i=0; i<numberOfVertices; i++) {
			int nodeExists = graph[mapNode.get(v)][i];
			if(nodeExists==1) {
				int node=inverseMapNode.get(i);
				if(!(visited.get(node))) {
					DFS(node,visited,component);
				}
			}
		}
	}
	
	
/*------------------------------------------------------------Degree Centrality ---------------------------------------------------------------------*/
	/**
	 * @returns the Node ID's with its degree centrality
	 */
	public int[][] degreecentrality()
	{
		int[][] vertices = new int[numberOfVertices][2]; 
		for(int row = 0; row < numberOfVertices; row++)
		{
			int count = 0; 
			for(int column = 0; column < numberOfVertices; column++)
			{
				if(graph[row][column] > 0) count++; 
			}	
		vertices[row][0] = count;
		vertices[row][1] = inverseMapNode.get(row); 
		}
		return vertices;
	}
	
/*----------------------------------------------------------Sort Degree Centrality--------------------------------------------------------------------*/
	/**
	 * Sorts array of degree centralities from highest to lowest
	 */
	public int[][] degree_sort(int[][] vertices)
	{
		Arrays.sort(vertices, java.util.Comparator.<int[]>comparingInt(a -> a[0]).thenComparingInt(a -> a[0]).reversed());
		return vertices;
	}
	
/*--------------------------------------------------------Sort Closeness Centrality-----------------------------------------------------------------*/
	/**
	 * Sorts array of closeness centralities from highest to lowest
	 */
	public double[][] closeness_sort(double[][] nodes)
	{
		Arrays.sort(nodes, java.util.Comparator.<double[]>comparingDouble(a -> a[0]).thenComparingDouble(a -> a[0]).reversed());
		return nodes;
	}

/*--------------------------------------------------------Sort Betweeness Centrality-----------------------------------------------------------------*/
	/**
	 * Sorts array of betweeness centralities from highest to lowest
	 */
	public float[][] betweeness_sort(float[][] nodes)
	{
		Arrays.sort(nodes, java.util.Comparator.<float[]>comparingDouble(a -> a[0]).thenComparingDouble(a -> a[0]).reversed());
		return nodes;
	}	
	
/*-----------------------------------------------------------Closeness Centrality -----------------------------------------------------------------*/
	/**
	 * @returns closeness centrality of each node
	 */
	public int[] BFS(int startVertex) {
	
		int[] distance = new int[numberOfVertices];
		boolean[] visited = new boolean[numberOfVertices];
		Queue<Integer> q = new LinkedList<Integer>();
		
		
		Arrays.fill(visited, false);
		Arrays.fill(distance, Integer.MAX_VALUE);
		

		distance[startVertex] = 0;
		visited[startVertex] = true;
		
		q.add(startVertex);
		while(!q.isEmpty()) {
			int w = q.remove();
			for(int i=0; i<numberOfVertices; i++) {
				if(visited[i] == false && graph[w][i] == 1) {
					distance[i] = distance[w] + 1;
					visited[i] = true;
					q.add(i);
				}
			}
			visited[w] = true;
		}
		
		return distance;
	}
	
/*--------------------------------------------------------distance Closeness Centrality-----------------------------------------------------------------*/
	/**
	 * Sums values in distance array
	 */
	public double distance(int[] distance)
	{
		int sum = 0;
		
		for(int i = 0; i < distance.length; i++)
		{
			if(distance[i] > 0) 
				sum += distance[i]; 
		}
		return sum; 
	}
	
/*--------------------------------------------------------Store Closeness Centrality-----------------------------------------------------------------*/
	/**
	 * Stores inverse of sum in second column of array
	 * Stores nodes in first column of array
	 */
	public double[][] closeness()
	{
		
		double[][] store = new double[numberOfVertices][2];
		for(int i = 0; i < numberOfVertices; i++)
		{
			store[i][0] = Math.pow(distance(BFS(i)),-1);
			store[i][1] = inverseMapNode.get(i); 
		}
		return store;
	}
	
	


	/*----------------------------------------------------Betweeness Centrality -----------------------------------------------------------------*/
	/**
	 * @returns betweeness centrality of each node
	 */
	public float[] betweeness()
	{
		float[] betweenessCentrality = new float[numberOfVertices];
		//stores the shortest path 
		ArrayList nodeList[] = new ArrayList[numberOfVertices];
		
		//s -> starting node
		for(int s = 0; s < numberOfVertices; s++)
		{
			//intalise empty stack
			Stack<Integer> stack = new Stack<Integer>();
			
			int distances[] = new int[numberOfVertices];	Arrays.fill(distances, -1);
			distances[s] = 0;
			
			float[] sigma = new float[numberOfVertices];	Arrays.fill(sigma, 0);
			sigma[s] = 1;
			
			float[] delta = new float[numberOfVertices];	Arrays.fill(delta, 0);
			
			//intialise an empty queue
			 Queue<Integer> queue = new LinkedList<>();
			 
			 for(int i = 0 ; i <  numberOfVertices; i++)
			 {
				 nodeList[i] = new ArrayList();
			 }
			 
			 //add vertex s to the queue
			 queue.add(s);	 
			 
			 int currentVertex; 
			 while(!queue.isEmpty())
			 {
				 //remove the vertex from the queue
				 currentVertex = queue.remove();
				 //push vertex in the stcak 
				 stack.push(currentVertex); 
				 
				 for(int w = 0; w < numberOfVertices; w++)
				 {
					 //check if connection between currentVertex and w exist 
					 if(graph[currentVertex][w] == 1)
					 {
						 if(distances[w] < 0)
						 {
							 queue.add(w);
		                     distances[w] = distances[currentVertex] + 1;
						 }
						   if(distances[w] == distances[currentVertex] + 1)
						   {
							   sigma[w] += sigma[currentVertex];
		                       nodeList[w].add(currentVertex);
						   }
					 }
				 }
			 }
			 //vertices in the stack 
			 while(!stack.isEmpty())
			 {
				 //pop the topmost vertix from the stack 
				 currentVertex = stack.pop();
				 Iterator<Integer> pathIterator = nodeList[currentVertex].iterator();
				 
				 int path; 
				 //loop until the end of iterator 
			       while(pathIterator.hasNext())
		            {
		                path = pathIterator.next();
		                delta[path] = delta[path] + (sigma[path]/sigma[currentVertex])*(delta[currentVertex]+1);
		            }
			       //check wheather currentVertex is not equal to start vertex
			       if(currentVertex != s)
			       {
			    	   betweenessCentrality[currentVertex] = betweenessCentrality[currentVertex] + delta[currentVertex];
			       }
			 }
		}
			return betweenessCentrality;
	}
	
/*----------------------------------------------------Store Betweeness Centrality -----------------------------------------------------------------*/
	/**
	 * Stores betweeness centrality in first column of array
	 * Stores node in second column of array
	 */
	public float[][] Betweeness_Centrality()
	{
		float[][] store = new float[numberOfVertices][2];
		float[] df = betweeness();
		for(int i = 0; i < numberOfVertices; i++)
		{
			store[i][0] = df[i];
			store[i][1] = inverseMapNode.get(i); 
		}
	
		return store;
	}

	
/*---------------------------------------------------------Katz Centrality -------------------------------------------------------------------------*/
	/**
	 * @returns Katz centrality for each node
	 */
	public int Katz(int startVertex) {
		
		int[] distance = new int[numberOfVertices];
		boolean[] visited = new boolean[numberOfVertices];
		Queue<Integer> q = new LinkedList<Integer>();
		
		
		Arrays.fill(visited, false);
		Arrays.fill(distance, Integer.MAX_VALUE);
		

		distance[startVertex] = 0;
		visited[startVertex] = true;
		
		q.add(startVertex);
		while(!q.isEmpty()) {
			int w = q.remove();
			for(int i=0; i<numberOfVertices; i++) {
				if(visited[i] == false && graph[w][i] == 1) {
					distance[i] = distance[w] + 1;
					visited[i] = true;
					q.add(i);
				}
			}
			visited[w] = true;
		}
		int sum = 0; 
		
		//gets the degree centrality - number of connections 
		for(int i = 0; i < numberOfVertices; i++)
		{
			int degreeCentrality = 0;
			for(int j = 0; j < numberOfVertices; j++)
			{
				if(graph[i][j] != 0)
					degreeCentrality++;
			}
			sum += degreeCentrality * Math.pow(0.5, distance[i]);
		}
		return sum;
	}
	
/*---------------------------------------------------------Store Katz Centrality -------------------------------------------------------------------------*/
	/**
	 * Stores Katz centrality in first column of array
	 * Stores node in second column of array
	 */
	public double[][] KatZ_Centrality()
	{
		double[][] store = new double[numberOfVertices][2];
		for(int i = 0; i < numberOfVertices; i++)
		{
			store[i][0] = Katz(i);
			store[i][1] = inverseMapNode.get(i); 
		}
		closeness_sort(store);
		return store;
	}

}

