

import java.util.*;

public class Kruskal {


    public static int find(int[] parent, int u) {
        if (parent[u] != u) {
            parent[u] = find(parent, parent[u]);  
        }
        return parent[u];
    }


    public static void union(int[] parent, int[] rank, int u, int v) {
        int rootU = find(parent, u);
        int rootV = find(parent, v);

        if (rootU != rootV) {
            if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
        }
    }

    
    public static List<String> calcularMST(Grafo grafo) {
        List<Grafo.Edge> edges = grafo.getEdges();

     
        edges.sort(Comparator.comparingInt(e -> e.weight));

      
        int[] parent = new int[grafo.getNumVertices()];
        int[] rank = new int[grafo.getNumVertices()];

        
        for (int i = 0; i < grafo.getNumVertices(); i++) {
            parent[i] = i;
            rank[i] = 0;
        }

      
        List<String> mst = new ArrayList<>();

       
        for (Grafo.Edge edge : edges) {
            int u = edge.u;
            int v = edge.v;

            
            if (find(parent, u) != find(parent, v)) {
                union(parent, rank, u, v);
                mst.add(u + "-" + v);  
            }

            
            if (mst.size() == grafo.getNumVertices() - 1) {
                break;
            }
        }

        return mst;
    }

    
    
}
