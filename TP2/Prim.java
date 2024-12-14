
import java.util.*;

public class Prim {
    public void executar(Grafo g, int start) {
        int V = g.getNumVertices();
        boolean[] inMST = new boolean[V];
        int[] chave = new int[V]; 
        int[] parent = new int[V]; 
        Arrays.fill(chave, Integer.MAX_VALUE); 
        Arrays.fill(parent, -1); 
        chave[start] = 0;

        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(u -> chave[u]));
        pq.add(start);

        while (!pq.isEmpty()) {
            int u = pq.poll(); 
            inMST[u] = true; 

            for (int v : g.getAdjList(u)) {
                int peso = g.getCapacity(u, v); 
                
                if (!inMST[v] && peso < chave[v]) {
                    chave[v] = peso;
                    parent[v] = u; 
                    pq.add(v); 
                }
            }
        }

        
        
System.out.println("Árvore Geradora Mínima (Prim) a partir do vértice " + start + ":");
for (int i = 0; i < V; i++) {
    if (parent[i] != -1) {
        int peso = g.getCapacity(parent[i], i); 
        System.out.println(parent[i] + " - " + i + " (" + peso + ")");
    }
}

    }
}
