import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> lastSeen = new HashMap<>();
        int minDistance = Integer.MAX_VALUE;
        boolean found = false;

        for (int j = 0; j < nums.length; j++) {
            int currentVal = nums[j];
            int neededVal = reverse(currentVal);
        }
        
        Map<Integer, Integer> valToIndex = new HashMap<>();
        for (int j = 0; j < nums.length; j++) {
            
            int currentVal = nums[j];
            
            if (valToIndex.containsKey(currentVal)) {
                minDistance = Math.min(minDistance, j - valToIndex.get(currentVal));
                found = true;
            }
            
            int rev = reverse(currentVal);
            valToIndex.put(rev, j); 
        }

        return found ? minDistance : -1;
    }

    private int reverse(int n) {
        int rev = 0;
        while (n > 0) {
            rev = rev * 10 + (n % 10);
            n /= 10;
        }
        return rev;
    }
}