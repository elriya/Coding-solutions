class Solution {
    public int zigZagArrays(int n, int l, int r) {
        int K = r - l + 1;
        long MOD = 1_000_000_007;

        long[][] dp = new long[K + 1][2];

        // Base Case: Initialize for length 2
        for (int v = 1; v <= K; v++) {
            dp[v][0] = K - v; 
            dp[v][1] = v - 1; 
        }

        // Transition for lengths from 3 to n
        for (int i = 3; i <= n; i++) {
            long[][] nextDp = new long[K + 1][2];

            long[] prefDown = new long[K + 2];
            long[] suffUp = new long[K + 2];

            for (int v = 1; v <= K; v++) {
                prefDown[v] = (prefDown[v - 1] + dp[v][0]) % MOD;
            }
            for (int v = K; v >= 1; v--) {
                suffUp[v] = (suffUp[v + 1] + dp[v][1]) % MOD;
            }

            for (int v = 1; v <= K; v++) {
                nextDp[v][0] = suffUp[v + 1];
                
                nextDp[v][1] = prefDown[v - 1];
            }

            dp = nextDp;
        }

        long totalCount = 0;
        for (int v = 1; v <= K; v++) {
            totalCount = (totalCount + dp[v][0] + dp[v][1]) % MOD;
        }

        return (int) totalCount;
    }
}