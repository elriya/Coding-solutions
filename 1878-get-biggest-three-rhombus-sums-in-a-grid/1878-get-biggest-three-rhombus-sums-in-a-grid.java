import java.util.*;

class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        TreeSet<Integer> topSums = new TreeSet<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                // Every single cell is a rhombus of size 0
                addSum(topSums, grid[r][c]);

                // Try expanding the rhombus size s
                for (int s = 1; ; s++) {
                    // Check if all 4 corners are within bounds
                    if (r - s < 0 || r + s >= m || c - s < 0 || c + s >= n) break;

                    int currentSum = 0;
                    // Top corner to right corner
                    for (int i = 0; i < s; i++) currentSum += grid[r - s + i][c + i];
                    // Right corner to bottom corner
                    for (int i = 0; i < s; i++) currentSum += grid[r + i][c + s - i];
                    // Bottom corner to left corner
                    for (int i = 0; i < s; i++) currentSum += grid[r + s - i][c - i];
                    // Left corner to top corner
                    for (int i = 0; i < s; i++) currentSum += grid[r - i][c - s + i];

                    addSum(topSums, currentSum);
                }
            }
        }

        int[] result = new int[topSums.size()];
        int idx = 0;
        while (!topSums.isEmpty()) {
            result[idx++] = topSums.pollLast();
        }
        return result;
    }

    private void addSum(TreeSet<Integer> set, int sum) {
        set.add(sum);
        if (set.size() > 3) {
            set.pollFirst(); 
        }
    }
}