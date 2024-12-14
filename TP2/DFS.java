
import java.util.*;

public class DFS {
    public void executar(Grafo g, int start) {
        boolean[] visited = new boolean[g.getNumVertices()];
        dfsUtil(g, start, visited);
    }

    private void dfsUtil(Grafo g, int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");
        
        for (int adj : g.getAdjList(v)) {
            if (!visited[adj]) {
                dfsUtil(g, adj, visited);
            }
        }
    }
}