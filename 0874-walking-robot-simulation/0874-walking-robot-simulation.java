import java.util.HashSet;
import java.util.Set;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        Set<String> obstacleSet = new HashSet<>();
        for (int[] obs : obstacles) {
            obstacleSet.add(obs[0] + "," + obs[1]);
        }
        
        int x = 0, y = 0;
        int directionIdx = 0; 
        int maxDistSq = 0;
        
        for (int cmd : commands) {
            if (cmd == -1) {
                directionIdx = (directionIdx + 1) % 4;
            } else if (cmd == -2) {
                directionIdx = (directionIdx + 3) % 4; 
            } else {
                for (int i = 0; i < cmd; i++) {
                    int nextX = x + directions[directionIdx][0];
                    int nextY = y + directions[directionIdx][1];
                    
                    if (!obstacleSet.contains(nextX + "," + nextY)) {
                        x = nextX;
                        y = nextY;
                        maxDistSq = Math.max(maxDistSq, x * x + y * y);
                    } else {
                        break;
                    }
                }
            }
        }
        
        return maxDistSq;
    }
}