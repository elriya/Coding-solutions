import java.util.HashSet;
import java.util.Set;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        
        // Collect all possible pair XOR values (a ^ b)
        // Max nums[i] <= 1500, so any XOR value < 2048
        boolean[] pairXor = new boolean[2048];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                pairXor[nums[i] ^ nums[j]] = true;
            }
        }
        
        // Combine pair XORs with every nums[k] to find all triplet XORs
        boolean[] tripletXor = new boolean[2048];
        for (int p = 0; p < 2048; p++) {
            if (pairXor[p]) {
                for (int num : nums) {
                    tripletXor[p ^ num] = true;
                }
            }
        }
        
        // Count total unique triplet values
        int uniqueCount = 0;
        for (boolean present : tripletXor) {
            if (present) {
                uniqueCount++;
            }
        }
        
        return uniqueCount;
    }
}