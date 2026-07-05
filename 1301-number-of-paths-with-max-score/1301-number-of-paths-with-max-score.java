import java.util.List;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;
        
        // dp[i][j][0] stores max score, dp[i][j][1] stores path count
        int[][][] dp = new int[n][n][2];
        
        // Initialize the starting point 'S' at the bottom-right corner
        dp[n - 1][n - 1][1] = 1; 
        
        // Define the 3 directions to look from the current cell: down, right, down-right
        int[][] dirs = {{1, 0}, {0, 1}, {1, 1}};
        
        // Traverse the board from bottom-right to top-left
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // Skip the starting cell initialization and skip obstacles entirely
                if ((i == n - 1 && j == n - 1) || board.get(i).charAt(j) == 'X') {
                    continue;
                }
                
                int maxScore = -1;
                int paths = 0;
                
                // Check all 3 valid incoming paths
                for (int[] dir : dirs) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];
                    
                    // Ensure the neighbor is within bounds and reachable
                    if (ni < n && nj < n && dp[ni][nj][1] > 0) {
                        if (dp[ni][nj][0] > maxScore) {
                            maxScore = dp[ni][nj][0];
                            paths = dp[ni][nj][1];
                        } else if (dp[ni][nj][0] == maxScore) {
                            paths = (paths + dp[ni][nj][1]) % MOD;
                        }
                    }
                }
                
                // If this cell is reachable from at least one valid path
                if (maxScore != -1) {
                    char c = board.get(i).charAt(j);
                    int currVal = (c == 'E') ? 0 : (c - '0');
                    
                    dp[i][j][0] = maxScore + currVal;
                    dp[i][j][1] = paths;
                }
            }
        }
        
        // Return the final result collected at the top-left cell 'E'
        return dp[0][0];
    }
}