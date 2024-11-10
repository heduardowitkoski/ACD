package acdtrabalho1;

import java.util.ArrayList;
import java.util.Collections;

public class Ordenadores{

    /*public static void main(String[] args)
    {
        Integer[] vetor = {2, 4, 5, 1, 12, 9, 7};
        System.out.printf("Vetor antes da ordenação:\n");
        for (Integer vetor1 : vetor) {
            System.out.printf("|%d", vetor1);
        }
        System.out.println("|");
        
        selectionsort(vetor);
        
        System.out.printf("\nVetor após da ordenação:\n");
        for (Integer vetor1 : vetor) {
            System.out.printf("|%d", vetor1);
        }
        System.out.println("|");
        
    }*/
    
    
    // Bubble Sort
    public static <T extends Comparable<T>> void bubblesort(T[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // Selection Sort
    public static <T extends Comparable<T>> void selectionsort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            T temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    // Heap Sort
    public static <T extends Comparable<T>> void heapsort(T[] array) {
        int n = array.length;

        // Construir o heap (reorganizar o array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Extrair elementos do heap um por um
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

    // Quick Sort
    public static <T extends Comparable<T>> void quicksort(T[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quicksort(array, low, pi - 1);
            quicksort(array, pi + 1, high);
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

    public static <T extends Comparable<T>> void shellsort(T[] array) {
        int n = array.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                T temp = array[i];
                int j;
                for (j = i; j >= gap && array[j - gap].compareTo(temp) > 0; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }
    }
    
    public static void countingsort(Integer[] array) {
        int max = Integer.MIN_VALUE;
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }

        int[] count = new int[max + 1];

        // Conta a ocorrência de cada elemento
        for (int num : array) {
            count[num]++;
        }

        int index = 0;
        // Reconstrói o array a partir do array de contagem
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                array[index++] = i;
                count[i]--;
            }
        }
    }
    
    public static void mergesort(Integer[] array) {
        if (array.length < 2) {
            return;
        }
        int mid = array.length / 2;
        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[array.length - mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        mergesort(left);
        mergesort(right);
        merge(array, left, right);
    }

    private static void merge(Integer[] array, Integer[] left, Integer[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }
    
    
    // Implementação do Bucket Sort
    public static void bucketsort(Integer[] array) {
        if (array.length <= 0) {
            return;
        }

        // Encontra o valor máximo no array
        int maxValue = array[0];
        for (int num : array) {
            if (num > maxValue) {
                maxValue = num;
            }
        }

        // Cria os buckets
        int numBuckets = (int) Math.sqrt(array.length);
        ArrayList<Integer>[] buckets = new ArrayList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Distribui os elementos nos buckets
        for (int num : array) {
            int bucketIndex = (num * numBuckets) / (maxValue + 1);
            buckets[bucketIndex].add(num);
        }

        // Ordena cada bucket individualmente e combina os resultados
        int index = 0;
        for (ArrayList<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int num : bucket) {
                array[index++] = num;
            }
        }
    }
    
    // Insertion Sort
public static <T extends Comparable<T>> void insertionsort(T[] array) {
    int n = array.length;
    for (int i = 1; i < n; i++) {
        T key = array[i];
        int j = i - 1;

        // Mover elementos que são maiores que a chave para uma posição à frente
        // de sua posição atual
        while (j >= 0 && array[j].compareTo(key) > 0) {
            array[j + 1] = array[j];
            j = j - 1;
        }
        array[j + 1] = key;
    }
}

    
    // Radix Sort (aplicável apenas para arrays de inteiros)
    public static void radixsort(int[] array) {
        int max = getMax(array);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(array, exp);
        }
    }

    private static void countSort(int[] array, int exp) {
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
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, array, 0, n);
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
}
