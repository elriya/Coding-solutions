import java.util.Arrays;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        
        // Count frequencies of each number in nums
        int[] freq = new int[maxVal + 1];
        for (int num : nums) {
            freq[num]++;
        }
        
        // Count how many numbers are multiples of each i
        long[] gcdPairsCount = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            long cnt = 0;
            for (int j = i; j <= maxVal; j += i) {
                cnt += freq[j];
            }
            // Number of pairs that share 'i' as a common divisor
            gcdPairsCount[i] = cnt * (cnt - 1) / 2;
        }
        
        // Inclusion-Exclusion to get the exact count of pairs with GCD == i
        for (int i = maxVal; i >= 1; i--) {
            for (int j = 2 * i; j <= maxVal; j += i) {
                gcdPairsCount[i] -= gcdPairsCount[j];
            }
        }
        
        // Create prefix sums of the GCD pair counts
        long[] prefixSums = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSums[i] = prefixSums[i - 1] + gcdPairsCount[i];
        }
        
        // Answer each query using binary search
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long target = queries[i];
            
            // Find the first index where prefixSums[index] > target
            int low = 1, high = maxVal, res = maxVal;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (prefixSums[mid] > target) {
                    res = mid;
                    high = mid - 1; // Try to find a smaller valid GCD value
                } else {
                    low = mid + 1;
                }
            }
            answer[i] = res;
        }
        
        return answer;
    }
}