class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        long[][][] dp = new long[m][n][2];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                long ways = 0;
                if (r == 0 && c == 0) {
                    ways = 1;
                } else {
                    ways = (dp[r][c][0] + dp[r][c][1]) % MOD;
                }

                if (ways == 0) {
                    continue;
                }

                if (r == m - 1 && c == n - 1) {
                    continue;
                }

                int[] targetRight = simulate(grid, r, c, 1);
                if (targetRight != null) {
                    int tr = targetRight[0];
                    int tc = targetRight[1];
                    int tdir = targetRight[2];
                    dp[tr][tc][tdir] = (dp[tr][tc][tdir] + ways) % MOD;
                }

                int[] targetDown = simulate(grid, r, c, 0);
                if (targetDown != null) {
                    int tr = targetDown[0];
                    int tc = targetDown[1];
                    int tdir = targetDown[2];
                    dp[tr][tc][tdir] = (dp[tr][tc][tdir] + ways) % MOD;
                }
            }
        }

        return (int) ((dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD);
    }

    private int[] simulate(int[][] grid, int r, int c, int dir) {
        int m = grid.length;
        int n = grid[0].length;
        int curr_r = r;
        int curr_c = c;
        int curr_dir = dir;

        while (true) {
            if (curr_dir == 1) {
                curr_c++;
            } else {
                curr_r++;
            }

            if (curr_r < 0 || curr_r >= m || curr_c < 0 || curr_c >= n) {
                return null;
            }

            if (grid[curr_r][curr_c] == 0) {
                return new int[] {curr_r, curr_c, curr_dir};
            } else {
                if (curr_dir == 1) {
                    curr_dir = 0;
                } else {
                    curr_dir = 1; 
                }
            }
        }
    }
}