class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long MOD = 1_000_000_007;

        // Using long to prevent overflow during calculations before modulo
        long[][] maxProd = new long[m][n];
        long[][] minProd = new long[m][n];

        // Initialize starting point
        maxProd[0][0] = minProd[0][0] = grid[0][0];

        // Fill first column
        for (int i = 1; i < m; i++) {
            maxProd[i][0] = minProd[i][0] = maxProd[i - 1][0] * grid[i][0];
        }

        // Fill first row
        for (int j = 1; j < n; j++) {
            maxProd[0][j] = minProd[0][j] = maxProd[0][j - 1] * grid[0][j];
        }

        // Fill the rest of the grid
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] >= 0) {
                    maxProd[i][j] = Math.max(maxProd[i - 1][j], maxProd[i][j - 1]) * grid[i][j];
                    minProd[i][j] = Math.min(minProd[i - 1][j], minProd[i][j - 1]) * grid[i][j];
                } else {
                    maxProd[i][j] = Math.min(minProd[i - 1][j], minProd[i][j - 1]) * grid[i][j];
                    minProd[i][j] = Math.max(maxProd[i - 1][j], maxProd[i][j - 1]) * grid[i][j];
                }
            }
        }

        long res = maxProd[m - 1][n - 1];
        return res < 0 ? -1 : (int) (res % MOD);
    }
}