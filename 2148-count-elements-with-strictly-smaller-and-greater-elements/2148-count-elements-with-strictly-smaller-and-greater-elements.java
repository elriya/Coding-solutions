class Solution {
    public int countElements(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int num : nums) {
            if (num < min) min = num;
            if (num > max) max = num;
        }
        
        int count = 0;
        for (int num : nums) {
            if (num > min && num < max) {
                count++;
            }
        }
        
        return count;
    }
}