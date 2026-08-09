import java.util.Arrays;

class Solution {
    private int[][] memo;
    private int[] suffixSum;
    private int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;
        // memo[i][M]: stores the maximum stones a player can get starting at index i with parameter M
        memo = new int[n][n + 1];
        suffixSum = new int[n];

        // Compute suffix sums: suffixSum[i] holds sum of piles from i to n-1
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        return solve(0, 1);
    }

    private int solve(int i, int M) {
        // If we can take all remaining piles, take them all
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }

        if (memo[i][M] != 0) {
            return memo[i][M];
        }

        int maxStones = 0;

        // Try taking X piles where 1 <= X <= 2 * M
        for (int X = 1; X <= 2 * M; X++) {
            int nextM = Math.max(M, X);
            // Current player gets total remaining stones minus what the next player optimal score is
            int currentStones = suffixSum[i] - solve(i + X, nextM);
            maxStones = Math.max(maxStones, currentStones);
        }

        memo[i][M] = maxStones;
        return maxStones;
    }
}