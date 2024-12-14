

import java.util.*;

public class BFS {
    public void executar(Grafo g, int start) {
        boolean[] visited = new boolean[g.getNumVertices()];
        LinkedList<Integer> queue = new LinkedList<>();
        
        visited[start] = true;
        queue.add(start);
        
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");
            
            for (int adj : g.getAdjList(vertex)) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    queue.add(adj);
                }
            }
        }
    }
}