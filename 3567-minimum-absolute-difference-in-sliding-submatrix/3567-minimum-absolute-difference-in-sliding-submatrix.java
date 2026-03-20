import java.util.*;

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int rows = m - k + 1;
        int cols = n - k + 1;
        int[][] ans = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ans[i][j] = getMinDiff(grid, i, j, k);
            }
        }

        return ans;
    }

    private int getMinDiff(int[][] grid, int startRow, int startCol, int k) {
        // Collect all distinct values in the k x k window
        Set<Integer> distinctValues = new HashSet<>();
        for (int i = startRow; i < startRow + k; i++) {
            for (int j = startCol; j < startCol + k; j++) {
                distinctValues.add(grid[i][j]);
            }
        }

        if (distinctValues.size() <= 1) {
            return 0;
        }

        // Convert to list and sort to find the closest neighbors
        List<Integer> sortedList = new ArrayList<>(distinctValues);
        Collections.sort(sortedList);

        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < sortedList.size() - 1; i++) {
            int diff = Math.abs(sortedList.get(i) - sortedList.get(i + 1));
            if (diff < minDiff) {
                minDiff = diff;
            }
        }

        return minDiff;
    }
}