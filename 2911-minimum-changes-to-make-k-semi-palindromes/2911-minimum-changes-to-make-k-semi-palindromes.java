import java.util.Arrays;

class Solution {
    private static final int INF = 1_000_000_000;

    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(cost[i], INF);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 2; j <= n; j++) {
                cost[i][j] = minSemiPalindromeCost(s, i, j);
            }
        }

        int[][] dp = new int[k + 1][n + 1];
        for (int p = 0; p <= k; p++) {
            Arrays.fill(dp[p], INF);
        }
        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            for (int i = p; i <= n; i++) {
                for (int j = p - 1; j < i; j++) {
                    if (dp[p - 1][j] != INF && cost[j][i] != INF) {
                        dp[p][i] = Math.min(dp[p][i], dp[p - 1][j] + cost[j][i]);
                    }
                }
            }
        }

        return dp[k][n];
    }

    private int minSemiPalindromeCost(String s, int start, int end) {
        int len = end - start;
        if (len < 2) {
            return INF;
        }

        int best = INF;
        for (int d = 1; d < len; d++) {
            if (len % d != 0) {
                continue;
            }
            int groupLen = len / d;
            int changes = 0;

            for (int g = 0; g < d; g++) {
                for (int step = 0; step < groupLen / 2; step++) {
                    int left = start + g + step * d;
                    int right = start + g + (groupLen - 1 - step) * d;
                    if (s.charAt(left) != s.charAt(right)) {
                        changes++;
                    }
                }
            }

            best = Math.min(best, changes);
        }

        return best;
    }
}