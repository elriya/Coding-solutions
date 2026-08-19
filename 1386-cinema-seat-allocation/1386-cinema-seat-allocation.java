import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowToMask = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            
            // ignore seats 1 and 10
            if (col >= 2 && col <= 9) {
                // map col to segment 0-3
                int bit = (col - 2) / 2; 
                rowToMask.put(row, rowToMask.getOrDefault(row, 0) | (1 << bit));
            }
        }
        
        // start with max possible
        int maxGroups = n * 2;
        
        for (int mask : rowToMask.values()) {
            // check left, right, or middle
            if ((mask & 3) == 0 || (mask & 12) == 0 || (mask & 6) == 0) {
                maxGroups -= 1;
            } else {
                maxGroups -= 2;
            }
        }
        
        return maxGroups;
    }
}