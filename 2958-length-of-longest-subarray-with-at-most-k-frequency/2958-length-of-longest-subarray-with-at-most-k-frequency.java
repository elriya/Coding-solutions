import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {
            int current = nums[right];
            freq.put(current, freq.getOrDefault(current, 0) + 1);

            // Shrink the window from the left if the frequency constraint is violated
            while (freq.get(current) > k) {
                freq.put(nums[left], freq.get(nums[left]) - 1);
                left++;
            }

            // Calculate the valid window length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}