import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();
        
        for (String query : queries) {
            if (isMatch(query, dictionary)) {
                result.add(query);
            }
        }
        
        return result;
    }
    
    private boolean isMatch(String query, String[] dictionary) {
        int n = query.length();
        
        for (String dictWord : dictionary) {
            int diffCount = 0;
            
            for (int i = 0; i < n; i++) {
                if (query.charAt(i) != dictWord.charAt(i)) {
                    diffCount++;
                }
                if (diffCount > 2) {
                    break;
                }
            }
            
            if (diffCount <= 2) {
                return true;
            }
        }
        
        return false;
    }
}