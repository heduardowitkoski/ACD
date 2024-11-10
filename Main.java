package teste;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Main {

    @FunctionalInterface
    interface OrdenadorMethod<T extends Comparable<T>> {
        void sort(T[] array);
    }

    // Classe para armazenar os registros dos testes com ID
    static class Registro {
        int id;
        String algoritmo;
        int tamanhoVetor;
        double tempoMedio;
        int repeticoes;

        public Registro(int id, String algoritmo, int tamanhoVetor, double tempoMedio, int repeticoes) {
            this.id = id;
            this.algoritmo = algoritmo;
            this.tamanhoVetor = tamanhoVetor;
            this.tempoMedio = tempoMedio;
            this.repeticoes = repeticoes;
        }

        @Override
        public String toString() {
            return String.format("%3d | %-15s | %10d | %12.9f s | %10d", id, algoritmo, tamanhoVetor, tempoMedio, repeticoes);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        List<Registro> registros = new ArrayList<>();
        Registro ultimoRegistro = null;
        int proximoId = 1; // ID começando de 1

        while (true) {
            System.out.println("Menu:");
            System.out.println("1 - Testar um algoritmo de ordenação");
            System.out.println("2 - Exibir o registro de testes");
            System.out.println("3 - Repetir teste anterior");
            System.out.println("4 - Deletar um teste do registro");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            if (opcao == 5) {
                System.out.println("Encerrando o programa.");
                break;
            } else if (opcao == 2) {
                if (registros.isEmpty()) {
                    System.out.println("Nenhum teste foi registrado até o momento.");
                } else {
                    System.out.println("Registro de Testes:");
                    System.out.println("ID  | Algoritmo       | Tamanho Vetor | Tempo Médio (s) | Repetições");
                    System.out.println("--------------------------------------------------------------");
                    for (Registro reg : registros) {
                        System.out.println(reg);
                    }
                }
                System.out.println();
            } else if (opcao == 4) {
                if (registros.isEmpty()) {
                    System.out.println("Nenhum teste foi registrado até o momento.");
                } else {
                    System.out.print("Digite o ID do teste a ser deletado: ");
                    int idParaDeletar = scanner.nextInt();
                    registros.removeIf(reg -> reg.id == idParaDeletar);
                    System.out.println("Teste deletado, se existia.\n");
                }
            } else if (opcao == 1 || opcao == 3) {
                String algoritmoNome = null;
                int tamanho = 0;
                int repeticoes = 0;
                OrdenadorMethod<Integer> metodoOrdenacao = null;

                if (opcao == 1) {
                    System.out.print("Digite o número de elementos do vetor (exemplo: 10, 100, 1000, etc.): ");
                    tamanho = scanner.nextInt();
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

                    switch (algoritmo) {
                        case 1:
                            metodoOrdenacao = Ordenadores::bubblesort;
                            algoritmoNome = "Bubble Sort";
                            break;
                        case 2:
                            metodoOrdenacao = Ordenadores::selectionsort;
                            algoritmoNome = "Selection Sort";
                            break;
                        case 3:
                            metodoOrdenacao = Ordenadores::heapsort;
                            algoritmoNome = "Heap Sort";
                            break;
                        case 4:
                            metodoOrdenacao = (arr) -> Ordenadores.quicksort(arr, 0, arr.length - 1);
                            algoritmoNome = "Quick Sort";
                            break;
                        default:
                            System.out.println("Opção inválida.");
                            continue;
                    }

                    System.out.print("Quantas vezes deseja repetir o teste? ");
                    repeticoes = scanner.nextInt();
                } else if (opcao == 3) {
                    if (ultimoRegistro == null) {
                        System.out.println("Nenhum teste anterior disponível para repetir.");
                        continue;
                    }
                    algoritmoNome = ultimoRegistro.algoritmo;
                    tamanho = ultimoRegistro.tamanhoVetor;
                    repeticoes = ultimoRegistro.repeticoes;
                    switch (algoritmoNome) {
                        case "Bubble Sort":
                            metodoOrdenacao = Ordenadores::bubblesort;
                            break;
                        case "Selection Sort":
                            metodoOrdenacao = Ordenadores::selectionsort;
                            break;
                        case "Heap Sort":
                            metodoOrdenacao = Ordenadores::heapsort;
                            break;
                        case "Quick Sort":
                            metodoOrdenacao = (arr) -> Ordenadores.quicksort(arr, 0, arr.length - 1);
                            break;
                    }
                }

                Integer[] vetor = new Integer[tamanho];
                for (int i = 0; i < tamanho; i++) {
                    vetor[i] = random.nextInt(10000);
                }

                long[] tempos = new long[repeticoes];
                long tempoTotal = 0;

                for (int i = 0; i < repeticoes; i++) {
                    long tempoInicio = System.nanoTime();
                    metodoOrdenacao.sort(vetor);
                    long tempoFim = System.nanoTime();
                    tempos[i] = tempoFim - tempoInicio;
                    tempoTotal += tempos[i];

                    if (i == 0) {
                        System.out.println("Vetor ordenado:");
                        for (int num : vetor) {
                            System.out.print(num + " ");
                        }
                        System.out.println();
                    }
                }

                double tempoMedio = (double) tempoTotal / repeticoes / 1e9;
                System.out.println("Média do tempo de execução: " + String.format("%.9f", tempoMedio) + " segundos\n");

                System.out.print("Deseja salvar este teste no registro? (s/n): ");
                char salvar = scanner.next().toLowerCase().charAt(0);
                if (salvar == 's') {
                    Registro novoRegistro = new Registro(proximoId++, algoritmoNome, tamanho, tempoMedio, repeticoes);
                    registros.add(novoRegistro);
                    ultimoRegistro = novoRegistro;
                    System.out.println("Teste salvo no registro.\n");
                } else {
                    System.out.println("Teste não foi salvo.\n");
                }
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }
}
