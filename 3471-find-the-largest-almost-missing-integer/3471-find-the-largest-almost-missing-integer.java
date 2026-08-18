class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int[] count = new int[51];
        
        // Loop through the starting index of every subarray of size k
        for (int i = 0; i <= n - k; i++) {
            // Track which elements we've already seen in the CURRENT subarray
            boolean[] seenInSubarray = new boolean[51];
            
            // Loop through the elements of the current subarray
            for (int j = 0; j < k; j++) {
                int val = nums[i + j];
                
                // If it's the first time seeing this value in this subarray, increment its global count
                if (!seenInSubarray[val]) {
                    seenInSubarray[val] = true;
                    count[val]++;
                }
            }
        }
        
        // Search backwards from the maximum possible value to find the largest integer
        for (int val = 50; val >= 0; val--) {
            // An integer is "almost missing" if it appears in EXACTLY one subarray of size k
            if (count[val] == 1) {
                return val;
            }
        }
        
        return -1;
    }
}