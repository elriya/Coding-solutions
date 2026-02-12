class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            int[] counts = new int[26];
            int distinctCount = 0;
            
            for (int j = i; j < n; j++) {
                int charIdx = s.charAt(j) - 'a';
                
                if (counts[charIdx] == 0) {
                    distinctCount++;
                }
                counts[charIdx]++;
                
                int targetFreq = counts[s.charAt(i) - 'a'];
                
                if (isBalanced(counts, distinctCount, targetFreq)) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;
    }

    private boolean isBalanced(int[] counts, int distinctCount, int targetFreq) {
        int foundDistinct = 0;
        for (int count : counts) {
            if (count > 0) {
                if (count != targetFreq) return false;
                foundDistinct++;
            }
        }
        return foundDistinct == distinctCount;
    }
}