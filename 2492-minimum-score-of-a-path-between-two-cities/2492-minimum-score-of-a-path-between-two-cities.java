import java.util.*;

class Solution {
    public int minScore(int n, int[][] roads) {
        // Build the adjacency list representation of the graph
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int dist = road[2];
            graph.get(u).add(new int[]{v, dist});
            graph.get(v).add(new int[]{u, dist});
        }
        
        // BFS to traverse the component containing city 1
        int minScore = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(1);
        visited[1] = true;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            // Check all neighboring roads
            for (int[] neighbor : graph.get(curr)) {
                int nextNode = neighbor[0];
                int weight = neighbor[1];
                
                // Track the minimum road weight seen in this entire component
                minScore = Math.min(minScore, weight);
                
                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    queue.offer(nextNode);
                }
            }
        }
        
        return minScore;
    }
}