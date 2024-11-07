public class Ordenadores {

    // Método estático para ordenar um array usando Bubble Sort
    public static <T extends Comparable<T>> void bubbleSort(T[] array) {
        int n = array.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    // Método estático para ordenar um array usando Selection Sort
    public static <T extends Comparable<T>> void selectionSort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                T temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }

    // Método estático para ordenar um array usando Heap Sort
    public static <T extends Comparable<T>> void heapSort(T[] array) {
        int n = array.length;

        // Constrói o heap máximo
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Extrai elementos do heap um por um
        for (int i = n - 1; i > 0; i--) {
            T temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }

    private static <T extends Comparable<T>> void heapify(T[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left].compareTo(array[largest]) > 0) {
            largest = left;
        }

        if (right < n && array[right].compareTo(array[largest]) > 0) {
            largest = right;
        }

        if (largest != i) {
            T swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            heapify(array, n, largest);
        }
    }

    // Método estático para ordenar um array usando Quick Sort
    public static <T extends Comparable<T>> void quickSort(T[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);

            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] array, int low, int high) {
        T pivot = array[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;

                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        T temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    // Método estático para ordenar um array de inteiros usando Radix Sort
    public static void radixSort(int[] array) {
        int max = getMax(array);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(array, exp);
        }
    }

    private static int getMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    private static void countingSortByDigit(int[] array, int exp) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            count[(array[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digit = (array[i] / exp) % 10;
            output[count[digit] - 1] = array[i];
            count[digit]--;
        }

        for (int i = 0; i < n; i++) {
            array[i] = output[i];
        }
    }

    // Método para testar os algoritmos de ordenação
    public static void main(String[] args) {
        Integer[] arrayHeap = {5, 1, 4, 2, 8};
        Integer[] arrayQuick = {5, 1, 4, 2, 8};
        int[] arrayRadix = {170, 45, 75, 90, 802, 24, 2, 66};
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
        
        
        System.out.println("Array antes da ordenação (Heap Sort):");
        for (int num1 : arrayHeap) {
            System.out.print(num1 + " ");
        }

        heapSort(arrayHeap);

        System.out.println("\nArray após a ordenação (Heap Sort):");
        for (int num2 : arrayHeap) {
            System.out.print(num2 + " ");
        }

        System.out.println("\n\nArray antes da ordenação (Quick Sort):");
        for (int num3 : arrayQuick) {
            System.out.print(num3 + " ");
        }

        quickSort(arrayQuick, 0, arrayQuick.length - 1);

        System.out.println("\nArray após a ordenação (Quick Sort):");
        for (int num4 : arrayQuick) {
            System.out.print(num4 + " ");
        }

        System.out.println("\n\nArray antes da ordenação (Radix Sort):");
        for (int num5 : arrayRadix) {
            System.out.print(num5 + " ");
        }

        radixSort(arrayRadix);

        System.out.println("\nArray após a ordenação (Radix Sort):");
        for (int num6 : arrayRadix) {
            System.out.print(num6 + " ");
        }
    }
}}
