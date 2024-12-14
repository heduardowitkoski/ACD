import java.util.*;

public class Grafo {
    private int numVertices;
    private Map<Integer, List<Integer>> adjList;
    private Map<String, Integer> capacities;
    private boolean isDirected; 

    public Grafo(int numVertices, boolean isDirected) {
        this.numVertices = numVertices;
        this.isDirected = isDirected;
        this.adjList = new HashMap<>();
        this.capacities = new HashMap<>();

        for (int i = 0; i < numVertices; i++) {
            adjList.put(i, new ArrayList<>());
        }
    }

    
    public void addEdge(int u, int v, int peso) {
        adjList.get(u).add(v);
        capacities.put(u + "-" + v, peso);

        if (!isDirected) { 
            adjList.get(v).add(u);
            capacities.put(v + "-" + u, peso);
        }
    }

 
    public List<Integer> getAdjList(int u) {
        return adjList.get(u);
    }

    
    public int getCapacity(int u, int v) {
        return capacities.getOrDefault(u + "-" + v, 0);
    }

    
    public void setCapacity(int u, int v, int capacity) {
        capacities.put(u + "-" + v, capacity);

        if (!isDirected) { 
            capacities.put(v + "-" + u, capacity);
        }
    }

    public int getNumVertices() {
        return numVertices;
    }


    public int getPeso(int u, int v) {
        return capacities.getOrDefault(u + "-" + v, Integer.MAX_VALUE);
    }

 
    public void printGrafo() {
        System.out.println("Estrutura do Grafo:");
        for (int u = 0; u < numVertices; u++) {
            System.out.print(u + " -> ");
            for (int v : adjList.get(u)) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }


    public void printGrafoPonderado() {
        System.out.println("Estrutura do Grafo (Ponderado):");
        for (int u = 0; u < numVertices; u++) {
            System.out.print(u + " -> ");
            for (int v : adjList.get(u)) {
                int peso = capacities.getOrDefault(u + "-" + v, 0);
                System.out.print(v + "(" + peso + ") ");
            }
            System.out.println();
        }
    }

    
    public List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : capacities.entrySet()) {
            String[] vertices = entry.getKey().split("-");
            int u = Integer.parseInt(vertices[0]);
            int v = Integer.parseInt(vertices[1]);
            int weight = entry.getValue();

          
            if (isDirected || u < v) {
                edges.add(new Edge(u, v, weight));
            }
        }
        return edges;
    }

    
    public static class Edge {
        int u, v, weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }
}
