class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int totalOnes = 0;
        int maxTradeGain = 0;
        
        // Tracks the length of the previous '0' block
        int prevZeroLen = Integer.MIN_VALUE; 
        
        int i = 0;
        while (i < n) {
            int j = i;
            // Find the boundary of the current contiguous block
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            
            int curLen = j - i;
            
            if (s.charAt(i) == '1') {
                totalOnes += curLen;
            } else { // Current block is '0'
                if (prevZeroLen != Integer.MIN_VALUE) {
                    maxTradeGain = Math.max(maxTradeGain, prevZeroLen + curLen);
                }
                prevZeroLen = curLen;
            }
            
            i = j;
        }
        
        return totalOnes + maxTradeGain;
    }
}