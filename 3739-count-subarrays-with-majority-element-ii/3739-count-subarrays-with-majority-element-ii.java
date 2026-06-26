class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        long count = 0;
        
        int offset = n + 1;
        FenwickTree bit = new FenwickTree(2 * n + 2);
        
        // Shifted value for 0 is 0 + offset.
        int currentPrefixSum = 0;
        bit.update(currentPrefixSum + offset, 1);
        
        for (int num : nums) {
            // Update prefix sum based on our transformation
            if (num == target) {
                currentPrefixSum += 1;
            } else {
                currentPrefixSum -= 1;
            }
            
            int shiftedSum = currentPrefixSum + offset;
            
            // range [1, shiftedSum - 1].
            count += bit.query(shiftedSum - 1);
            
            // Add the current prefix sum to the Fenwick Tree
            bit.update(shiftedSum, 1);
        }
        
        return count;
    }
    
    // Standard Fenwick Tree (Binary Indexed Tree) implementation
    class FenwickTree {
        private int[] tree;
        private int size;
        
        public FenwickTree(int size) {
            this.size = size;
            this.tree = new int[size + 1];
        }
        
        public void update(int index, int delta) {
            while (index <= size) {
                tree[index] += delta;
                index += index & (-index);
            }
        }
        
        public int query(int index) {
            int sum = 0;
            while (index > 0) {
                sum += tree[index];
                index -= index & (-index);
            }
            return sum;
        }
    }
}