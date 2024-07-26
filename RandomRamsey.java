import java.util.Random;
import java.util.ArrayList;


class RandomRamsey {
    final int VERTICES = 18;
    final int CLIQUE_VERTICES = 4;
    final int EDGES = (VERTICES * (VERTICES-1)) / 2;
    final int colors = 2;
    
    int store[] = new int[VERTICES];

    Random rand = new Random();
    int[][] graph = new int[VERTICES][VERTICES];
   
    // Creates an nxn edge matrix of a complete graph where vertices
    // are randomly colored red or blue (1 or 2). 
    public void GraphSim() {
        for (int vertex = 0; vertex < VERTICES; ++vertex) {
            for (int edge = 0; edge < VERTICES; ++edge) {
                if (vertex == edge) continue;
               
                int randColor = 1 + rand.nextInt(colors);
               
                graph[vertex][edge] = randColor;
                graph[edge][vertex] = randColor;
            }
        }
    }

    // Loops through all possibilities of cliques in the edge matrix
    // checking if all the edges are the same color
    public int findMonoTriangle() {
        int mono = 0;
        
        for (int i = 0; i < VERTICES; ++i) {
            for (int j = i + 1; j < VERTICES; ++j) {
                for (int k = j + 1; k < VERTICES; ++k) {
                    for (int l = k + 1; l < VERTICES; ++l) {
                        if (graph[i][j] == graph[j][k] && graph[j][k] == graph[k][l] && graph[k][l] == graph[i][l]) {
                            mono++;
                        }
                    }
                }
            }
        }

        return mono;
    }
        
    public static void main(String[] args) {
        ArrayList<Integer> data = new ArrayList<Integer>();
        
        
        RandomRamsey test = new RandomRamsey();
        int prob = 0;
        
        for (int i = 0; i < 10000; ++i) {
            test.GraphSim();
            int numCliques = test.findMonoTriangle();

            data.add(numCliques);

            if (numCliques > 0) {
                prob++;
            }
        }
        
        
        for (int i = 0; i < data.size(); ++i) {
            System.out.print(data.get(i) + ", ");
        }
        
        System.out.println();
        System.out.println("Probability: " + prob);
        
    }

// Function to check if the given set of vertices
    // in store array is a clique or not
    public boolean is_clique(int b) {
        // Run a loop for all the set of edges
        // for the select vertex
        
        for (int color = 1; color < colors;) {
            for (int i = 1; i < b; i++) 
            {
                for (int j = i + 1; j < b; j++)
         
                    // If any edge is missing
                    if (graph[store[i]][store[j]] == color)
                        return false;
            }
            return true;
        }
        
        System.out.println("dasuh");
        return true;
    }
     
    // Function to print the clique
    public void print(int n) {
        for (int i = 1; i < n; i++)
            System.out.print(store[i] + " ");
        System.out.print(", ");
    }
     
    // Function to find all the cliques of size s
    public void findCliques(int i, int l, int s) {
        // Check if any vertices from i+1 can be inserted
        for (int j = i + 1; j < VERTICES - (s - l); j++) {
            // Add the vertex to store
            store[l] = j;
 
            // If the graph is not a clique of size k
            // then it cannot be a clique
            // by adding another edge
            if (is_clique(l + 1))
 
                // If the length of the clique is
                // still less than the desired size
                if (l < s)
 
                    // Recursion to add vertices
                    findCliques(j, l + 1, s);
 
                // Size is met
                else
                    print(l + 1);   
        }
    }
    
}