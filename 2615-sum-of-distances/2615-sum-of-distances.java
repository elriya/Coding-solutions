import java.util.*;

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        Map<Integer, Long> totalSum = new HashMap<>();
        Map<Integer, Integer> totalCount = new HashMap<>();

        for (int i = 0; i < n; i++) {
            totalSum.put(nums[i], totalSum.getOrDefault(nums[i], 0L) + i);
            totalCount.put(nums[i], totalCount.getOrDefault(nums[i], 0) + 1);
        }

        Map<Integer, Long> prefixSum = new HashMap<>();
        Map<Integer, Integer> prefixCount = new HashMap<>();
        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            int val = nums[i];
            
            long curSum = prefixSum.getOrDefault(val, 0L);
            int curCnt = prefixCount.getOrDefault(val, 0);

            long tSum = totalSum.get(val);
            int tCnt = totalCount.get(val);

            long leftDist = (long) curCnt * i - curSum;
            
            long rightDist = (tSum - curSum - i) - (long) (tCnt - curCnt - 1) * i;

            arr[i] = leftDist + rightDist;

            prefixSum.put(val, curSum + i);
            prefixCount.put(val, curCnt + 1);
        }

        return arr;
    }
}