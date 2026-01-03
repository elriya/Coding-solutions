class Solution {
    public int[] searchRange(int[] nums, int target) {
        int x = -1, y = -1;
        int n = nums.length;

        // Find First Position
        int lx = 0, hx = n - 1;
        while (lx <= hx) {
            int midx = lx + (hx - lx) / 2;
            if (nums[midx] == target) {
                x = midx;
                hx = midx - 1; 
            } else if (nums[midx] > target) {
                hx = midx - 1;
            } else {
                lx = midx + 1;
            }
        }

        // If x is still -1, target doesn't exist.
        if (x == -1) return new int[]{-1, -1};

        //Find Rightmost Last Position
        int ly = 0, hy = n - 1;
        while (ly <= hy) {
            int midy = ly + (hy - ly) / 2;
            if (nums[midy] == target) {
                y = midy;
                ly = midy + 1; 
            } else if (nums[midy] > target) {
                hy = midy - 1;
            } else {
                ly = midy + 1;
            }
        }

        return new int[]{x, y};
    }
}