
import java.util.*;

public class FloydWarshall {
    public void executar(Grafo g) {
        int V = g.getNumVertices();
        int[][] dist = new int[V][V];

  
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i != j) {
                    dist[i][j] = Integer.MAX_VALUE;
                } else {
                    dist[i][j] = 0;
                }
            }
        }

        
        for (int u = 0; u < V; u++) {
            for (int v : g.getAdjList(u)) {
                dist[u][v] = 1; 
            }
        }

        
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        
        System.out.println("Caminho Mínimo entre Todos os Pares (Floyd-Warshall):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
