import java.util.ArrayList;

class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] lengths = new long[n];
        long currentLen = 0;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (Character.isLowerCase(ch)) {
                currentLen++;
            } else if (ch == '*') {
                if (currentLen > 0) {
                    currentLen--;
                }
            } else if (ch == '#') {
                currentLen *= 2;
            } else if (ch == '%') {
            }
            lengths[i] = currentLen;
        }

        if (k < 0 || k >= currentLen) {
            return '.';
        }

        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            long prevLen = (i == 0) ? 0 : lengths[i - 1];

            if (Character.isLowerCase(ch)) {
                if (k == lengths[i] - 1) {
                    return ch;
                }
            } else if (ch == '#') {
                if (k >= prevLen) {
                    k %= prevLen;
                }
            } else if (ch == '%') {
                long L = lengths[i];
                if (L > 0) {
                    k = L - 1 - k;
                }
            }
        }

        return '.';
    }
}