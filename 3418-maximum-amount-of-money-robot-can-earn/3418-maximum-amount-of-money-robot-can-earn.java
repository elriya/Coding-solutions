class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        
        int[][][] dp = new int[m][n][3];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE / 2; 
                }
            }
        }

        dp[0][0][0] = coins[0][0];
        if (coins[0][0] < 0) {
            dp[0][0][1] = 0; 
            dp[0][0][2] = 0; 
        } else {
            dp[0][0][1] = coins[0][0];
            dp[0][0][2] = coins[0][0];
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                for (int k = 0; k < 3; k++) {
                    int fromPrev = Integer.MIN_VALUE / 2;
                    if (i > 0) fromPrev = Math.max(fromPrev, dp[i - 1][j][k]);
                    if (j > 0) fromPrev = Math.max(fromPrev, dp[i][j - 1][k]);
                    
                    dp[i][j][k] = Math.max(dp[i][j][k], fromPrev + coins[i][j]);

                    if (k > 0 && coins[i][j] < 0) {
                        int fromPrevWithOneLessSkip = Integer.MIN_VALUE / 2;
                        if (i > 0) fromPrevWithOneLessSkip = Math.max(fromPrevWithOneLessSkip, dp[i - 1][j][k - 1]);
                        if (j > 0) fromPrevWithOneLessSkip = Math.max(fromPrevWithOneLessSkip, dp[i][j - 1][k - 1]);
                        
                        dp[i][j][k] = Math.max(dp[i][j][k], fromPrevWithOneLessSkip);
                    }
                    
                    if (k > 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j][k-1]);
                    }
                }
            }
        }

        return Math.max(dp[m - 1][n - 1][0], Math.max(dp[m - 1][n - 1][1], dp[m - 1][n - 1][2]));
    }
}