import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int totalElements = m * n;
        
        // Effective shifts needed
        k = k % totalElements;

        // Initialize the result grid with empty lists
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            result.add(new ArrayList<>());
        }

        // Place each element directly in its final 2D position
        for (int i = 0; i < totalElements; i++) {
            // Find where the element coming to 1D index 'i' is currently located
            int prevIndex = (i - k + totalElements) % totalElements;
            
            int r = prevIndex / n;
            int c = prevIndex % n;

            // Place it in the current row
            result.get(i / n).add(grid[r][c]);
        }

        return result;
    }
}