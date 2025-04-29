import java.util.*;

class PairWithGivenSum {
    boolean findPairWithSum(int[] arr, int target) {
        Set<Integer> set = new HashSet<>();
        
        for (int num : arr) {
            if (set.contains(target - num)) {
                return true;
            }
            set.add(num);
        }
        
        return false;
    }

    public static void main(String[] args) {
        PairWithGivenSum solution = new PairWithGivenSum();
        int[] arr = {10, 15, 3, 7};
        int target = 17;
        boolean result = solution.findPairWithSum(arr, target);
        
        System.out.println(result ? "Pair found!" : "No pair found.");
    }
}

