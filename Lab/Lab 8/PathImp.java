import CITS2200.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Class Implementation of the Prim's and Dijkstra's algorithm for graphs
 * Prims algorithm - non - weighted weighted and undirected graph
 * Dijkstra's algorithm - non - negative weighted and directed graph
 *
 * @author Varun Jain
 */

public class PathImp<E> implements Path
{

	private class Edge implements Comparable<Edge>
	{
		int vertex, key;

		/**
		 * @param ver - holds vertex value
		 * @param pointer - holds key value
		 * getkey() - getter method for object edge
		 * getVertix() - getter method for object edge
		 */

		public Edge(int ver, int pointer)
		{
			vertex = ver;
			key = pointer;
		}

		int getKey() { return this.key; } //returns key value

		 int getVertix() { return this.vertex; } //returns vertex value

		@Override

		public int compareTo(Edge compareEdge)
		{
			return Integer.compare(this.key, compareEdge.key);
		}

	}


	/**
	 * Prim's Algorithm Referenced From: https://www.geeksforgeeks.org/greedy-algorithms-set-5-prims-minimum-spanning-tree-mst-2/
	 * Algorithm implemented for the MST to find the sum of all the edge-weight
	 * MST - weighted and undirected graph
	 * @param g - the graph
	 * @return the cumulative sum of the MST weights
	 * @return -1 if no MST exists
	 */

	public int getMinSpanningTree(Graph g)
	{
		//stores the number of vertices in the graph
		int numOfVertex = g.getNumberOfVertices();
		//check if vertiX visited or not
		boolean[] visited = new boolean[numOfVertex];
		//shortest path - the distances
		int[] distance = new int[g.getNumberOfVertices()];

		PriorityQueue<E> q = new PriorityQueue<E>(numOfVertex);

		int sum_of_weight = 0;

	 //assume only positive edges are provided
	 //set distance/weight as unknown (hence -1)
		for(int i = 0; i < numOfVertex ;i++) {
			distance[i] = -1;
			visited[i] = false;
		}

		//starting vertex: 0
		//set key value to 0
		//push/enqueue new element Edge into the priority queue
		q.add((E) new Edge(0,0));

		while(!q.isEmpty())
		{
			//pop/dequeue the lowest key value from queue
			Edge adjacent = (PathImp<E>.Edge) q.remove();

			////check if the vertex is visited or if there is a small distance value
			if(visited[adjacent.getVertix()] == false || distance[adjacent.getVertix()] > adjacent.getKey())
			{
				//update vertex to visited
				visited[adjacent.getVertix()] = true;
				//update distance if smaller value is found
				distance[adjacent.getVertix()] = adjacent.getKey();
			}

			for(int i = 0; i < numOfVertex; i++)
			{
				if(g.getWeight(i, adjacent.getVertix()) != 0 && visited[i] == false)
				{
					//adds adjacent vertex to the queue
					//start of the edge: adjacent.getVertix()
					//end of the edge: i
					//key value = the weight of the edge between adjacent.getVertix() and i
					q.add((E) new Edge(i, g.getWeight(adjacent.getVertix(), i)));
				}
			}
		}

		//if the vertex has not been visited then return the mst weight as -1
		//if the vertex has been visited in the mst graph then return the sum of the minimum weight

		for(int weights:distance)
		{
			if(visited[weights] = false)
			{
				sum_of_weight = -1;
				break;
			}
			sum_of_weight = sum_of_weight + weights;
		}
		return sum_of_weight;
	}


	/**
	 * Dijkstra Algorithm Referenced From: https://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
	 * Finds the shortest distance from a specified source
	 * @param g - the graph
	 * @param arg1 - starting vertex
	 * @return an array of the distances from the specified start vertex to each of the vertices in the graph.
	 */


	public int[] getShortestPaths(Graph g, int arg1)
	{
		int[][] edgeMatrix = g.getEdgeMatrix();
		int numOfVertex = g.getNumberOfVertices();
		boolean[] visited = new boolean[numOfVertex];
		//Adjacency matrix used to represent graphs
		//iterate over the edges of each vertex
		int[] distance = new int[numOfVertex];

		//distance initialize to infinite
		Arrays.fill(distance, Integer.MAX_VALUE);
		//Vertices not yet included in the shortest path tree
		Arrays.fill(visited, false);
    //the vertex, itself has a distance of 0
		distance[arg1] = 0;

		for(int i = 0; i < numOfVertex; i++)
		{
			int min_index = minDist(distance, visited, numOfVertex);
			//Change visit status of the vertex to true
			visited[min_index] = true;

			for(int j = 0; j < numOfVertex; j++)
			{
				//adjacent vertex has not been visited yet
				if(edgeMatrix[min_index][j] != 0 && visited[j] == false) {
					if(distance[min_index] != Integer.MAX_VALUE) {
						//adjacent vertex is greater
						if(distance[j] > distance[min_index] + edgeMatrix[min_index][j])
						{
							//update distance for adjacent vertex
							distance[j] = distance[min_index] + edgeMatrix[min_index][j];
						}
					}
				}
			}
		}

		for (int i=0; i<numOfVertex; i++) {
			//in the distance array, if there are one vertices distance = max
			//then update distance to -1
			if (distance[i] == Integer.MAX_VALUE) {
				distance[i] = -1;
			}
		}

		return distance;

	}

	//finds the minimum distance value from a pool of vertices
	int minDist(int dist[], boolean sptSet[], int V)
    {
        int min = Integer.MAX_VALUE, min_index=-1;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }
}
