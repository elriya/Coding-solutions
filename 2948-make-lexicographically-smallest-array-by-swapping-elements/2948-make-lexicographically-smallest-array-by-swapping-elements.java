import java.util.Arrays;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }
        
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] ans = new int[n];
        int i = 0;
        
        // Find connected components and place them
        while (i < n) {
            int j = i + 1;
            
            // Group elements that have a difference <= limit with the previous element
            while (j < n && pairs[j][0] - pairs[j - 1][0] <= limit) {
                j++;
            }
            
            // Extract the original indices of the current group
            int[] componentIndices = new int[j - i];
            for (int k = i; k < j; k++) {
                componentIndices[k - i] = pairs[k][1];
            }
            
            Arrays.sort(componentIndices);
            
            for (int k = i; k < j; k++) {
                ans[componentIndices[k - i]] = pairs[k][0];
            }
            
            i = j;
        }
        
        return ans;
    }
}