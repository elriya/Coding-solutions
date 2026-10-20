class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int totalSum = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean possible = true;
            int prevMax = Integer.MIN_VALUE;

            for (int i = 0; i < n; i += k) {
                int[] block = new int[k];
                for (int j = 0; j < k; j++) {
                    block[j] = nums[i + j];
                }

                int descents = 0;
                int minVal = Integer.MAX_VALUE;
                int maxVal = Integer.MIN_VALUE;

                for (int j = 0; j < k; j++) {
                    if (block[j] < minVal) minVal = block[j];
                    if (block[j] > maxVal) maxVal = block[j];
                    if (block[j] > block[(j + 1) % k]) {
                        descents++;
                    }
                }

                if (descents > 1) {
                    possible = false;
                    break;
                }

                if (minVal < prevMax) {
                    possible = false;
                    break;
                }

                prevMax = maxVal;
            }

            if (possible) {
                totalSum += k;
            }
        }

        return totalSum;
    }
}