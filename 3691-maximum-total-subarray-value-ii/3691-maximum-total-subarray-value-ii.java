import java.util.*;

public class Solution {
    
    static class SubarrayState {
        int l, r;       
        int maxIdx;    
        int minIdx;     
        long value;   
        
        public SubarrayState(int l, int r, int maxIdx, int minIdx, long value) {
            this.l = l;
            this.r = r;
            this.maxIdx = maxIdx;
            this.minIdx = minIdx;
            this.value = value;
        }
    }

    private int[][] maxST;
    private int[][] minST;
    private int[] logTable;
    private int[] array;

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        this.array = nums;
        
        logTable = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            logTable[i] = logTable[i / 2] + 1;
        }

        int K = logTable[n] + 1;
        maxST = new int[K][n];
        minST = new int[K][n];

        for (int i = 0; i < n; i++) {
            maxST[0][i] = i;
            minST[0][i] = i;
        }

        for (int j = 1; j < K; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                int idx1 = maxST[j - 1][i];
                int idx2 = maxST[j - 1][i + (1 << (j - 1))];
                maxST[j][i] = (nums[idx1] >= nums[idx2]) ? idx1 : idx2;

                int idx3 = minST[j - 1][i];
                int idx4 = minST[j - 1][i + (1 << (j - 1))];
                minST[j][i] = (nums[idx3] <= nums[idx4]) ? idx3 : idx4;
            }
        }

        PriorityQueue<SubarrayState> pq = new PriorityQueue<>((a, b) -> Long.compare(b.value, a.value));
        
        Set<Long> visited = new HashSet<>();

        int initialMax = queryMaxIdx(0, n - 1);
        int initialMin = queryMinIdx(0, n - 1);
        long initialVal = (long) nums[initialMax] - nums[initialMin];
        
        pq.offer(new SubarrayState(0, n - 1, initialMax, initialMin, initialVal));
        visited.add(id(0, n - 1, n));

        long totalValue = 0;

        while (k > 0 && !pq.isEmpty()) {
            SubarrayState curr = pq.poll();
            totalValue += curr.value;
            k--;

            if (k == 0) break;

            if (curr.l < curr.r) {
                pushState(curr.l + 1, curr.r, pq, visited, n);
                pushState(curr.l, curr.r - 1, pq, visited, n);
            }
        }

        return totalValue;
    }

    private int queryMaxIdx(int L, int R) {
        int j = logTable[R - L + 1];
        int idx1 = maxST[j][L];
        int idx2 = maxST[j][R - (1 << j) + 1];
        return (array[idx1] >= array[idx2]) ? idx1 : idx2;
    }

    private int queryMinIdx(int L, int R) {
        int j = logTable[R - L + 1];
        int idx1 = minST[j][L];
        int idx2 = minST[j][R - (1 << j) + 1];
        return (array[idx1] <= array[idx2]) ? idx1 : idx2;
    }

    private void pushState(int l, int r, PriorityQueue<SubarrayState> pq, Set<Long> visited, int n) {
        long hash = id(l, r, n);
        if (visited.contains(hash)) return;
        visited.add(hash);

        int maxIdx = queryMaxIdx(l, r);
        int minIdx = queryMinIdx(l, r);
        long val = (long) array[maxIdx] - array[minIdx];
        pq.offer(new SubarrayState(l, r, maxIdx, minIdx, val));
    }

    private long id(int l, int r, int n) {
        return ((long) l * n) + r;
    }
}