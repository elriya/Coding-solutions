class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        int total = n + k - 1;
        int m = 2 * k;
        
        long[][] C = new long[total + 1][m + 1];
        
        for (int i = 0; i <= total; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= m && j <= i; j++) {
                C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]) % MOD;
            }
        }
        
        return (int) C[total][m];
    }
}