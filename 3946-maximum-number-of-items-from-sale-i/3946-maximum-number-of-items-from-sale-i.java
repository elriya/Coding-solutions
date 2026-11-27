import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freeCount = new int[n];
        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int factorI = items[i][0];
            int priceI = items[i][1];
            if (priceI < minPrice) {
                minPrice = priceI;
            }
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % factorI == 0) {
                    freeCount[i]++;
                }
            }
        }

        final int NEG_INF = Integer.MIN_VALUE / 2;
        int[] dp = new int[budget + 1];
        Arrays.fill(dp, NEG_INF);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int gain = 1 + freeCount[i];
            for (int c = budget; c >= price; c--) {
                if (dp[c - price] != NEG_INF) {
                    dp[c] = Math.max(dp[c], dp[c - price] + gain);
                }
            }
        }

        for (int c = minPrice; c <= budget; c++) {
            if (dp[c - minPrice] != NEG_INF) {
                dp[c] = Math.max(dp[c], dp[c - minPrice] + 1);
            }
        }

        int ans = 0;
        for (int c = 0; c <= budget; c++) {
            ans = Math.max(ans, dp[c]);
        }
        return ans;
    }
}