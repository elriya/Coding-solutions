class Solution {
    public int minimumDistance(String word) {
        int[] dp = new int[27];
        java.util.Arrays.fill(dp, 0); 

        for (int i = 0; i < word.length() - 1; i++) {
            int curr = word.charAt(i) - 'A';
            int next = word.charAt(i + 1) - 'A';
            int[] nextDp = new int[27];
            java.util.Arrays.fill(nextDp, Integer.MAX_VALUE / 2);

            for (int other = 0; other <= 26; other++) {
                if (dp[other] == Integer.MAX_VALUE / 2) continue;

                nextDp[other] = Math.min(nextDp[other], dp[other] + getDist(curr, next));

                nextDp[curr] = Math.min(nextDp[curr], dp[other] + getDist(other, next));
            }
            dp = nextDp;
        }

        int minval = Integer.MAX_VALUE;
        for (int val : dp) minval = Math.min(minval, val);
        return minval;
    }

    private int getDist(int a, int b) {
        if (a == 26) return 0; 
        int x1 = a / 6, y1 = a % 6;
        int x2 = b / 6, y2 = b % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}