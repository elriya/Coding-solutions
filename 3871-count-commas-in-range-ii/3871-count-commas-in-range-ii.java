class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long bound = 999;
        
        while (n > bound) {
            totalCommas += (n - bound);
            bound = bound * 1000 + 999; 
        }
        
        return totalCommas;
    }
}