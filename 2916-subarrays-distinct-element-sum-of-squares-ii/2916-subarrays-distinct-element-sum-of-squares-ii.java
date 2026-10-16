class Solution {
    private static final int MOD = 1_000_000_007;

    private long[] sumSq;
    private long[] sumCnt;
    private long[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sumSq = new long[4 * n];
        sumCnt = new long[4 * n];
        lazy = new long[4 * n];

        java.util.HashMap<Integer, Integer> lastSeen = new java.util.HashMap<>();
        long totalSum = 0;

        for (int r = 0; r < n; r++) {
            int l = lastSeen.getOrDefault(nums[r], -1) + 1;
            lastSeen.put(nums[r], r);

            update(1, 0, n - 1, l, r);

            totalSum = (totalSum + sumSq[1]) % MOD;
        }

        return (int) totalSum;
    }

    private void push(int node, int start, int end) {
        if (lazy[node] != 0) {
            long l = lazy[node];
            int mid = (start + end) / 2;
            apply(2 * node, start, mid, l);
            apply(2 * node + 1, mid + 1, end, l);
            lazy[node] = 0;
        }
    }

    private void apply(int node, int start, int end, long l) {
        long len = end - start + 1;
        long addedSq = (2 * l * sumCnt[node] % MOD + len * ((l * l) % MOD) % MOD) % MOD;
        sumSq[node] = (sumSq[node] + addedSq) % MOD;
        
        long addedCnt = (len * l) % MOD;
        sumCnt[node] = (sumCnt[node] + addedCnt) % MOD;

        lazy[node] = (lazy[node] + l) % MOD;
    }

    private void update(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return;
        }
        if (l <= start && end <= r) {
            apply(node, start, end, 1);
            return;
        }
        push(node, start, end);
        int mid = (start + end) / 2;
        update(2 * node, start, mid, l, r);
        update(2 * node + 1, mid + 1, end, l, r);

        sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
        sumCnt[node] = (sumCnt[2 * node] + sumCnt[2 * node + 1]) % MOD;
    }
}