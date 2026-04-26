class Solution {
    private int m, n;
    private boolean[][] visited;
    private final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean containsCycle(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    if (dfs(grid, i, j, -1, -1, grid[i][j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] grid, int r, int c, int prevR, int prevC, char val) {
        visited[r][c] = true;

        for (int[] d : dirs) {
            int nr = r + d[0];
            int nc = c + d[1];

            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == val) {
                if (visited[nr][nc]) {
                    if (nr != prevR || nc != prevC) {
                        return true;
                    }
                } else {
                    if (dfs(grid, nr, nc, r, c, val)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}