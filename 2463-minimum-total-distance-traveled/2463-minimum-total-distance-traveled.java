import java.util.*;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));

        int n = robot.size();
        int m = factory.length;

        long[][] dp = new long[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 2); 
        }

        for (int j = 1; j <= m; j++) {
            int factoryPos = factory[j - 1][0];
            int limit = factory[j - 1][1];

            for (int i = 0; i <= n; i++) {
                dp[i][j] = dp[i][j - 1];

                long currentDistance = 0;
                for (int k = 1; k <= limit && i - k >= 0; k++) {
                    currentDistance += Math.abs(robot.get(i - k) - factoryPos);
                    
                    if (dp[i - k][j - 1] != Long.MAX_VALUE / 2) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - k][j - 1] + currentDistance);
                    }
                }
            }
        }

        return dp[n][m];
    }
}