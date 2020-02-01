/*
	Tiffany Wong / twong61@horizon.csueastbay.edu
	CS 413 / Fall 2019
	Dijkstra's Algorithm Homework
*/

import java.io.*;
import java.util.Scanner;

public class DijkstraExercise
{
    // Number of vertices in graph = length of the adjacency matrix.
    static void dijkstra(int[][] adjMatrix, int s)
    {
        int[] dist = new int[adjMatrix.length];             // will contain shortest distance from source to each vertex
        boolean[] visited = new boolean[adjMatrix.length];  // contains whether we have added a vertex to the shortest path tree

        for (int i = 0; i < adjMatrix.length; i++)
            dist[i] = Integer.MAX_VALUE;

        dist[s] = 0;

        for (int i = 0; i < adjMatrix.length; i++)
        {
            int u = minVertex(dist, visited);
            visited[u] = true;

            for (int v = 0; v < adjMatrix.length; v++)
            {
                if (!visited[v]                                  // v hasn't been visited
                        && adjMatrix[u][v] != 0                  // there exists an edge from u to v
                        && dist[u] + adjMatrix[u][v] < dist[v])  // dist[u] + weight of edge u, v < current dist[v]
                {
                    dist[v] = dist[u] + adjMatrix[u][v]; // shorter path found, update distance of v from source
                }
            }
        }

        System.out.println("Vertex \t Shortest Distance from Source");
        System.out.println("---------------------------------------");
        for (int i = 0; i < adjMatrix.length; i++)
            System.out.println(i + "\t\t " + dist[i] + " units");
    }

    private static int minVertex(int[] dist, boolean[] visited)
    {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < dist.length; v++)
        {
            if (!visited[v] && dist[v] < min)
            {
                min = dist[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    public static void main(String[] args)
    {
        File matrixFile = new File("D:\\Documents\\CS413\\DijkstraExercise\\src\\adjacencyMatrix.txt");
        int [][] adjacencyMatrix;

        try
        {
            Scanner countVertices = new Scanner(new BufferedReader(new FileReader(matrixFile)));
            int vertices = 0;
            while (countVertices.hasNextLine())
            {
                vertices++;
                countVertices.nextLine();
            }
            countVertices.close();

            adjacencyMatrix = new int[vertices][vertices];

            Scanner numReader = new Scanner(new BufferedReader(new FileReader(matrixFile)));
            for (int i = 0; i < vertices; i++)
            {
                String[] currLine = numReader.nextLine().split(" ");

                for (int j = 0; j < currLine.length; j++)
                {
                    int cost = Integer.parseInt(currLine[j]);
                    adjacencyMatrix[i][j] = cost;
                }
            }
            numReader.close();

            dijkstra(adjacencyMatrix, 0);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}

