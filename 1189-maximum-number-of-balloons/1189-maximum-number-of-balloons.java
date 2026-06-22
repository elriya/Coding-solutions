class Solution {
    public int maxNumberOfBalloons(String text) {
        // Frequency array for all 26 lowercase English letters
        int[] counts = new int[26];
        for (char c : text.toCharArray()) {
            counts[c - 'a']++;
        }
        
        // Find the limiting character count
        int bCount = counts['b' - 'a'];
        int aCount = counts['a' - 'a'];
        int lCount = counts['l' - 'a'] / 2; 
        int oCount = counts['o' - 'a'] / 2; 
        int nCount = counts['n' - 'a'];
        
        // The bottleneck defines the maximum number of words we can form
        return Math.min(bCount, 
               Math.min(aCount, 
               Math.min(lCount, 
               Math.min(oCount, nCount))));
    }
}