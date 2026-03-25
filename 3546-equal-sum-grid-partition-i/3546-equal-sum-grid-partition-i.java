class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long totalSum = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                totalSum += grid[i][j];
            }
        }

        if (totalSum % 2 != 0) {
            return false;
        }

        long target = totalSum / 2;

        long runningRowSum = 0;
        for (int i = 0; i < m - 1; i++) { 
            for (int j = 0; j < n; j++) {
                runningRowSum += grid[i][j];
            }
            if (runningRowSum == target) {
                return true;
            }
            if (runningRowSum > target) {
                break;
            }
        }

        long runningColSum = 0;
        for (int j = 0; j < n - 1; j++) { 
            for (int i = 0; i < m; i++) {
                runningColSum += grid[i][j];
            }
            if (runningColSum == target) {
                return true;
            }
            if (runningColSum > target) {
                break;
            }
        }

        return false;
    }
}