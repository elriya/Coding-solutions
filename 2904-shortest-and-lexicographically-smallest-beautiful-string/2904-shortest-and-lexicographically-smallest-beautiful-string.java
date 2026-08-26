class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        String ans = "";
        int minLen = Integer.MAX_VALUE;
        
        for (int i = 0; i < s.length(); i++) {
            int onesCount = 0;
            
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(j) == '1') {
                    onesCount++;
                }
                
                // Once we hit exactly k ones, we process the substring and break
                if (onesCount == k) {
                    int currentLen = j - i + 1;
                    String currentSub = s.substring(i, j + 1);
                    
                    if (currentLen < minLen) {
                        minLen = currentLen;
                        ans = currentSub;
                    } else if (currentLen == minLen) {
                        // If lengths are equal, pick the smaller one
                        if (currentSub.compareTo(ans) < 0) {
                            ans = currentSub;
                        }
                    }
                    break; 
                }
            }
        }
        
        return ans;
    }
}