class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        return Math.min(
            findBest(landStartTime, landDuration, waterStartTime, waterDuration),
            findBest(waterStartTime, waterDuration, landStartTime, landDuration)
        );
    }

    private int findBest(int[] start1, int[] dur1, int[] start2, int[] dur2) {
        int n = start1.length;
        int m = start2.length;
        
        int minFinish1 = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            minFinish1 = Math.min(minFinish1, start1[i] + dur1[i]);
        }
        
        int minTotalFinish = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) {
            int currentFinish = Math.max(minFinish1, start2[j]) + dur2[j];
            minTotalFinish = Math.min(minTotalFinish, currentFinish);
        }
        
        return minTotalFinish;
    }
}