import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean hasAllCodes(String s, int k) {
        int requiredCount = 1 << k; 
        
        Set<String> seenCodes = new HashSet<>();
        
        for (int i = 0; i <= s.length() - k; i++) {
            String sub = s.substring(i, i + k);
            seenCodes.add(sub);
            
            if (seenCodes.size() == requiredCount) {
                return true;
            }
        }
        
        return false;
    }
}