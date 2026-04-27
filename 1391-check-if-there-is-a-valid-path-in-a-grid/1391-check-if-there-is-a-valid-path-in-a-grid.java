import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        
        int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        
        int[][] connections = {
            {}, // Placeholder
            {0, 1}, // Type 1: Left, Right
            {2, 3}, // Type 2: Up, Down
            {0, 3}, // Type 3: Left, Down
            {1, 3}, // Type 4: Right, Down
            {0, 2}, // Type 5: Left, Up
            {1, 2}  // Type 6: Right, Up
        };
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            
            if (r == m - 1 && c == n - 1) return true;
            
            int type = grid[r][c];
            for (int dirIndex : connections[type]) {
                int nr = r + dirs[dirIndex][0];
                int nc = c + dirs[dirIndex][1];
                
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                    // Check if the neighbor has a connection back to current cell
                    if (canConnect(grid[nr][nc], dirIndex)) {
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }
        
        return false;
    }
    
    private boolean canConnect(int type, int direction) {
        int targetDirection = (direction % 2 == 0) ? direction + 1 : direction - 1;
        
        int[] allowed = getConnections(type);
        for (int dir : allowed) {
            if (dir == targetDirection) return true;
        }
        return false;
    }
    
    private int[] getConnections(int type) {
        switch(type) {
            case 1: return new int[]{0, 1};
            case 2: return new int[]{2, 3};
            case 3: return new int[]{0, 3};
            case 4: return new int[]{1, 3};
            case 5: return new int[]{0, 2};
            case 6: return new int[]{1, 2};
            default: return new int[]{};
        }
    }
}