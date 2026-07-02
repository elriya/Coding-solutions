import java.util.*;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        
        // minCost[i][j] stores the minimum health lost to reach cell (i, j)
        int[][] minCost = new int[m][n];
        for (int[] row : minCost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        // Deque for 0-1 BFS
        Deque<int[]> deque = new ArrayDeque<>();
        
        // starting position
        minCost[0][0] = grid.get(0).get(0);
        deque.offerFirst(new int[]{0, 0});
        
        // Direction of vectors : Up, Down, Left, Right
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int r = curr[0];
            int c = curr[1];
            
            // If we reached the destination, we can stop early
            if (r == m - 1 && c == n - 1) {
                break;
            }
            
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                
                // Check grid boundaries
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int nextCost = minCost[r][c] + grid.get(nr).get(nc);
                    
                    // If we found a path to (nr, nc) with a smaller health loss
                    if (nextCost < minCost[nr][nc]) {
                        minCost[nr][nc] = nextCost;
                        
                        // 0-1 BFS optimization: 
                        // If edge weight is 0, add to front. If 1, add to back.
                        if (grid.get(nr).get(nc) == 0) {
                            deque.offerFirst(new int[]{nr, nc});
                        } else {
                            deque.offerLast(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        
        return minCost[m - 1][n - 1] < health;
    }
}