class Solution {
    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Check left and right columns
        for (int i = 0; i < rows; i++) {
            if (grid[i][0] == 0) {
                dfs(grid, i, 0);
            }
            if (grid[i][cols - 1] == 0) {
                dfs(grid, i, cols - 1);
            }
        }
        
        // Check top and bottom rows
        for (int j = 0; j < cols; j++) {
            if (grid[0][j] == 0) {
                dfs(grid, 0, j);
            }
            if (grid[rows - 1][j] == 0) {
                dfs(grid, rows - 1, j);
            }
        }
        
        int closedIslandsCount = 0;
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 0) {
                    closedIslandsCount++;
                    dfs(grid, i, j); // Sink the entire closed island
                }
            }
        }
        
        return closedIslandsCount;
    }
    
    private void dfs(int[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] == 1) {
            return;
        }
        
        grid[r][c] = 1;
        
        dfs(grid, r + 1, c); // Down
        dfs(grid, r - 1, c); // Up
        dfs(grid, r, c + 1); // Right
        dfs(grid, r, c - 1); // Left
    }
}