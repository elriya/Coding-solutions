class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCount = new int[31];
        for (int num : nums) {
            for (int i = 0; i <= 30; i++) {
                if ((num & (1 << i)) != 0) {
                    bitCount[i]++;
                }
            }
        }

        long ans = 0;
        long MOD = 1_000_000_007;

        for (int i = 0; i < k; i++) {
            long current = 0;
            for (int j = 0; j <= 30; j++) {
                if (bitCount[j] > 0) {
                    current |= (1 << j);
                    bitCount[j]--;
                }
            }
            ans = (ans + (current * current) % MOD) % MOD;
        }

        return (int) ans;
    }
}