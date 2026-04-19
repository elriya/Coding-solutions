class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int maxDist = 0;
        
        int n = nums1.length;
        int m = nums2.length;
        
        while (i < n && j < m) {
            if (nums1[i] <= nums2[j]) {
                if (j >= i) {
                    maxDist = Math.max(maxDist, j - i);
                }
                j++;
            } else {
                i++;
                if (i > j && i < m) {
                    j = i;
                }
            }
        }
        
        return maxDist;
    }
}