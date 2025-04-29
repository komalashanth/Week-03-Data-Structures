import java.util.*;

public class SortingComparison {

    // Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    // Merge Sort
    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);
            merge(arr, l, mid, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int[] left = Arrays.copyOfRange(arr, l, m + 1);
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);
        int i = 0, j = 0, k = l;
        while (i < left.length && j < right.length) {
            arr[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    // Quick Sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
            }
        }
        int tmp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = tmp;
        return i + 1;
    }

    // Generate Random Array
    public static int[] generateArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(size * 10);
        }
        return arr;
    }

    // Measure Time
    public static void measureTime(String name, int[] data, Runnable sorter) {
        int[] copy = Arrays.copyOf(data, data.length);
        long start = System.nanoTime();
        sorter.run();
        long end = System.nanoTime();
        System.out.printf("%s Time: %.2f ms\n", name, (end - start) / 1e6);
    }

    public static void runComparison(int size) {
        System.out.println("Dataset Size: " + size);
        int[] data = generateArray(size);

        if (size <= 10000) {
            measureTime("Bubble Sort", data, () -> bubbleSort(Arrays.copyOf(data, data.length)));
        } else {
            System.out.println("Bubble Sort: Skipped (too slow)");
        }

        measureTime("Merge Sort", data, () -> mergeSort(Arrays.copyOf(data, data.length), 0, data.length - 1));
        measureTime("Quick Sort", data, () -> quickSort(Arrays.copyOf(data, data.length), 0, data.length - 1));
        System.out.println("--------------------------------");
    }

    public static void main(String[] args) {
        runComparison(1000);
        runComparison(10000);
        runComparison(1000000);
    }
}

