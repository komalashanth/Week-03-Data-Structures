import java.util.*;

class ZeroSumSubarrays {
    List<List<Integer>> findSubarraysWithZeroSum(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            
            if (sum == 0) {
                result.add(Arrays.asList(0, i));
            }
            
            if (map.containsKey(sum)) {
                for (int start : map.get(sum)) {
                    result.add(Arrays.asList(start + 1, i));
                }
            }
            
            map.computeIfAbsent(sum, k -> new ArrayList<>()).add(i);
        }
        
        return result;
    }

    public static void main(String[] args) {
        ZeroSumSubarrays solution = new ZeroSumSubarrays();
        int[] arr = {6, -3, 4, -1, 2, 1, -5, 4};
        List<List<Integer>> result = solution.findSubarraysWithZeroSum(arr);
        
        for (List<Integer> subarray : result) {
            System.out.println("Subarray: " + subarray);
        }
    }
}

