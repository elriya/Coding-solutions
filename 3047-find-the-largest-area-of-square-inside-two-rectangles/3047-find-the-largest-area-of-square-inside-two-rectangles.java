class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long maxSide = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long xLow = Math.max(bottomLeft[i][0], bottomLeft[j][0]);
                long xHigh = Math.min(topRight[i][0], topRight[j][0]);
                long yLow = Math.max(bottomLeft[i][1], bottomLeft[j][1]);
                long yHigh = Math.min(topRight[i][1], topRight[j][1]);

                if (xHigh > xLow && yHigh > yLow) {
                    long side = Math.min(xHigh - xLow, yHigh - yLow);
                    maxSide = Math.max(maxSide, side);
                }
            }
        }

        return maxSide * maxSide;
    }
}