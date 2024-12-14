
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
        while (true) {
            // Exibe o menu
            System.out.println("\n===== MENU =====");
            System.out.println("1. BFS e DFS");
            System.out.println("2. Dijkstra");
            System.out.println("3. Floyd-Warshall");
            System.out.println("4. Ford-Fulkerson");
            System.out.println("5. Prim");
            System.out.println("6. Bellman-Ford");
            System.out.println("7. Kruskal");
            System.out.println("8. Ordenação Topológica");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            
            if (opcao == 9) {
                System.out.println("Saindo...");
                break;
            }
            
            long inicio, fim, tempoExecucao;
            
            switch (opcao) {
                case 1:
                    // Submenu para escolher o tipo de grafo
                    System.out.println("\n==== Escolha o Tipo de Grafo ====");
                    System.out.println("1. Grafo Não Dirigido e Não Ponderado");
                    System.out.println("2. Grafo Dirigido e Não Ponderado");
                    System.out.print("Escolha uma opção: ");
                    int tipoGrafo = scanner.nextInt();
            
                    Grafo grafoSelecionado;
            
                    if (tipoGrafo == 1) {
                        // Grafo Não Dirigido
                        System.out.println("==== Teste com Grafo Não Dirigido ====");
                        grafoSelecionado = new Grafo(10, false);
                        grafoSelecionado.addEdge(0, 1, 1);
                        grafoSelecionado.addEdge(0, 2, 1);
                        grafoSelecionado.addEdge(1, 3, 1);
                        grafoSelecionado.addEdge(1, 4, 1);
                        grafoSelecionado.addEdge(2, 3, 1);
                        grafoSelecionado.addEdge(3, 5, 1);
                        grafoSelecionado.addEdge(3, 6, 1);
                        grafoSelecionado.addEdge(4, 7, 1);
                        grafoSelecionado.addEdge(5, 8, 1);
                        grafoSelecionado.addEdge(6, 9, 1);
                        grafoSelecionado.addEdge(7, 8, 1);
                        grafoSelecionado.addEdge(8, 9, 1);
                    } else if (tipoGrafo == 2) {
                        // Grafo Dirigido
                        System.out.println("==== Teste com Grafo Dirigido ====");
                        grafoSelecionado = new Grafo(10, true);
                        grafoSelecionado.addEdge(0, 1, 1);
                        grafoSelecionado.addEdge(0, 2, 1);
                        grafoSelecionado.addEdge(1, 3, 1);
                        grafoSelecionado.addEdge(2, 4, 1);
                        grafoSelecionado.addEdge(3, 5, 1);
                        grafoSelecionado.addEdge(4, 6, 1);
                        grafoSelecionado.addEdge(5, 7, 1);
                        grafoSelecionado.addEdge(6, 8, 1);
                        grafoSelecionado.addEdge(7, 9, 1);
                    } else {
                        System.out.println("Opção inválida. Retornando ao menu principal.");
                        break;
                    }
            
                    grafoSelecionado.printGrafo();
            
                    // Executar BFS
                    System.out.println("\nBFS");
                    inicio = System.nanoTime();
                    BFS bfs = new BFS();
                    bfs.executar(grafoSelecionado, 0);
                    fim = System.nanoTime();
                    tempoExecucao = fim - inicio;
                    System.out.println("Tempo de execução do BFS: " + tempoExecucao + " nanosegundos");
            
                    // Executar DFS
                    System.out.println("\nDFS");
                    inicio = System.nanoTime();
                    DFS dfs = new DFS();
                    dfs.executar(grafoSelecionado, 0);
                    fim = System.nanoTime();
                    tempoExecucao = fim - inicio;
                    System.out.println("Tempo de execução do DFS: " + tempoExecucao + " nanosegundos");
                    break;
            
                case 2:
                    // Submenu para escolher o tipo de grafo
                    System.out.println("\n==== Escolha o Tipo de Grafo ====");
                    System.out.println("1. Grafo Não Dirigido e Ponderado");
                    System.out.println("2. Grafo Dirigido e Ponderado");
                    System.out.print("Escolha uma opção: ");
                    int tipoGrafoDijkstra = scanner.nextInt();
            
                    Grafo grafoDijkstra;
            
                    if (tipoGrafoDijkstra == 1) {
                        // Grafo Não Dirigido e Ponderado
                        System.out.println("==== Teste com Grafo Não Dirigido e Ponderado ====");
                        grafoDijkstra = new Grafo(5, false);
                        grafoDijkstra.addEdge(0, 1, 10);
                        grafoDijkstra.addEdge(0, 2, 3);
                        grafoDijkstra.addEdge(1, 3, 2);
                        grafoDijkstra.addEdge(2, 1, 4);
                        grafoDijkstra.addEdge(2, 3, 8);
                        grafoDijkstra.addEdge(3, 4, 7);
                        grafoDijkstra.printGrafoPonderado();
                    } else if (tipoGrafoDijkstra == 2) {
                        // Grafo Dirigido e Ponderado
                        System.out.println("==== Teste com Grafo Dirigido e Ponderado ====");
                        grafoDijkstra = new Grafo(5, true);
                        grafoDijkstra.addEdge(0, 1, 10);
                        grafoDijkstra.addEdge(0, 2, 3);
                        grafoDijkstra.addEdge(1, 3, 2);
                        grafoDijkstra.addEdge(2, 1, 4);
                        grafoDijkstra.addEdge(2, 3, 8);
                        grafoDijkstra.addEdge(3, 4, 7);
                        grafoDijkstra.printGrafoPonderado();
                    } else {
                        System.out.println("Opção inválida. Retornando ao menu principal.");
                        break;
                    }
            
                    // Executar Dijkstra
                    Dijkstra dijkstra = new Dijkstra();
                    inicio = System.nanoTime();
                    dijkstra.executar(grafoDijkstra, 0);
                    fim = System.nanoTime();
                    tempoExecucao = fim - inicio;
                    System.out.println("Tempo de execução do Dijkstra: " + tempoExecucao + " nanosegundos");
                    break;
            
                
                    case 3:
                    // Submenu para escolher o tipo de grafo
                    System.out.println("\n==== Escolha o Tipo de Grafo ====");
                    System.out.println("1. Grafo Não Dirigido e Ponderado");
                    System.out.println("2. Grafo Dirigido e Ponderado");
                    System.out.print("Escolha uma opção: ");
                    int tipoGrafoFloydWarshall = scanner.nextInt();
                
                    Grafo grafoFloydWarshall;
                
                    if (tipoGrafoFloydWarshall == 1) {
                        // Grafo Não Dirigido e Ponderado
                        System.out.println("==== Teste com Grafo Não Dirigido e Ponderado ====");
                        grafoFloydWarshall = new Grafo(7, false);
                        grafoFloydWarshall.addEdge(0, 1, 5);
                        grafoFloydWarshall.addEdge(0, 3, 10);
                        grafoFloydWarshall.addEdge(1, 2, 3);
                        grafoFloydWarshall.addEdge(2, 3, 1);
                        grafoFloydWarshall.addEdge(1, 4, 7);
                        grafoFloydWarshall.addEdge(4, 5, 2);
                        grafoFloydWarshall.addEdge(5, 6, 4);
                        grafoFloydWarshall.addEdge(3, 5, 6);
                        grafoFloydWarshall.addEdge(2, 6, 8);
                        grafoFloydWarshall.addEdge(6, 4, 3);
                        grafoFloydWarshall.printGrafoPonderado();
                    } else if (tipoGrafoFloydWarshall == 2) {
                        // Grafo Dirigido e Ponderado
                        System.out.println("==== Teste com Grafo Dirigido e Ponderado ====");
                        grafoFloydWarshall = new Grafo(7, true);
                        grafoFloydWarshall.addEdge(0, 1, 5);
                        grafoFloydWarshall.addEdge(0, 3, 10);
                        grafoFloydWarshall.addEdge(1, 2, 3);
                        grafoFloydWarshall.addEdge(2, 3, 1);
                        grafoFloydWarshall.addEdge(1, 4, 7);
                        grafoFloydWarshall.addEdge(4, 5, 2);
                        grafoFloydWarshall.addEdge(5, 6, 4);
                        grafoFloydWarshall.addEdge(3, 5, 6);
                        grafoFloydWarshall.addEdge(2, 6, 8);
                        grafoFloydWarshall.addEdge(6, 4, 3);
                        grafoFloydWarshall.printGrafoPonderado();
                    } else {
                        System.out.println("Opção inválida. Retornando ao menu principal.");
                        break;
                    }
                
                    // Executar Floyd-Warshall
                    FloydWarshall floydWarshall = new FloydWarshall();
                    inicio = System.nanoTime();
                    floydWarshall.executar(grafoFloydWarshall);
                    fim = System.nanoTime();
                    tempoExecucao = fim - inicio;
                    System.out.println("Tempo de execução do Floyd-Warshall: " + tempoExecucao + " nanosegundos");
                    break;
                
                    case 4:
                    // Submenu para escolher o tipo de grafo
                    System.out.println("\n==== Escolha o Tipo de Grafo ====");
                    System.out.println("1. Grafo Não Dirigido e Ponderado");
                    System.out.println("2. Grafo Dirigido e Ponderado");
                    System.out.print("Escolha uma opção: ");
                    int tipoGrafoFordFulkerson = scanner.nextInt();
                
                    Grafo grafoFordFulkerson;
                
                    if (tipoGrafoFordFulkerson == 1) {
                        // Grafo Não Dirigido e Ponderado
                        System.out.println("==== Teste com Grafo Não Dirigido e Ponderado ====");
                        grafoFordFulkerson = new Grafo(8, false);
                        grafoFordFulkerson.addEdge(0, 1, 16);
                        grafoFordFulkerson.addEdge(0, 2, 13);
                        grafoFordFulkerson.addEdge(1, 2, 10);
                        grafoFordFulkerson.addEdge(1, 3, 12);
                        grafoFordFulkerson.addEdge(2, 1, 4);
                        grafoFordFulkerson.addEdge(2, 4, 14);
                        grafoFordFulkerson.addEdge(3, 2, 9);
                        grafoFordFulkerson.addEdge(3, 5, 20);
                        grafoFordFulkerson.addEdge(4, 3, 7);
                        grafoFordFulkerson.addEdge(4, 5, 4);
                        grafoFordFulkerson.addEdge(5, 6, 15);
                        grafoFordFulkerson.addEdge(6, 7, 10);
                        grafoFordFulkerson.addEdge(7, 3, 5);
                        grafoFordFulkerson.addEdge(6, 4, 6);
                        grafoFordFulkerson.addEdge(7, 5, 3);
                        grafoFordFulkerson.printGrafoPonderado();
                    } else if (tipoGrafoFordFulkerson == 2) {
                        // Grafo Dirigido e Ponderado
                        System.out.println("==== Teste com Grafo Dirigido e Ponderado ====");
                        grafoFordFulkerson = new Grafo(8, true);
                        grafoFordFulkerson.addEdge(0, 1, 16);
                        grafoFordFulkerson.addEdge(0, 2, 13);
                        grafoFordFulkerson.addEdge(1, 2, 10);
                        grafoFordFulkerson.addEdge(1, 3, 12);
                        grafoFordFulkerson.addEdge(2, 1, 4);
                        grafoFordFulkerson.addEdge(2, 4, 14);
                        grafoFordFulkerson.addEdge(3, 2, 9);
                        grafoFordFulkerson.addEdge(3, 5, 20);
                        grafoFordFulkerson.addEdge(4, 3, 7);
                        grafoFordFulkerson.addEdge(4, 5, 4);
                        grafoFordFulkerson.addEdge(5, 6, 15);
                        grafoFordFulkerson.addEdge(6, 7, 10);
                        grafoFordFulkerson.addEdge(7, 3, 5);
                        grafoFordFulkerson.addEdge(6, 4, 6);
                        grafoFordFulkerson.addEdge(7, 5, 3);
                        grafoFordFulkerson.printGrafoPonderado();
                    } else {
                        System.out.println("Opção inválida. Retornando ao menu principal.");
                        break;
                    }
                
                    // Executar Ford-Fulkerson
                    FordFulkerson fordFulkerson = new FordFulkerson();
                    inicio = System.nanoTime();
                    int maxFlow = fordFulkerson.executar(grafoFordFulkerson, 0, 7);
                    fim = System.nanoTime();
                    tempoExecucao = fim - inicio;
                    System.out.println("Fluxo Máximo: " + maxFlow);
                    System.out.println("Tempo de execução do Ford-Fulkerson: " + tempoExecucao + " nanosegundos");
                    break;
                
                case 5:
                // Grafo Não Dirigido e Ponderado para Prim
                System.out.println("\n==== Teste com Grafo Não Dirigido e Ponderado ====");
                Grafo grafoPrim = new Grafo(6, false);
                grafoPrim.addEdge(0, 1, 10);
                grafoPrim.addEdge(0, 2, 15);
                grafoPrim.addEdge(1, 2, 5);
                grafoPrim.addEdge(1, 3, 20);
                grafoPrim.addEdge(2, 3, 30);
                grafoPrim.addEdge(3, 4, 40);
                grafoPrim.addEdge(4, 5, 50);
                grafoPrim.printGrafoPonderado();

                Prim prim = new Prim();
                inicio = System.nanoTime();
                prim.executar(grafoPrim, 0);  
                fim = System.nanoTime();
                tempoExecucao = fim - inicio;
                System.out.println("Tempo de execução do Prim: " + tempoExecucao + " nanosegundos");
                break;

                case 6:
                // Submenu para escolher o tipo de grafo para Bellman-Ford
                System.out.println("\n==== Escolha o Tipo de Grafo ====");
                System.out.println("1. Grafo Não Dirigido e Ponderado");
                System.out.println("2. Grafo Dirigido e Ponderado");
                System.out.print("Escolha uma opção: ");
                int tipoGrafoBellmanFord = scanner.nextInt();
            
                Grafo grafoBellmanFord;
            
                if (tipoGrafoBellmanFord == 1) {
                    // Grafo Não Dirigido e Ponderado
                    System.out.println("==== Teste com Grafo Não Dirigido e Ponderado ====");
                    grafoBellmanFord = new Grafo(7, false);
                    grafoBellmanFord.addEdge(0, 1, 5);
                    grafoBellmanFord.addEdge(0, 2, 3);
                    grafoBellmanFord.addEdge(1, 2, 2);
                    grafoBellmanFord.addEdge(1, 3, 6);
                    grafoBellmanFord.addEdge(2, 3, 7);
                    grafoBellmanFord.addEdge(2, 4, 4);
                    grafoBellmanFord.addEdge(4, 5, 1);
                    grafoBellmanFord.addEdge(5, 3, 1);
                    grafoBellmanFord.printGrafoPonderado();
                } else if (tipoGrafoBellmanFord == 2) {
                    // Grafo Dirigido e Ponderado
                    System.out.println("==== Teste com Grafo Dirigido e Ponderado ====");
                    grafoBellmanFord = new Grafo(7, true);
                    grafoBellmanFord.addEdge(0, 1, 5);
                    grafoBellmanFord.addEdge(0, 2, 3);
                    grafoBellmanFord.addEdge(1, 2, 2);
                    grafoBellmanFord.addEdge(1, 3, 6);
                    grafoBellmanFord.addEdge(2, 3, 7);
                    grafoBellmanFord.addEdge(2, 4, 4);
                    grafoBellmanFord.addEdge(4, 5, 1);
                    grafoBellmanFord.addEdge(5, 3, 1);
                    grafoBellmanFord.printGrafoPonderado();
                } else {
                    System.out.println("Opção inválida. Retornando ao menu principal.");
                    break;
                }
            
                // Executar Bellman-Ford
                BellmanFord bellmanFord = new BellmanFord();
                inicio = System.nanoTime();
                bellmanFord.executar(grafoBellmanFord, 0);
                fim = System.nanoTime();
                tempoExecucao = fim - inicio;
                System.out.println("Tempo de execução do Bellman-Ford: " + tempoExecucao + " nanosegundos");
                break;
            
            case 7:
                // Submenu para escolher o tipo de grafo para Kruskal
                System.out.println("\n==== Escolha o Tipo de Grafo ====");
                System.out.println("1. Grafo Não Dirigido e Ponderado");
                System.out.println("2. Grafo Dirigido e Ponderado");
                System.out.print("Escolha uma opção: ");
                int tipoGrafoKruskal = scanner.nextInt();
            
                Grafo grafoKruskal;
            
                if (tipoGrafoKruskal == 1) {
                    // Grafo Não Dirigido e Ponderado
                    System.out.println("==== Teste com Grafo Não Dirigido e Ponderado ====");
                    grafoKruskal = new Grafo(6, false);
                    grafoKruskal.addEdge(0, 1, 10);
                    grafoKruskal.addEdge(0, 2, 15);
                    grafoKruskal.addEdge(1, 2, 5);
                    grafoKruskal.addEdge(1, 3, 20);
                    grafoKruskal.addEdge(2, 3, 30);
                    grafoKruskal.addEdge(3, 4, 40);
                    grafoKruskal.addEdge(4, 5, 50);
                    grafoKruskal.printGrafoPonderado();
                } else if (tipoGrafoKruskal == 2) {
                    // Grafo Dirigido e Ponderado
                    System.out.println("==== Teste com Grafo Dirigido e Ponderado ====");
                    grafoKruskal = new Grafo(6, true);
                    grafoKruskal.addEdge(0, 1, 10);
                    grafoKruskal.addEdge(0, 2, 15);
                    grafoKruskal.addEdge(1, 2, 5);
                    grafoKruskal.addEdge(1, 3, 20);
                    grafoKruskal.addEdge(2, 3, 30);
                    grafoKruskal.addEdge(3, 4, 40);
                    grafoKruskal.addEdge(4, 5, 50);
                    grafoKruskal.printGrafoPonderado();
                } else {
                    System.out.println("Opção inválida. Retornando ao menu principal.");
                    break;
                }
            
                // Executar Kruskal
                Kruskal kruskal = new Kruskal();
                inicio = System.nanoTime();
                List<String> mstKruskal = kruskal.calcularMST(grafoKruskal);
                fim = System.nanoTime();
                tempoExecucao = fim - inicio;
                System.out.println("Árvore Geradora Mínima (Kruskal): " + mstKruskal);
                System.out.println("Tempo de execução do Kruskal: " + tempoExecucao + " nanosegundos");
                break;

            case 8:
                // Grafo Dirigido para Ordenação Topológica
                System.out.println("\n==== Teste com Grafo Dirigido ====");
                Grafo grafoTopologico = new Grafo(6, true);
                grafoTopologico.addEdge(0, 1, 1);
                grafoTopologico.addEdge(0, 2, 1);
                grafoTopologico.addEdge(1, 3, 1);
                grafoTopologico.addEdge(2, 3, 1);
                grafoTopologico.addEdge(3, 4, 1);
                grafoTopologico.addEdge(4, 5, 1);
                grafoTopologico.printGrafo();

                TopologicalSort topologica = new TopologicalSort();
                List<Integer> topoOrder = topologica.topologicalSort(grafoTopologico);
                topologica.printTopologicalOrder(topoOrder);
                break;
                
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
        
        scanner.close();
    }
}
