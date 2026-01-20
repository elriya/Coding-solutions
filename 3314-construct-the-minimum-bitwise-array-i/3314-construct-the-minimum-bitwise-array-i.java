import java.util.*;

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            int val = nums.get(i);
            
            if (val == 2) {
                ans[i] = -1;
            } else {
                for (int b = 0; b < 31; b++) {
                    if (((val >> (b + 1)) & 1) == 0) {
                        ans[i] = val ^ (1 << b);
                        break;
                    }
                }
            }
        }
        
        return ans;
    }
}