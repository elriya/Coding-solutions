class Solution {
    private int n;
    private long[][] pref;
    private Long[][][] memo;

    public long maximumScore(int[][] grid) {
        n = grid.length;
        pref = new long[n + 1][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                pref[i + 1][j] = pref[i][j] + grid[i][j];
            }
        }

        memo = new Long[n + 1][n + 1][3];
        return solve(0, 0, 0);
    }

    private long solve(int col, int prevH, int state) {
        if (col == n) return 0;
        if (memo[col][prevH][state] != null) return memo[col][prevH][state];

        long res = 0;

        for (int currH = 0; currH <= n; currH++) {
            if (state == 0) {
                if (currH > prevH) {
                    long gain = (col > 0) ? pref[currH][col - 1] - pref[prevH][col - 1] : 0;
                    res = Math.max(res, gain + solve(col + 1, currH, 0));
                } else if (currH < prevH) {
                    long gain = pref[prevH][col] - pref[currH][col];
                    res = Math.max(res, gain + solve(col + 1, currH, 1));
                } else {
                    res = Math.max(res, solve(col + 1, currH, 0));
                }
            } else if (state == 1) {
                if (currH < prevH) {
                    long gain = pref[prevH][col] - pref[currH][col];
                    res = Math.max(res, gain + solve(col + 1, currH, 1));
                } else {
                    res = Math.max(res, solve(col + 1, currH, 2));
                }
            } else { 
                if (currH > prevH) {
                    long gain = pref[currH][col - 1] - pref[prevH][col - 1];
                    res = Math.max(res, gain + solve(col + 1, currH, 0));
                } else {
                    long gain = pref[prevH][col] - pref[currH][col];
                    res = Math.max(res, gain + solve(col + 1, currH, 1));
                }
            }
        }
        return memo[col][prevH][state] = res;
    }
}