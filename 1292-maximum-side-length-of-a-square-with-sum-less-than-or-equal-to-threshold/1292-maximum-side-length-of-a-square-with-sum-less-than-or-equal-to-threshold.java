class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] prefixSum = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefixSum[i][j] = mat[i - 1][j - 1] + prefixSum[i - 1][j] 
                                  + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1];
            }
        }

        int maxSide = 0;
        int low = 1, high = Math.min(m, n);

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasSquare(prefixSum, mid, threshold, m, n)) {
                maxSide = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return maxSide;
    }

    private boolean hasSquare(int[][] p, int k, int threshold, int m, int n) {
        for (int i = k; i <= m; i++) {
            for (int j = k; j <= n; j++) {
                int currentSum = p[i][j] - p[i - k][j] - p[i][j - k] + p[i - k][j - k];
                if (currentSum <= threshold) return true;
            }
        }
        return false;
    }
}