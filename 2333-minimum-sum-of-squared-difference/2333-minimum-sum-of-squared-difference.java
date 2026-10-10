import java.util.Arrays;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalOps = (long) k1 + k2;
        
        // Count frequencies of each absolute difference (max diff is 10^5)
        int[] count = new int[100001];
        long totalDiff = 0;
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            count[diff]++;
            totalDiff += diff;
        }
        
        // If we have enough operations to reduce all differences to 0
        if (totalDiff <= totalOps) {
            return 0;
        }
        
        // Greedily reduce the largest differences
        for (int d = 100000; d > 0 && totalOps > 0; d--) {
            if (count[d] == 0) continue;
            
            // Number of reductions we can make for differences of size d
            long opsToReduce = Math.min((long) count[d], totalOps);
            
            count[d] -= opsToReduce;
            count[d - 1] += opsToReduce;
            totalOps -= opsToReduce;
        }
        
        long minSumSqDiff = 0;
        for (int d = 1; d <= 100000; d++) {
            if (count[d] > 0) {
                minSumSqDiff += (long) count[d] * d * d;
            }
        }
        
        return minSumSqDiff;
    }
}