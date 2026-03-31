class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int len = n + m - 1;
        char[] res = new char[len];
        boolean[] fixed = new boolean[len];

        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (fixed[i + j] && res[i + j] != str2.charAt(j)) {
                        return ""; 
                    }
                    res[i + j] = str2.charAt(j);
                    fixed[i + j] = true;
                }
            }
        }

        for (int i = 0; i < len; i++) {
            if (!fixed[i]) {
                res[i] = 'a';
            }
        }

        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                if (isMatch(res, i, str2)) {
                    boolean changed = false;
                    for (int j = m - 1; j >= 0; j--) {
                        int idx = i + j;
                        if (!fixed[idx]) {
                            
                            res[idx] = 'b';
                            changed = true;
                            break; 
                        }
                    }
                    if (!changed) return ""; 
                }
            }
        }

        return new String(res);
    }

    private boolean isMatch(char[] res, int start, String str2) {
        for (int j = 0; j < str2.length(); j++) {
            if (res[start + j] != str2.charAt(j)) return false;
        }
        return true;
    }
}