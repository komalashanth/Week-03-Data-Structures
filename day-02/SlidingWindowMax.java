import java.util.*;

class SlidingWindowMax {
    List<Integer> maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            while (!dq.isEmpty() && dq.peek() <= i - k)
                dq.poll();
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i])
                dq.pollLast();
            dq.offer(i);
            if (i >= k - 1)
                result.add(nums[dq.peek()]);
        }
        return result;
    }

    public static void main(String[] args) {
        SlidingWindowMax swm = new SlidingWindowMax();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        List<Integer> result = swm.maxSlidingWindow(nums, k);
        
        System.out.println("Sliding Window Maximum:");
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}

