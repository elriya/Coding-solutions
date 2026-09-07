class Solution {
    public int distinctSubseqII(String s) {
        long MOD = 1_000_000_007;
        long[] end = new long[26];
        long total = 0;
        
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            
            long old_end = end[idx];
            long new_end = (total + 1) % MOD;
            
            end[idx] = new_end;
            total = (total - old_end + new_end + MOD) % MOD; 
        }
        
        return (int) total;
    }
}