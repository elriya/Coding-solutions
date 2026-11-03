class Solution {
    public int minMoves(int[] nums) {
        int minMoves = 0;
        int max = nums[0];
        
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        
        for (int num : nums) {
            minMoves += (max - num);
        }
        
        return minMoves;
    }
}