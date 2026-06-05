class Solution {

    private char[] digits;
    private long[][][][][][] memoCnt;
    private long[][][][][][] memoSum;
    private boolean[][][][][][] seen;

    public long totalWaviness(long num1, long num2) {
        return calc(num2) - calc(num1 - 1);
    }

    private long calc(long n) {
        if (n <= 0) return 0;

        digits = Long.toString(n).toCharArray();
        int len = digits.length;

        memoCnt = new long[len + 1][2][3][11][11][1];
        memoSum = new long[len + 1][2][3][11][11][1];
        seen = new boolean[len + 1][2][3][11][11][1];

        long[] res = dfs(0, 1, 0, 10, 10);
        return res[1];
    }

    // returns {countNumbers, totalWaviness}
    private long[] dfs(int pos, int tight, int state, int prev2, int prev1) {
        if (pos == digits.length) {
            return new long[]{1, 0};
        }

        if (seen[pos][tight][state][prev2][prev1][0]) {
            return new long[]{
                memoCnt[pos][tight][state][prev2][prev1][0],
                memoSum[pos][tight][state][prev2][prev1][0]
            };
        }

        long count = 0;
        long sum = 0;

        int limit = (tight == 1) ? digits[pos] - '0' : 9;

        for (int d = 0; d <= limit; d++) {
            int ntight = (tight == 1 && d == limit) ? 1 : 0;

            if (state == 0) {
                // no actual digit started yet
                if (d == 0) {
                    long[] nxt = dfs(pos + 1, ntight, 0, 10, 10);
                    count += nxt[0];
                    sum += nxt[1];
                } else {
                    long[] nxt = dfs(pos + 1, ntight, 1, 10, d);
                    count += nxt[0];
                    sum += nxt[1];
                }
            } else if (state == 1) {
                // exactly one actual digit seen
                long[] nxt = dfs(pos + 1, ntight, 2, prev1, d);
                count += nxt[0];
                sum += nxt[1];
            } else {
                // at least two actual digits seen
                int add = 0;

                if ((prev1 > prev2 && prev1 > d) ||
                    (prev1 < prev2 && prev1 < d)) {
                    add = 1;
                }

                long[] nxt = dfs(pos + 1, ntight, 2, prev1, d);

                count += nxt[0];
                sum += nxt[1] + nxt[0] * add;
            }
        }

        seen[pos][tight][state][prev2][prev1][0] = true;
        memoCnt[pos][tight][state][prev2][prev1][0] = count;
        memoSum[pos][tight][state][prev2][prev1][0] = sum;

        return new long[]{count, sum};
    }
}