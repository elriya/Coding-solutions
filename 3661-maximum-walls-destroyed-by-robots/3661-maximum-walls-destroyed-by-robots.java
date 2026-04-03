import java.util.*;

class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        int[][] robotData = new int[n][2];
        for (int i = 0; i < n; i++) {
            robotData[i][0] = robots[i];
            robotData[i][1] = distance[i];
        }
        Arrays.sort(robotData, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(walls);

        long[][] dp = new long[n][2];

        dp[0][0] = countWalls(walls, robotData[0][0] - robotData[0][1], robotData[0][0]);
        dp[0][1] = countWalls(walls, robotData[0][0], robotData[0][0] + robotData[0][1], 
                              (n > 1) ? robotData[1][0] : Integer.MAX_VALUE, true);

        for (int i = 1; i < n; i++) {
            int currPos = robotData[i][0];
            int currDist = robotData[i][1];
            int prevPos = robotData[i-1][0];
            
            int rightLimit = (i == n - 1) ? Integer.MAX_VALUE : robotData[i+1][0];

            long leftIfPrevLeft = dp[i-1][0] + countWalls(walls, currPos - currDist, currPos, prevPos, false);
            
            int gapL = prevPos;
            int gapR = currPos;
            int reachPrev = Math.min(prevPos + robotData[i-1][1], gapR - 1);
            int reachCurr = Math.max(currPos - currDist, gapL + 1);
            
            long unionCount = countWallsUnion(walls, gapL, reachPrev, reachCurr, gapR);
            long leftIfPrevRight = (i > 1 ? Math.max(dp[i-2][0], dp[i-2][1]) : 0) + unionCount;
            
            dp[i][0] = Math.max(leftIfPrevLeft, leftIfPrevRight);

            long wallsRight = countWalls(walls, currPos, currPos + currDist, rightLimit, true);
            dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]) + wallsRight;
        }

        return (int) Math.max(dp[n-1][0], dp[n-1][1]);
    }

    private int countWalls(int[] walls, int L, int R, int blocker, boolean blockerIsRight) {
        if (blockerIsRight) R = Math.min(R, blocker - 1);
        else L = Math.max(L, blocker + 1);
        return countWallsInRange(walls, L, R);
    }

    private int countWalls(int[] walls, int L, int R) {
        return countWallsInRange(walls, L, R);
    }

    private int countWallsUnion(int[] walls, int L1, int R1, int L2, int R2) {
        if (L2 <= R1) {
            return countWallsInRange(walls, L1, R2);
        } else {
            return countWallsInRange(walls, L1, R1) + countWallsInRange(walls, L2, R2);
        }
    }

    private int countWallsInRange(int[] walls, int L, int R) {
        if (L > R) return 0;
        int start = Arrays.binarySearch(walls, L);
        if (start < 0) start = -(start + 1);
        int end = Arrays.binarySearch(walls, R);
        if (end < 0) end = -(end + 1) - 1;
        return Math.max(0, end - start + 1);
    }
}