import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        
        int[] freq = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;
        for (int[] item : items) {
            freq[item[0]]++;
            if (item[1] < minPrice) {
                minPrice = item[1];
            }
        }
        
        int[] multCount = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            if (freq[f] == 0) continue;
            for (int m = f; m <= n; m += f) {
                multCount[f] += freq[m];
            }
        }
        
        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));
        
        long remBudget = budget;
        long totalCopies = 0;
        long limitPrice = 2L * minPrice;
        
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            
            if (price >= limitPrice || remBudget < price) {
                break;
            }
            
            long maxDoubleCopies = multCount[factor] - 1;
            if (maxDoubleCopies > 0) {
                long canBuy = Math.min(maxDoubleCopies, remBudget / price);
                totalCopies += canBuy * 2;
                remBudget -= canBuy * price;
            }
        }
        
        totalCopies += remBudget / minPrice;
        
        return (int) totalCopies;
    }
}