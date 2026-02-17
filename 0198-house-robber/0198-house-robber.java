class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int prev2 = 0;
        int prev1 = 0;

        for (int money : nums) {
            int current = Math.max(prev1, money + prev2);
            
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}