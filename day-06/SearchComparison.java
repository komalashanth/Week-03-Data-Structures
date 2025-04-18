import java.util.*;

public class SearchComparison {

    // Linear Search
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target)
                return i;
        }
        return -1;
    }

    // Binary Search (Array must be sorted)
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target)
                return mid;
            else if (arr[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    public static void runComparison(int size, int target) {
        Random rand = new Random();
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = rand.nextInt(size * 10);
        }

        // Linear Search
        long startTime = System.nanoTime();
        linearSearch(data, target);
        long endTime = System.nanoTime();
        double linearTime = (endTime - startTime) / 1e6;

        // Binary Search (after sorting)
        Arrays.sort(data); // O(N log N)
        startTime = System.nanoTime();
        binarySearch(data, target);
        endTime = System.nanoTime();
        double binaryTime = (endTime - startTime) / 1e6;

        System.out.println("Dataset Size: " + size);
        System.out.printf("Linear Search Time: %.4f ms\n", linearTime);
        System.out.printf("Binary Search Time: %.4f ms\n", binaryTime);
        System.out.println("--------------------------------");
    }

    public static void main(String[] args) {
        runComparison(1000, -1);
        runComparison(10000, -1);
        runComparison(1000000, -1);
    }
}
