class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Calculate the prefix sums in-place to save space
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }
        
        // if we are forced to take the entire array
        int maxDifference = stones[n - 1];
        
        // Work backwards from the second to last possible choice down to index 1
        // (Index 0 isn't allowed because a player must take at least x > 1 stones)
        for (int i = n - 2; i >= 1; i--) {
            maxDifference = Math.max(maxDifference, stones[i] - maxDifference);
        }
        
        return maxDifference;
    }
}