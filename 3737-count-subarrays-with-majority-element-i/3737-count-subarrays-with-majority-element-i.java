class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int count = 0;

        // Iterate over all possible starting positions of the subarray
        for (int i = 0; i < n; i++) {
            int balance = 0;
            
            // Expand the subarray to the right
            for (int j = i; j < n; j++) {
                // If it matches target, increment balance, otherwise decrement
                if (nums[j] == target) {
                    balance++;
                } else {
                    balance--;
                }
                
                // If balance > 0, target is strictly the majority element
                if (balance > 0) {
                    count++;
                }
            }
        }

        return count;
    }
}