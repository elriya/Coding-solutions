import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put((long) num, countMap.getOrDefault((long) num, 0) + 1);
        }

        int maxLength = 1;

        // Handle the edge case of 1s
        if (countMap.containsKey(1L)) {
            int onesCount = countMap.get(1L);
            // If even, we can only take an odd number of them
            if (onesCount % 2 == 0) {
                maxLength = Math.max(maxLength, onesCount - 1);
            } else {
                maxLength = Math.max(maxLength, onesCount);
            }
        }

        // Check for other bases > 1
        for (long x : countMap.keySet()) {
            if (x == 1) continue;

            int currentLength = 0;
            long current = x;

            // Greedily build the chain while we have at least 2 elements
            while (countMap.containsKey(current) && countMap.get(current) >= 2) {
                currentLength += 2;
                current = current * current; // Move to x^2
            }

            // check if the next squared element exists to act as the peak
            if (countMap.containsKey(current)) {
                currentLength += 1; 
            } else {
                currentLength -= 1; 
            }

            maxLength = Math.max(maxLength, currentLength);
        }

        return maxLength;
    }
}