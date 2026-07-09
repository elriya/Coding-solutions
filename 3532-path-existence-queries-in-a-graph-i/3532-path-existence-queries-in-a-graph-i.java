class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Label each node with its connected component ID
        int[] component = new int[n];
        component[0] = 0;
        
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] <= maxDiff) {
                component[i] = component[i - 1]; // Same component
            } else {
                component[i] = component[i - 1] + 1; // New component
            }
        }
        
        // Answer each query in O(1) time
        boolean[] answer = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            answer[i] = (component[u] == component[v]);
        }
        
        return answer;
    }
}