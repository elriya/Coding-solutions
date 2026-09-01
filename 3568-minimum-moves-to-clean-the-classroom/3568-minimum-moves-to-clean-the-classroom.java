import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int startR = -1, startC = -1;
        
        Map<Integer, Integer> litterIdx = new HashMap<>();
        int numLitter = 0;
        
        // Find the start position and label each piece of litter
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startR = i; 
                    startC = j;
                } else if (c == 'L') {
                    litterIdx.put(i * n + j, numLitter++);
                }
            }
        }
        
        // No litter on the board
        if (numLitter == 0) return 0;
        
        // visited[row][col][mask] stores the maximum energy recorded at that specific state
        int[][][] visited = new int[m][n][1 << numLitter];
        for (int[][] arr2D : visited) {
            for (int[] arr1D : arr2D) {
                Arrays.fill(arr1D, -1);
            }
        }
        
        // Queue elements: {row, col, collected_mask, current_energy}
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startR, startC, 0, energy});
        visited[startR][startC][0] = energy;
        
        int moves = 0;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int targetMask = (1 << numLitter) - 1;
        
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int r = curr[0], c = curr[1], mask = curr[2], curEnergy = curr[3];
                
                // If the student has 0 energy left, they cannot initiate a new move
                if (curEnergy == 0) continue; 
                
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    
                    char nextCell = classroom[nr].charAt(nc);
                    if (nextCell == 'X') continue;
                    
                    int nextEnergy = curEnergy - 1;
                    if (nextCell == 'R') {
                        nextEnergy = energy; // Reset to max capacity
                    }
                    
                    int nextMask = mask;
                    if (nextCell == 'L') {
                        nextMask |= (1 << litterIdx.get(nr * n + nc));
                    }
                    
                    // All litter has been successfully collected
                    if (nextMask == targetMask) return moves + 1;
                    
                    // Proceed only if we've arrived at this state with strictly more energy than before
                    if (nextEnergy > visited[nr][nc][nextMask]) {
                        visited[nr][nc][nextMask] = nextEnergy;
                        q.offer(new int[]{nr, nc, nextMask, nextEnergy});
                    }
                }
            }
            moves++;
        }
        
        return -1;
    }
}