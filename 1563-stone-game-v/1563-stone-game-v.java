import java.util.Arrays;

class Solution {
    private int[][] memo;
    private int[] prefixSum;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        memo = new int[n][n];
        prefixSum = new int[n + 1];
        
        // Build the prefix sum array for O(1) range sum queries
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + stoneValue[i];
            // Initialize memo table with -1 (uncomputed)
            Arrays.fill(memo[i], -1);
        }
        
        // Start evaluating from the full array range: index 0 to n - 1
        return solve(0, n - 1);
    }

    private int solve(int i, int j) {
        // Base case: Only one stone left, no points can be added
        if (i == j) {
            return 0;
        }
        
        // Return precomputed result if it exists
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int maxScore = 0;
        
        // Try every possible split point k
        for (int k = i; k < j; k++) {
            int leftSum = prefixSum[k + 1] - prefixSum[i];
            int rightSum = prefixSum[j + 1] - prefixSum[k + 1];

            if (leftSum < rightSum) {
                // Bob throws right, we keep left
                maxScore = Math.max(maxScore, leftSum + solve(i, k));
            } else if (leftSum > rightSum) {
                // Bob throws left, we keep right
                maxScore = Math.max(maxScore, rightSum + solve(k + 1, j));
            } else {
                // Sums are equal, Alice chooses the path that gives the maximum future score
                maxScore = Math.max(maxScore, leftSum + Math.max(solve(i, k), solve(k + 1, j)));
            }
        }
        
        return memo[i][j] = maxScore;
    }
}