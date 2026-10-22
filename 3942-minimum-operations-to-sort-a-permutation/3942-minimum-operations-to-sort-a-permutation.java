class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        
        boolean sorted = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                sorted = false;
                break;
            }
        }
        if (sorted) {
            return 0;
        }
        
        boolean isRotation = true;
        int k = nums[0];
        for (int i = 0; i < n; i++) {
            if (nums[i] != (k + i) % n) {
                isRotation = false;
                break;
            }
        }
        
        if (isRotation) {
            int ans = n - k;
            if (k > 0) {
                ans = Math.min(ans, k + 2);
            }
            return ans;
        }
        
        int[] rev = new int[n];
        for (int i = 0; i < n; i++) {
            rev[i] = nums[n - 1 - i];
        }
        
        boolean isReflection = true;
        int m = rev[0];
        for (int i = 0; i < n; i++) {
            if (rev[i] != (m + i) % n) {
                isReflection = false;
                break;
            }
        }
        
        if (isReflection) {
            int rot = (n - m) % n;
            return Math.min(rot + 1, (n - rot) + 1);
        }
        
        return -1;
    }
}