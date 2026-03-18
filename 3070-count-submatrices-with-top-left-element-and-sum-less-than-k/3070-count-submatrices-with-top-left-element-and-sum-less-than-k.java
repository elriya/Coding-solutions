class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Add the value from the top
                if (i > 0) {
                    grid[i][j] += grid[i - 1][j];
                }
                // Add the value from the left
                if (j > 0) {
                    grid[i][j] += grid[i][j - 1];
                }
                // Subtract the top-left diagonal (the overlap)
                if (i > 0 && j > 0) {
                    grid[i][j] -= grid[i - 1][j - 1];
                }

                if (grid[i][j] <= k) {
                    count++;
                } else {
                    break; 
                }
            }
        }

        return count;
    }
}