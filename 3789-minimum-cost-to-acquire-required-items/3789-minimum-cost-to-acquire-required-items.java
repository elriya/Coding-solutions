class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long minCost = Long.MAX_VALUE;
        
        long[] candidates = new long[] {
            0,
            need1,
            need2,
            Math.min(need1, need2),
            Math.max(need1, need2)
        };
        
        for (long i : candidates) {
            if (i < 0) continue;
            
            long currentCost = i * costBoth 
                    + Math.max(0L, need1 - i) * cost1 
                    + Math.max(0L, need2 - i) * cost2;
            
            minCost = Math.min(minCost, currentCost);
        }
        
        return minCost;
    }
}