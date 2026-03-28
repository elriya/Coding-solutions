class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n];
        char curChar = 'a';

        for (int i = 0; i < n; i++) {
            if (word[i] != 0) continue; 
            if (curChar > 'z') return ""; 
            
            for (int j = i; j < n; j++) {
                if (lcp[i][j] > 0) {
                    word[j] = curChar;
                }
            }
            curChar++;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int expectedLcp = 0;
                if (word[i] == word[j]) {
                    if (i == n - 1 || j == n - 1) {
                        expectedLcp = 1;
                    } else {
                        expectedLcp = 1 + lcp[i + 1][j + 1];
                    }
                }
                
                if (lcp[i][j] != expectedLcp) {
                    return "";
                }
            }
        }

        return new String(word);
    }
}