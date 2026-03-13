class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long low = 1;
        long minWorkerTime = workerTimes[0];
        for (int t : workerTimes) minWorkerTime = Math.min(minWorkerTime, t);
        long high = minWorkerTime * (long) mountainHeight * (mountainHeight + 1) / 2;
        
        long ans = high;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (canReduce(mid, mountainHeight, workerTimes)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean canReduce(long totalTime, int targetHeight, int[] workerTimes) {
        long totalHeightReduced = 0;
        for (int wTime : workerTimes) {
            long limit = (2 * totalTime) / wTime;
            long x = (long) ((-1 + Math.sqrt(1 + 4 * limit)) / 2);
            
            totalHeightReduced += x;
            if (totalHeightReduced >= targetHeight) return true;
        }
        return totalHeightReduced >= targetHeight;
    }
}