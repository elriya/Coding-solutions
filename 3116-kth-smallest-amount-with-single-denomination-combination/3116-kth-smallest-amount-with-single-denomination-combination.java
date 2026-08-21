class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        int numSubsets = 1 << n;
        
        long[] lcmArray = new long[numSubsets];
        int[] setBits = new int[numSubsets];
        
        // Base case for LCM array
        lcmArray[0] = 1;
        long minCoin = Integer.MAX_VALUE;
        
        // Precompute LCMs and subset sizes (popcounts)
        for (int i = 1; i < numSubsets; i++) {
            int lowestBit = Integer.lowestOneBit(i);
            int prev = i ^ lowestBit;
            int coinIdx = Integer.numberOfTrailingZeros(lowestBit);
            
            if (prev == 0) {
                lcmArray[i] = coins[coinIdx];
                minCoin = Math.min(minCoin, coins[coinIdx]);
            } else {
                lcmArray[i] = lcm(lcmArray[prev], coins[coinIdx]);
            }
            setBits[i] = setBits[prev] + 1;
        }
        
        long low = 1;
        long high = minCoin * k;
        long ans = high;
        
        // Binary search for the k-th smallest amount
        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            long count = 0;
            // Apply Principle of Inclusion-Exclusion
            for (int i = 1; i < numSubsets; i++) {
                if (setBits[i] % 2 == 1) {
                    count += mid / lcmArray[i];
                } else {
                    count -= mid / lcmArray[i];
                }
            }
            
            if (count >= k) {
                ans = mid;
                high = mid - 1; // Try to find a smaller valid amount
            } else {
                low = mid + 1;
            }
        }
        
        return ans;
    }
    
    // Helper function to calculate Least Common Multiple (LCM)
    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
    
    // Helper function to calculate Greatest Common Divisor (GCD)
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}