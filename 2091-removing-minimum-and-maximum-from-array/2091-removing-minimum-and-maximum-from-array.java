class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n; 
        
        int minIndex = 0;
        int maxIndex = 0;
        
        for (int k = 1; k < n; k++) {
            if (nums[k] < nums[minIndex]) {
                minIndex = k;
            }
            if (nums[k] > nums[maxIndex]) {
                maxIndex = k;
            }
        }
        
        int i = Math.min(minIndex, maxIndex);
        int j = Math.max(minIndex, maxIndex);
        
        int bothFromFront = j + 1;
        int bothFromBack = n - i;
        int oneFromEachEnd = (i + 1) + (n - j);
        
        return Math.min(bothFromFront, Math.min(bothFromBack, oneFromEachEnd));
    }
}