class Solution {
    public int minFlips(String s) {
        int n = s.length();
        String doubled = s + s;
        
        StringBuilder p1 = new StringBuilder();
        StringBuilder p2 = new StringBuilder();
        for (int i = 0; i < doubled.length(); i++) {
            p1.append(i % 2 == 0 ? '0' : '1');
            p2.append(i % 2 == 0 ? '1' : '0');
        }

        int res = n; 
        int diff1 = 0, diff2 = 0;
        int left = 0;

        for (int right = 0; right < doubled.length(); right++) {
            if (doubled.charAt(right) != p1.charAt(right)) diff1++;
            if (doubled.charAt(right) != p2.charAt(right)) diff2++;

            if (right - left + 1 > n) {
                if (doubled.charAt(left) != p1.charAt(left)) diff1--;
                if (doubled.charAt(left) != p2.charAt(left)) diff2--;
                left++;
            }

            if (right - left + 1 == n) {
                res = Math.min(res, Math.min(diff1, diff2));
            }
        }

        return res;
    }
}