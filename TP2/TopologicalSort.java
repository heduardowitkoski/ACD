
import java.util.*;

public class TopologicalSort {

    
    public List<Integer> topologicalSort(Grafo g) {
        boolean[] visited = new boolean[g.getNumVertices()];
        Stack<Integer> stack = new Stack<>();
        
       
        for (int i = 0; i < g.getNumVertices(); i++) {
            if (!visited[i]) {
                dfs(g, i, visited, stack);
            }
        }
        
        
        List<Integer> topoOrder = new ArrayList<>();
        while (!stack.isEmpty()) {
            topoOrder.add(stack.pop());
        }
        
        return topoOrder;
    }

    
    private void dfs(Grafo g, int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;

        
        for (int adj : g.getAdjList(v)) {
            if (!visited[adj]) {
                dfs(g, adj, visited, stack);
            }
        }

       
        stack.push(v);
    }

    
    public void printTopologicalOrder(List<Integer> topoOrder) {
        System.out.println("Ordenação Topológica:");
        for (int v : topoOrder) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    
}
