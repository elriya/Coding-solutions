class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        long MOD = 1_000_000_007L;

        for (int[] query : queries) {
            int li = query[0];
            int ri = query[1];
            int ki = query[2];
            int vi = query[3];

            for (int idx = li; idx <= ri; idx += ki) {
                long updatedValue = ((long) nums[idx] * vi) % MOD;
                nums[idx] = (int) updatedValue;
            }
        }

        int result = 0;
        for (int num : nums) {
            result ^= num;
        }

        return result;
    }
}