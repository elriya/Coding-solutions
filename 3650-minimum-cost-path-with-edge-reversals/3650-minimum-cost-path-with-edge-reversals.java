import java.util.*;

class Solution {
    public int minCost(int n, int[][] edges) {
        List<int[]>[] adj = new ArrayList[n];
        List<int[]>[] revAdj = new ArrayList[n];
        
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            revAdj[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            adj[u].add(new int[]{v, w});
            revAdj[v].add(new int[]{u, w}); 
        }
        
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{0, 0});
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];
            int d = curr[1];
            
            if (d > dist[u]) continue;
            if (u == n - 1) return d;
            
            for (int[] edge : adj[u]) {
                int v = edge[0];
                int w = edge[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
            
            for (int[] edge : revAdj[u]) {
                int v = edge[0]; 
                int w = edge[1];
                if (dist[u] + 2 * w < dist[v]) {
                    dist[v] = dist[u] + 2 * w;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }
        
        return dist[n - 1] == Integer.MAX_VALUE ? -1 : dist[n - 1];
    }
}