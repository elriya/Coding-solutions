class Solution {
    public int maxDistance(int[] colors) {
        int maxDist = 0;
        int n = colors.length;

        for (int i = 0; i < n; i++) {
            if (colors[i] != colors[0]) {
                maxDist = Math.max(maxDist, i);
            }
        }

        for (int i = 0; i < n; i++) {
            if (colors[i] != colors[n - 1]) {
                maxDist = Math.max(maxDist, (n - 1) - i);
            }
        }

        return maxDist;
    }
}