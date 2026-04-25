import java.util.Arrays;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] pos = new long[n];
        long perimeter = 4L * side;

        for (int i = 0; i < n; i++) {
            pos[i] = getPerimeterPos(points[i][0], points[i][1], side);
        }
        Arrays.sort(pos);

        // Extended array to simplify circular distance checks
        long[] extendedPos = new long[2 * n];
        for (int i = 0; i < n; i++) {
            extendedPos[i] = pos[i];
            extendedPos[i + n] = pos[i] + perimeter;
        }

        long low = 0, high = 2L * side;
        int ans = 0;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (canPlace(extendedPos, n, k, mid, perimeter)) {
                ans = (int) mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private long getPerimeterPos(int x, int y, int side) {
        if (y == 0) return x;              // Bottom edge
        if (x == side) return side + y;    // Right edge
        if (y == side) return 3L * side - x; // Top edge
        return 4L * side - y;              // Left edge
    }

    private boolean canPlace(long[] extendedPos, int n, int k, long dist, long perimeter) {
        // Try starting the sequence from every point i
        for (int i = 0; i < n; i++) {
            long lastPos = extendedPos[i];
            int currentIdx = i;
            int count = 1;

            // Greedily pick the next k-1 points
            for (int j = 1; j < k; j++) {
                long target = lastPos + dist;
                
                // Find first point >= target
                int nextIdx = Arrays.binarySearch(extendedPos, target);
                if (nextIdx < 0) nextIdx = -(nextIdx + 1);
                
                // Ensure we don't pick the same point or go beyond the circle
                // We must pick a point strictly further than the last one we picked.
                // Binary search finds first index >= target.
                // We also limit to i + n to ensure we stay within one full circle rotation
                if (nextIdx < i + n) {
                    lastPos = extendedPos[nextIdx];
                    currentIdx = nextIdx;
                    count++;
                } else {
                    break;
                }
            }
            
            // Validate if we picked k points and if the last point connects back to the start
            if (count == k) {
                // Circular distance: (start + perimeter) - last_point >= dist
                if (extendedPos[i] + perimeter - lastPos >= dist) {
                    return true;
                }
            }
        }
        return false;
    }
}