class Solution {
    private static final int MOD = 1_000_000_007;

    public int numOfSubarrays(int[] arr) {
        long evenCount = 1; 
        long oddCount = 0;
        int prefixParity = 0;
        long ans = 0;

        for (int num : arr) {
            prefixParity ^= (num & 1);
            if (prefixParity == 1) {
                ans = (ans + evenCount) % MOD;
                oddCount++;
            } else {
                ans = (ans + oddCount) % MOD;
                evenCount++;
            }
        }

        return (int) ans;
    }
}