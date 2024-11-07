public class Ordenadores {

    // Método estático para ordenar um array usando Bubble Sort
    public static <T extends Comparable<T>> void bubbleSort(T[] array) {
        int n = array.length;
        boolean swapped;

        // Percorre o array várias vezes
        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            // Comparação e troca de elementos adjacentes, se necessário
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    // Troca os elementos
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            // Se não houve trocas, o array já está ordenado
            if (!swapped) {
                break;
            }
        }
    }

    // Método estático para ordenar um array usando Selection Sort
    public static <T extends Comparable<T>> void selectionSort(T[] array) {
        int n = array.length;

        // Percorre cada posição do array
        for (int i = 0; i < n - 1; i++) {
            // Encontra o índice do menor elemento a partir de i
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            // Troca o menor elemento encontrado com o elemento da posição i
            if (minIndex != i) {
                T temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }

    // Método para testar os algoritmos de ordenação
    public static void main(String[] args) {
        Integer[] arrayBubble = {5, 1, 4, 2, 8};
        Integer[] arraySelection = {5, 1, 4, 2, 8};

        System.out.println("Array antes da ordenação (Bubble Sort):");
        for (int num : arrayBubble) {
            System.out.print(num + " ");
        }

        bubbleSort(arrayBubble);

        System.out.println("\nArray após a ordenação (Bubble Sort):");
        for (int num : arrayBubble) {
            System.out.print(num + " ");
        }

        System.out.println("\n\nArray antes da ordenação (Selection Sort):");
        for (int num : arraySelection) {
            System.out.print(num + " ");
        }

        selectionSort(arraySelection);

        System.out.println("\nArray após a ordenação (Selection Sort):");
        for (int num : arraySelection) {
            System.out.print(num + " ");
        }
    }
}
