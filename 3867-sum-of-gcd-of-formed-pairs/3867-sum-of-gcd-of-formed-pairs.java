import java.util.Arrays;

class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] prefixGcd = new int[n];
        
        int currentMax = 0;
        // Build the prefixGcd array
        for (int i = 0; i < n; i++) {
            currentMax = Math.max(currentMax, nums[i]);
            prefixGcd[i] = gcd(nums[i], currentMax);
        }
        
        // Sort the array
        Arrays.sort(prefixGcd);
        
        // Pair the smallest and largest elements and compute sum
        long totalSum = 0;
        int left = 0;
        int right = n - 1;
        
        while (left < right) {
            totalSum += gcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }
        
        return totalSum;
    }
    
    // Helper method to calculate GCD
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}