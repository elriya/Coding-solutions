import java.util.*;

class Solution {
    private static final long INF = 1000000000000000L;

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] minOps = getWindowCosts(nums, x);

        Long[][] memo = new Long[n + 1][k + 1];
        return solveDP(0, k, x, minOps, memo);
    }

    private long solveDP(int i, int rem, int x, long[] minOps, Long[][] memo) {
        if (rem == 0) return 0;
        if (i >= minOps.length) return INF;
        if (memo[i][rem] != null) return memo[i][rem];

        long skip = solveDP(i + 1, rem, x, minOps, memo);

        long pick = minOps[i] + solveDP(i + x, rem - 1, x, minOps, memo);

        return memo[i][rem] = Math.min(skip, pick);
    }

    private long[] getWindowCosts(int[] nums, int x) {
        int n = nums.length;
        long[] res = new long[n - x + 1];

        TreeMap<Integer, Integer> lower = new TreeMap<>();
        TreeMap<Integer, Integer> upper = new TreeMap<>();
        long lowerSum = 0, upperSum = 0;
        int lowerSize = 0, upperSize = 0;

        for (int i = 0; i < n; i++) {
            int val = nums[i];
            if (lowerSize == 0 || val <= lower.lastKey()) {
                lower.put(val, lower.getOrDefault(val, 0) + 1);
                lowerSum += val;
                lowerSize++;
            } else {
                upper.put(val, upper.getOrDefault(val, 0) + 1);
                upperSum += val;
                upperSize++;
            }

            if (i >= x) {
                int out = nums[i - x];
                if (lower.containsKey(out)) {
                    int count = lower.get(out);
                    if (count == 1) {
                        lower.remove(out);
                    } else {
                        lower.put(out, count - 1);
                    }
                    lowerSum -= out;
                    lowerSize--;
                } else if (upper.containsKey(out)) {
                    int count = upper.get(out);
                    if (count == 1) {
                        upper.remove(out);
                    } else {
                        upper.put(out, count - 1);
                    }
                    upperSum -= out;
                    upperSize--;
                }
            }

            if (lowerSize < upperSize) {
                int moveVal = upper.firstKey();
                int count = upper.get(moveVal);
                if (count == 1) {
                    upper.remove(moveVal);
                } else {
                    upper.put(moveVal, count - 1);
                }
                upperSum -= moveVal;
                upperSize--;

                lower.put(moveVal, lower.getOrDefault(moveVal, 0) + 1);
                lowerSum += moveVal;
                lowerSize++;
            } else if (lowerSize > upperSize + 1) {
                int moveVal = lower.lastKey();
                int count = lower.get(moveVal);
                if (count == 1) {
                    lower.remove(moveVal);
                } else {
                    lower.put(moveVal, count - 1);
                }
                lowerSum -= moveVal;
                lowerSize--;

                upper.put(moveVal, upper.getOrDefault(moveVal, 0) + 1);
                upperSum += moveVal;
                upperSize++;
            }

            if (i >= x - 1) {
                int median = lower.lastKey();
                long cost = (1L * median * lowerSize - lowerSum) + (upperSum - 1L * median * upperSize);
                res[i - x + 1] = cost;
            }
        }

        return res;
    }
}