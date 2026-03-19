class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // 2D prefix sum arrays for 'X' and 'Y'
        int[][] cntX = new int[rows + 1][cols + 1];
        int[][] cntY = new int[rows + 1][cols + 1];
        int result = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Carry over sums from top, left, and subtract diagonal overlap
                cntX[i + 1][j + 1] = cntX[i][j + 1] + cntX[i + 1][j] - cntX[i][j];
                cntY[i + 1][j + 1] = cntY[i][j + 1] + cntY[i + 1][j] - cntY[i][j];

                // Increment based on current cell character
                if (grid[i][j] == 'X') {
                    cntX[i + 1][j + 1]++;
                } else if (grid[i][j] == 'Y') {
                    cntY[i + 1][j + 1]++;
                }

                // Check conditions: at least one 'X' and equal 'X' and 'Y'
                if (cntX[i + 1][j + 1] > 0 && cntX[i + 1][j + 1] == cntY[i + 1][j + 1]) {
                    result++;
                }
            }
        }

        return result;
    }
}