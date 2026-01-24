import java.util.Arrays;

class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        
        int max_sum = 0;
        int n = nums.length;
        
        for (int i = 0; i < n / 2; i++) {
            max_sum = Math.max(max_sum, nums[i] + nums[n - 1 - i]);
        }
        
        return max_sum;
    }
}