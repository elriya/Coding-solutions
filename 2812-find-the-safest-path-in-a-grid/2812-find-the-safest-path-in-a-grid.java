import java.util.*;

class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        
        // If the start or end cell contains a thief, the safeness factor is automatically 0
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }
        
        // dist[r][c] will store the minimum Manhattan distance to any thief
        int[][] dist = new int[n][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        Queue<int[]> queue = new LinkedList<>();
        
        // Multi-source BFS to find distance to the nearest thief for each cell
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid.get(r).get(c) == 1) {
                    dist[r][c] = 0;
                    queue.offer(new int[]{r, c});
                }
            }
        }
        
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            
            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && dist[nr][nc] == Integer.MAX_VALUE) {
                    dist[nr][nc] = dist[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        
        // Modified Dijkstra to find the maximum safeness factor path
        // PriorityQueue stores elements as [safeness_factor, r, c], sorted in descending order of safeness factor
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        maxHeap.offer(new int[]{dist[0][0], 0, 0});
        
        // safeness[r][c] stores the maximum possible safeness factor to reach (r, c)
        int[][] maxSafeness = new int[n][n];
        for (int[] row : maxSafeness) {
            Arrays.fill(row, -1);
        }
        maxSafeness[0][0] = dist[0][0];
        
        while (!maxHeap.isEmpty()) {
            int[] curr = maxHeap.poll();
            int d = curr[0];
            int r = curr[1];
            int c = curr[2];
            
            // If we reached the destination, return the maximum safeness factor found
            if (r == n - 1 && c == n - 1) {
                return d;
            }
            
            // If we found a better safeness factor path to this cell already, skip it
            if (d < maxSafeness[r][c]) {
                continue;
            }
            
            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                    // The safeness of the path to the neighbor is limited by the minimum 
                    // distance encountered so far and the neighbor's own distance to a thief
                    int nextSafeness = Math.min(d, dist[nr][nc]);
                    
                    if (nextSafeness > maxSafeness[nr][nc]) {
                        maxSafeness[nr][nc] = nextSafeness;
                        maxHeap.offer(new int[]{nextSafeness, nr, nc});
                    }
                }
            }
        }
        
        return 0;
    }
}