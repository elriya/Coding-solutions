import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        // Build the adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeComponentsCount = 0;

        // Traverse all vertices to find all connected components
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (isCompleteComponent(i, adj, visited)) {
                    completeComponentsCount++;
                }
            }
        }

        return completeComponentsCount;
    }

    private boolean isCompleteComponent(int start, List<List<Integer>> adj, boolean[] visited) {
        List<Integer> componentVertices = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        
        // Start BFS
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            componentVertices.add(curr);

            for (int neighbor : adj.get(curr)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        // Verify if the component is complete
        int numVertices = componentVertices.size();
        for (int vertex : componentVertices) {
            // A component is complete if every vertex is connected to all other vertices in it
            if (adj.get(vertex).size() != numVertices - 1) {
                return false;
            }
        }

        return true;
    }
}