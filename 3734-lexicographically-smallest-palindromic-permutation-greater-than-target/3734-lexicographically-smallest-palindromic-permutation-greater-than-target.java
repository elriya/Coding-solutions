class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        
        int oddCount = 0;
        String mid_c = "";
        for (int i = 0; i < 26; i++) {
            if (counts[i] % 2 != 0) {
                oddCount++;
                mid_c = String.valueOf((char)(i + 'a'));
            }
        }
        
        if (oddCount > 1) {
            return "";
        }
        
        int[] available = new int[26];
        for (int i = 0; i < 26; i++) {
            available[i] = counts[i] / 2;
        }
        
        int m = n / 2;
        String tHalf = target.substring(0, m);
        
        // Check if making the first half exactly equal to target's first half works
        int[] tempRem = new int[26];
        System.arraycopy(available, 0, tempRem, 0, 26);
        boolean canFormTHalf = true;
        for (int i = 0; i < m; i++) {
            int charIdx = tHalf.charAt(i) - 'a';
            tempRem[charIdx]--;
            if (tempRem[charIdx] < 0) {
                canFormTHalf = false;
                break;
            }
        }
        
        if (canFormTHalf) {
            StringBuilder half = new StringBuilder(tHalf);
            String candidate = half.toString() + mid_c + half.reverse().toString();
            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }
        
        // Find the smallest permutation > target's first half
        for (int k = m - 1; k >= 0; k--) {
            int[] rem = new int[26];
            System.arraycopy(available, 0, rem, 0, 26);
            boolean possiblePrefix = true;
            
            // Check if we can form the prefix identical to target up to length k
            for (int i = 0; i < k; i++) {
                int charIdx = tHalf.charAt(i) - 'a';
                rem[charIdx]--;
                if (rem[charIdx] < 0) {
                    possiblePrefix = false;
                    break;
                }
            }
            
            if (!possiblePrefix) continue;
            
            // Try to find the next strictly greater character for index k
            int reqChar = tHalf.charAt(k) - 'a';
            int chosen = -1;
            for (int i = reqChar + 1; i < 26; i++) {
                if (rem[i] > 0) {
                    chosen = i;
                    break;
                }
            }
            
            if (chosen != -1) {
                StringBuilder p = new StringBuilder(tHalf.substring(0, k));
                p.append((char)(chosen + 'a'));
                rem[chosen]--;
                
                // Append the remaining characters sorted natively by ascending index traversal
                for (int i = 0; i < 26; i++) {
                    while (rem[i] > 0) {
                        p.append((char)(i + 'a'));
                        rem[i]--;
                    }
                }
                
                String pStr = p.toString();
                return pStr + mid_c + p.reverse().toString();
            }
        }
        
        return "";
    }
}