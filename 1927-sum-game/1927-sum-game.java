class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sum1 = 0, sum2 = 0;
        int cnt1 = 0, cnt2 = 0;
        
        // Process the first half
        for (int i = 0; i < n / 2; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                cnt1++;
            } else {
                sum1 += c - '0';
            }
        }
        
        // Process the second half
        for (int i = n / 2; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                cnt2++;
            } else {
                sum2 += c - '0';
            }
        }
        
        // If the total number of '?' is odd, Alice gets the last turn and can always win.
        if ((cnt1 + cnt2) % 2 != 0) {
            return true;
        }
        
        // Check if Bob's winning condition holds
        return 2 * (sum1 - sum2) != 9 * (cnt2 - cnt1);
    }
}