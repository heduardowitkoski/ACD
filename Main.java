package teste;

import java.util.Scanner;
import java.util.Random;

public class Main{
    
    @FunctionalInterface
    interface OrdenadorMethod<T extends Comparable<T>> {
        void sort(T[] array);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1 - Testar um algoritmo de ordenação");
            System.out.println("2 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            if (opcao == 2) {
                System.out.println("Encerrando o programa.");
                break;
            } else if (opcao == 1) {
                System.out.print("Digite o número de elementos do vetor (exemplo: 10, 100, 1000, etc.): ");
                int tamanho = scanner.nextInt();
                Integer[] vetor = new Integer[tamanho];

                // Gera um vetor com valores aleatórios
                for (int i = 0; i < tamanho; i++) {
                    vetor[i] = random.nextInt(10000); // Números entre 0 e 9999
                }

                System.out.println("Escolha um algoritmo de ordenação:");
                System.out.println("1 - Bubble Sort");
                System.out.println("2 - Selection Sort");
                System.out.println("3 - Heap Sort");
                System.out.println("4 - Quick Sort");
                int algoritmo = scanner.nextInt();

                OrdenadorMethod<Integer> metodoOrdenacao = null;

                switch (algoritmo) {
                    case 1:
                        metodoOrdenacao = Ordenadores::bubblesort;
                        break;
                    case 2:
                        metodoOrdenacao = Ordenadores::selectionsort;
                        break;
                    case 3:
                        metodoOrdenacao = Ordenadores::heapsort;
                        break;
                    case 4:
                        metodoOrdenacao = (arr) -> Ordenadores.quicksort(arr, 0, arr.length - 1);
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        continue;
                }

                System.out.print("Quantas vezes deseja repetir o teste? ");
                int repeticoes = scanner.nextInt();

                long[] tempos = new long[repeticoes];
                long tempoTotal = 0;

                for (int i = 0; i < repeticoes; i++) {
                    // Medição do tempo de execução
                    long tempoInicio = System.nanoTime();
                    metodoOrdenacao.sort(vetor);
                    long tempoFim = System.nanoTime();
                    tempos[i] = tempoFim - tempoInicio;
                    tempoTotal += tempos[i];

                    // Exibe o vetor ordenado (apenas no primeiro teste)
                    if (i == 0) {
                        System.out.println("Vetor ordenado:");
                        for (int num : vetor) {
                            System.out.print(num + " ");
                        }
                        System.out.println();
                    }

                    System.out.println("Tempo de execução do teste " + (i + 1) + ": " + tempos[i] + " nanosegundos");
                }

                // Cálculo da média simples dos tempos
                long tempoMedio = tempoTotal / repeticoes;
                System.out.println("\nTempos de execução para " + repeticoes + " testes:");
                for (int i = 0; i < repeticoes; i++) {
                    System.out.println("Teste " + (i + 1) + ": " + tempos[i] + " nanosegundos");
                }
                System.out.println("Média do tempo de execução: " + tempoMedio + " nanosegundos\n");

            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }
}
