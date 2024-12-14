
import java.util.*;

public class Dijkstra {
    public void executar(Grafo g, int src) {
        int V = g.getNumVertices();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);  
        dist[src] = 0;

        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(u -> dist[u]));
        pq.add(src);

        while (!pq.isEmpty()) {
            int u = pq.poll();  

            
            for (int v : g.getAdjList(u)) {
                int weight = g.getPeso(u, v);  

              
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(v);  
                }
            }
        }

     
        for (int i = 0; i < V; i++) {
            System.out.println("Distância de " + src + " para " + i + " é " + dist[i]);
        }
    }
}
