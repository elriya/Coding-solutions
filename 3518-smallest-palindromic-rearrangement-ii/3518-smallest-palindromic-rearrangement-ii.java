class Solution {

    static final long LIMIT = 1000000L;

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) freq[ch - 'a']++;

        int[] cnt = new int[26];
        int halfLen = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            cnt[i] = freq[i] / 2;
            halfLen += cnt[i];
            if ((freq[i] & 1) == 1)
                mid = (char) ('a' + i);
        }

        long totalWays = countWays(cnt);
        if (totalWays < k) return "";

        StringBuilder half = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {

            for (int c = 0; c < 26; c++) {
                if (cnt[c] == 0) continue;

                cnt[c]--;

                long ways = countWays(cnt);

                if (ways >= k) {
                    half.append((char) ('a' + c));
                    break;
                } else {
                    k -= ways;
                    cnt[c]++;
                }
            }
        }

        String left = half.toString();
        String right = new StringBuilder(left).reverse().toString();

        if ((s.length() & 1) == 1)
            return left + mid + right;
        return left + right;
    }

    private long countWays(int[] cnt) {
        int total = 0;
        for (int x : cnt) total += x;

        long ways = 1;
        int rem = total;

        for (int c : cnt) {
            if (c == 0) continue;
            ways = multiplyCap(ways, comb(rem, c));
            if (ways >= LIMIT) return LIMIT;
            rem -= c;
        }
        return ways;
    }

    private long multiplyCap(long a, long b) {
        if (a == 0 || b == 0) return 0;
        if (a >= LIMIT || b >= LIMIT) return LIMIT;
        if (a > LIMIT / b) return LIMIT;
        long res = a * b;
        return Math.min(res, LIMIT);
    }

    private long comb(int n, int r) {
        if (r < 0 || r > n) return 0;
        r = Math.min(r, n - r);

        long res = 1;

        for (int i = 1; i <= r; i++) {
            long num = n - r + i;
            long den = i;

            long g = gcd(num, den);
            num /= g;
            den /= g;

            long g2 = gcd(res, den);
            res /= g2;
            den /= g2;

            if (res > LIMIT / num) return LIMIT;
            res *= num;
            res /= den;

            if (res >= LIMIT) return LIMIT;
        }

        return Math.min(res, LIMIT);
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}