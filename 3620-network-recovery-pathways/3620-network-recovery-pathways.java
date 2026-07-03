import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        
        // Extract and sort unique edge costs for binary search
        TreeSet<Integer> uniqueCostsSet = new TreeSet<>();
        
        uniqueCostsSet.add(0); 
        for (int[] edge : edges) {
            uniqueCostsSet.add(edge[2]);
        }
        
        int[] uniqueCosts = new int[uniqueCostsSet.size()];
        int idx = 0;
        for (int cost : uniqueCostsSet) {
            uniqueCosts[idx++] = cost;
        }
        
        // Build the adjacency list for the DAG
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        int[] inDegree = new int[n];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            adj.get(u).add(new int[]{v, cost});
            inDegree[v]++;
        }
        
        // Get a valid topological ordering of the DAG
        int[] topoOrder = getTopoOrder(n, adj, inDegree);
        
        // Binary Search over the unique edge costs
        int low = 0, high = uniqueCosts.length - 1;
        int ans = -1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int threshold = uniqueCosts[mid];
            
            if (isValid(n, topoOrder, adj, online, k, threshold)) {
                ans = threshold; // This threshold is possible, try to find a larger one
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        // If ans is still 0, check if an actual path with 0 cost was validated or if it's just default
        if (ans == 0) {
            // Check if threshold 0 is genuinely achievable
            if (!isValid(n, topoOrder, adj, online, k, 0)) {
                return -1;
            }
        }
        
        return ans;
    }
    
    // Kahn's algorithm for Topological Sort
    private int[] getTopoOrder(int n, List<List<int[]>> adj, int[] inDegree) {
        int[] topo = new int[n];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) q.add(i);
        }
        
        int idx = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;
            for (int[] edge : adj.get(u)) {
                int v = edge[0];
                inDegree[v]--;
                if (inDegree[v] == 0) q.add(v);
            }
        }
        return topo;
    }
    
    // DP verification step using the topological order
    private boolean isValid(int n, int[] topoOrder, List<List<int[]>> adj, boolean[] online, long k, int minEdgeThreshold) {
        long[] minCost = new long[n];
        Arrays.fill(minCost, Long.MAX_VALUE);
        minCost[0] = 0;
        
        for (int u : topoOrder) {
            if (minCost[u] == Long.MAX_VALUE) continue;
            // Intermediate nodes must be online. (Node 0 and n-1 are guaranteed online)
            if (u != 0 && !online[u]) continue; 
            
            for (int[] edge : adj.get(u)) {
                int v = edge[0];
                int cost = edge[1];
                
                // Only consider edges meeting or exceeding our guessed threshold
                if (cost >= minEdgeThreshold) {
                    if (minCost[u] + cost < minCost[v]) {
                        minCost[v] = minCost[u] + cost;
                    }
                }
            }
        }
        
        return minCost[n - 1] <= k;
    }
}