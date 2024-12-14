
import java.util.*;

public class BellmanFord {
    public void executar(Grafo g, int source) {
        int V = g.getNumVertices();
        int[] distancia = new int[V];
        Arrays.fill(distancia, Integer.MAX_VALUE);
        distancia[source] = 0;

        
        for (int i = 0; i < V - 1; i++) {
            for (int u = 0; u < V; u++) {
                for (int v : g.getAdjList(u)) {
                    int peso = g.getCapacity(u, v);
                    if (distancia[u] != Integer.MAX_VALUE && distancia[u] + peso < distancia[v]) {
                        distancia[v] = distancia[u] + peso;
                    }
                }
            }
        }

        
        boolean cicloNegativo = false;
        for (int u = 0; u < V; u++) {
            for (int v : g.getAdjList(u)) {
                int peso = g.getCapacity(u, v);
                if (distancia[u] != Integer.MAX_VALUE && distancia[u] + peso < distancia[v]) {
                    cicloNegativo = true;
                    break;
                }
            }
            if (cicloNegativo) break;
        }

        
        if (cicloNegativo) {
            System.out.println("O grafo contém ciclo negativo!");
        } else {
            
            System.out.println("Distâncias mínimas a partir do vértice " + source + ":");
            for (int i = 0; i < V; i++) {
                System.out.println("Vértice " + source + " -> Vértice " + i + ": " +
                        (distancia[i] == Integer.MAX_VALUE ? "Infinito" : distancia[i]));
            }
        }
    }
}
