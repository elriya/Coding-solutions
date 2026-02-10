import java.util.HashSet;

class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            HashSet<Integer> distinctEvens = new HashSet<>();
            HashSet<Integer> distinctOdds = new HashSet<>();

            for (int j = i; j < n; j++) {
                if (nums[j] % 2 == 0) {
                    distinctEvens.add(nums[j]);
                } else {
                    distinctOdds.add(nums[j]);
                }

                if (distinctEvens.size() == distinctOdds.size()) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }
}