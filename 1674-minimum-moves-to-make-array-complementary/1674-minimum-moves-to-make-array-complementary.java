class Solution {
    public int minMoves(int[] nums, int limit) {
        int[] delta = new int[2 * limit + 2];
        int n = nums.length;

        for (int i = 0; i < n / 2; i++) {
            int a = nums[i];
            int b = nums[n - 1 - i];

            int sum = a + b;
            int minVal = Math.min(a, b);
            int maxVal = Math.max(a, b);

            delta[2] += 2;
            delta[2 * limit + 1] -= 2;

            delta[minVal + 1] -= 1;
            delta[maxVal + limit + 1] += 1;

            delta[sum] -= 1;
            delta[sum + 1] += 1;
        }

        int minMoves = n; 
        int currentMoves = 0;

        for (int i = 2; i <= 2 * limit; i++) {
            currentMoves += delta[i];
            minMoves = Math.min(minMoves, currentMoves);
        }

        return minMoves;
    }
}