import java.util.Arrays;

public class LinearBinaryChallenge {

    // Linear search for first missing positive
    public static int firstMissingPositive(int[] arr) {
        int n = arr.length;
        boolean[] present = new boolean[n + 1];

        for (int val : arr) {
            if (val > 0 && val <= n) {
                present[val] = true;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (!present[i]) return i;
        }
        return n + 1;
    }

    // Binary search for index of target
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, -1, 1};
        int target = 4;

        int missing = firstMissingPositive(arr);
        System.out.println("First missing positive: " + missing);

        Arrays.sort(arr); // Binary search requires sorted array
        int index = binarySearch(arr, target);
        System.out.println("Index of target (" + target + "): " + index);
    }
}

