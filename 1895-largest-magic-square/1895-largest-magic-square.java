class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] rowSum = new int[m][n + 1];
        int[][] colSum = new int[m + 1][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowSum[i][j + 1] = rowSum[i][j] + grid[i][j];
                colSum[i + 1][j] = colSum[i][j] + grid[i][j];
            }
        }

        for (int k = Math.min(m, n); k > 1; k--) {
            for (int i = 0; i <= m - k; i++) {
                for (int j = 0; j <= n - k; j++) {
                    if (isMagic(grid, i, j, k, rowSum, colSum)) {
                        return k;
                    }
                }
            }
        }

        return 1;
    }

    private boolean isMagic(int[][] grid, int r, int c, int k, int[][] rowSum, int[][] colSum) {
        int target = rowSum[r][c + k] - rowSum[r][c];

        for (int i = r + 1; i < r + k; i++) {
            if (rowSum[i][c + k] - rowSum[i][c] != target) return false;
        }

        for (int j = c; j < c + k; j++) {
            if (colSum[r + k][j] - colSum[r][j] != target) return false;
        }

        int diag1 = 0;
        int diag2 = 0;
        for (int i = 0; i < k; i++) {
            diag1 += grid[r + i][c + i];
            diag2 += grid[r + i][c + k - 1 - i];
        }

        return diag1 == target && diag2 == target;
    }
}