class Solution {
    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int maxVal = 200;
        // dp[g1][g2] stores the number of pairs with GCD g1 and g2 respectively
        int[][] dp = new int[maxVal + 1][maxVal + 1];
        dp[0][0] = 1;

        // Precompute GCD values to speed up the loop
        int[][] gcdTable = new int[maxVal + 1][maxVal + 1];
        for (int i = 0; i <= maxVal; i++) {
            for (int j = 0; j <= maxVal; j++) {
                gcdTable[i][j] = gcd(i, j);
            }
        }

        for (int x : nums) {
            int[][] nextDp = new int[maxVal + 1][maxVal + 1];
            
            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    if (dp[g1][g2] == 0) continue;
                    
                    long currentCount = dp[g1][g2];

                    // Do not include x in either seq1 or seq2
                    nextDp[g1][g2] = (int) ((nextDp[g1][g2] + currentCount) % MOD);

                    // Include x in seq1
                    int n1 = gcdTable[g1][x];
                    nextDp[n1][g2] = (int) ((nextDp[n1][g2] + currentCount) % MOD);

                    // Include x in seq2
                    int n2 = gcdTable[g2][x];
                    nextDp[g1][n2] = (int) ((nextDp[g1][n2] + currentCount) % MOD);
                }
            }
            dp = nextDp;
        }

        // Sum up all valid pairs where g1 == g2 and g1 > 0
        long totalPairs = 0;
        for (int g = 1; g <= maxVal; g++) {
            totalPairs = (totalPairs + dp[g][g]) % MOD;
        }

        return (int) totalPairs;
    }

    private int gcd(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}