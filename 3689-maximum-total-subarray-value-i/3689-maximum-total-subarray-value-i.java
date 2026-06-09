class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int globalMax = Integer.MIN_VALUE;
        int globalMin = Integer.MAX_VALUE;
        
        for (int num : nums) {
            if (num > globalMax) {
                globalMax = num;
            }
            if (num < globalMin) {
                globalMin = num;
            }
        }
        
        long maxDiff = (long) globalMax - globalMin;
        
        return maxDiff * k;
    }
}