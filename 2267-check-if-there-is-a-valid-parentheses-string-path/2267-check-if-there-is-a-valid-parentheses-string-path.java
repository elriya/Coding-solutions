class Solution {
    private char[][] grid;
    private int m, n;
    private Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        
        if ((m + n - 1) % 2 != 0) return false;
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;
        
        memo = new Boolean[m][n][m + n + 1];
        
        return dfs(0, 0, 0);
    }
    
    private boolean dfs(int i, int j, int k) {
        if (i >= m || j >= n) return false;
        
        k += (grid[i][j] == '(') ? 1 : -1;
        
        if (k < 0) return false;
        
        if (i == m - 1 && j == n - 1) {
            return k == 0;
        }
        
        int remainingSteps = (m - 1 - i) + (n - 1 - j);
        if (k > remainingSteps) return false;
        
        if (memo[i][j][k] != null) {
            return memo[i][j][k];
        }
        
        boolean down = dfs(i + 1, j, k);
        boolean right = dfs(i, j + 1, k);
        
        return memo[i][j][k] = down || right;
    }
}