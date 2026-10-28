import java.util.HashSet;
import java.util.Set;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        @SuppressWarnings("unchecked")
        Set<Integer>[] dp = new HashSet[n];
        for (int j = 0; j < n; j++) {
            dp[j] = new HashSet<>();
        }
        
        dp[0].add(grid[0][0]);
        
        for (int j = 1; j < n; j++) {
            Set<Integer> prev = dp[j - 1];
            Set<Integer> curr = dp[j];
            int val = grid[0][j];
            for (int xorVal : prev) {
                curr.add(xorVal ^ val);
            }
        }
        
        for (int i = 1; i < m; i++) {
            Set<Integer> curr = dp[0];
            Set<Integer> nextCurr = new HashSet<>();
            int val = grid[i][0];
            for (int xorVal : curr) {
                nextCurr.add(xorVal ^ val);
            }
            dp[0] = nextCurr;
            
            for (int j = 1; j < n; j++) {
                curr = dp[j];
                Set<Integer> left = dp[j - 1];
                Set<Integer> up = dp[j]; 
                
                Set<Integer> newSet = new HashSet<>();
                val = grid[i][j];
                
                for (int xorVal : left) {
                    newSet.add(xorVal ^ val);
                }
                for (int xorVal : up) {
                    newSet.add(xorVal ^ val);
                }
                
                dp[j] = newSet;
            }
        }
        
        int minXor = Integer.MAX_VALUE;
        for (int xorVal : dp[n - 1]) {
            minXor = Math.min(minXor, xorVal);
        }
        
        return minXor;
    }
}