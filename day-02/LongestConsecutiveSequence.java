import java.util.*;

class LongestConsecutiveSequence {
    int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        int maxLength = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {  // Check if it's the start of a sequence
                int currentNum = num;
                int currentStreak = 1;
                
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }
                
                maxLength = Math.max(maxLength, currentStreak);
            }
        }
        
        return maxLength;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence solution = new LongestConsecutiveSequence();
        int[] nums = {100, 4, 200, 1, 3, 2};
        int result = solution.longestConsecutive(nums);
        
        System.out.println("Longest consecutive sequence length: " + result);
    }
}

