package com.company;

/**
 * Static class that prints the shortest paths from a source vertex
 * to all other vertices of a graph using the Bellman-Ford algorithm.
 *
 * @author Raz Consta
 */
class Main {

    /**
     * Driver method of the class.
     *
     * @param args (String[]) command line arguments
     */
    public static void main(String[] args) {

        /*
         - 2D array storing the graph represented as edges
         - edge stored as starting vertex, terminal vertex, weight
         - first edge is a dummy edge
         */
        int graph[][] = { { 0, 0,  0},
                          { 0, 1, -1 },
                          { 0, 2,  4 },
                          { 1, 2,  3 },
                          { 1, 3,  2 },
                          { 1, 4,  2 },
                          { 3, 2,  5 },
                          { 3, 1,  1 },
                          { 4, 3, -3 } };

        final int VERTICES = 5;
        final int EDGES = 8;
        final int SOURCE = 0;
        BellmanFord(graph, VERTICES, EDGES, SOURCE);
    }

    /**
     * Implementation of Bellman-Ford algorithm that prints the shortest
     * path from a source vertex to all other vertices of a graph.
     *
     * Additionally, it can detect whether the graph contains
     * a negative cycle or not.
     *
     * @param theGraph      (int[][]) the edges of the graph
     * @param theVertices   (int) the number of vertices in the graph
     * @param theEdges      (int) the number of edges in the graph
     * @param theSource     (int) the source vertex
     */
    public static void BellmanFord(int theGraph[][],
                                   int theVertices, int theEdges,
                                   int theSource) {

        // declare the 1D array which stores the shortest paths
        int[] solution = new int[theVertices];

        // initialize the solution array to large values
        for (int i = 0; i < theVertices; i++) {
            solution[i] = Integer.MAX_VALUE;
        }

        // initialize distance from source to itself as 0
        solution[theSource] = 0;

        // iterate |V| - 1 times
        for (int i = 1; i <= theVertices - 1; i++) {
            // iterate |E| times
            for (int j = 1; j <= theEdges; j++) {

                // Storing two minimum weights and one edge weight.
                int u = solution[theGraph[j][0]]; // min to initial vertex
                int v = solution[theGraph[j][1]]; // min to terminal vertex
                int weight = theGraph[j][2]; // weight of edge (u, v)

                if (u != Integer.MAX_VALUE && u + theGraph[j][2] < v) {
                    solution[theGraph[j][1]] = u + weight;
                }
            }
        }

        System.out.println("\nVertex \t Distance from vertex " + theSource);
        for (int i = 0; i < theVertices; i++) {
            if (i != theSource) {
                if (solution[i] < 0) {
                    System.out.println("  " + i + "\t\t\t" + solution[i]);
                } else {
                    System.out.println("  " + i + "\t\t\t " + solution[i]);
                }
            }
        }

        boolean negativeCycle = false;

        /*
         If we perform a |V|-th edge relaxation and we find
         a shorter path that means there is a negative cycle.
         */
        for (int i = 1; i <= theEdges; i++) {

            // Storing all of the information about edge i.
            int u = theGraph[i][0]; // initial vertex
            int v = theGraph[i][1]; // terminal vertex
            int weight = theGraph[i][2]; // weight of edge (u, v)

            if (solution[u] != Integer.MAX_VALUE
                && solution[u] + weight < solution[v]) {
                    negativeCycle = true;
                    break;
            }
        }

        if (negativeCycle) {
            System.out.println("Note: Negative cycle detected.");
        } else {
            System.out.println("Note: No negative cycle detected.");
        }
    }
}

