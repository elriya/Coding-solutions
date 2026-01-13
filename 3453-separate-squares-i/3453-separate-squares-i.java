class Solution {
    public double separateSquares(int[][] squares) {
        double totalArea = 0;
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;

        for (int[] sq : squares) {
            long l = sq[2];
            totalArea += (double) l * l;
            minY = Math.min(minY, sq[1]);
            maxY = Math.max(maxY, (double) sq[1] + l);
        }

        double low = minY;
        double high = maxY;
        
        for (int i = 0; i < 100; i++) {
            double mid = low + (high - low) / 2;
            if (getAreaBelow(squares, mid) >= totalArea / 2.0) {
                high = mid;
            } else {
                low = mid;
            }
        }

        return low;
    }

    private double getAreaBelow(int[][] squares, double k) {
        double area = 0;
        for (int[] sq : squares) {
            double y = sq[1];
            double l = sq[2];
            double top = y + l;

            if (k <= y) {
                continue; 
            } else if (k >= top) {
                area += l * l;
            } else {
                area += l * (k - y);
            }
        }
        return area;
    }
}