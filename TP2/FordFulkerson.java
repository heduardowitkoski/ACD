

import java.util.*;

public class FordFulkerson {
    public int executar(Grafo g, int source, int sink) {
        int V = g.getNumVertices();
        int[][] flow = new int[V][V]; 

        int maxFlow = 0;
        int[] parent = new int[V];

        while (true) {
            
            Arrays.fill(parent, -1);
            Queue<Integer> queue = new LinkedList<>();
            queue.add(source);
            parent[source] = -2; 

            while (!queue.isEmpty()) {
                int u = queue.poll();

                for (int v : g.getAdjList(u)) {
                    if (parent[v] == -1 && g.getCapacity(u, v) - flow[u][v] > 0) {
                        parent[v] = u;
                        if (v == sink) break;
                        queue.add(v);
                    }
                }
            }

            if (parent[sink] == -1) break; 

            
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, g.getCapacity(u, v) - flow[u][v]);
            }

            
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                flow[u][v] += pathFlow;
                flow[v][u] -= pathFlow; 
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }
}
