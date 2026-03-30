class Solution {
    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();
        
        // Frequency arrays for characters at even and odd positions
        int[] evenS1 = new int[26];
        int[] oddS1 = new int[26];
        int[] evenS2 = new int[26];
        int[] oddS2 = new int[26];
        
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                evenS1[s1.charAt(i) - 'a']++;
                evenS2[s2.charAt(i) - 'a']++;
            } else {
                oddS1[s1.charAt(i) - 'a']++;
                oddS2[s2.charAt(i) - 'a']++;
            }
        }
        
        // Compare the frequency distributions
        for (int i = 0; i < 26; i++) {
            if (evenS1[i] != evenS2[i] || oddS1[i] != oddS2[i]) {
                return false;
            }
        }
        
        return true;
    }
}