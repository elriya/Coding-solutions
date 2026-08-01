class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        // Base Case: Subarrays of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }

        // Fill the DP table for subarray lengths from 2 to n
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(
                    nums[i] - dp[i + 1][j],
                    nums[j] - dp[i][j - 1]
                );
            }
        }

        // Player 1 wins if net score over Player 2 is non-negative
        return dp[0][n - 1] >= 0;
    }
}