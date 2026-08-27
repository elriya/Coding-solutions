class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        
        // Find the longest prefix of target that can be matched
        int M = 0;
        while (M < n && freq[target.charAt(M) - 'a'] > 0) {
            freq[target.charAt(M) - 'a']--;
            M++;
        }
        
        // Traverse backwards to branch off with the smallest strictly greater character
        for (int i = M; i >= 0; i--) {
            if (i < n) {
                for (int c = target.charAt(i) - 'a' + 1; c < 26; c++) {
                    if (freq[c] > 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(target.substring(0, i));
                        sb.append((char) (c + 'a'));
                        freq[c]--;
                        
                        // Append remaining available characters in ascending order
                        for (int j = 0; j < 26; j++) {
                            while (freq[j] > 0) {
                                sb.append((char) (j + 'a'));
                                freq[j]--;
                            }
                        }
                        return sb.toString();
                    }
                }
            }
            
            // Restore character to the pool to check the previous index
            if (i > 0) {
                freq[target.charAt(i - 1) - 'a']++;
            }
        }
        
        return "";
    }
}