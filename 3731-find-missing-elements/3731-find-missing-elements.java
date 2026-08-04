import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        
        Set<Integer> numSet = new HashSet<>();
        
        // Find the minimum and maximum values, and store array elements in a Set
        for (int num : nums) {
            minVal = Math.min(minVal, num);
            maxVal = Math.max(maxVal, num);
            numSet.add(num);
        }
        
        List<Integer> missing = new ArrayList<>();
        
        // Iterate through the full range and add any missing numbers to the result
        for (int i = minVal; i <= maxVal; i++) {
            if (!numSet.contains(i)) {
                missing.add(i);
            }
        }
        
        return missing;
    }
}