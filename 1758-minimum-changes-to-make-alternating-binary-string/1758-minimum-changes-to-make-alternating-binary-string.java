class Solution {
    public int minOperations(String s) {
        int opsToStart0 = 0;
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            char expected = (i % 2 == 0) ? '0' : '1';
            
            if (s.charAt(i) != expected) {
                opsToStart0++;
            }
        }
        
        return Math.min(opsToStart0, n - opsToStart0);
    }
}