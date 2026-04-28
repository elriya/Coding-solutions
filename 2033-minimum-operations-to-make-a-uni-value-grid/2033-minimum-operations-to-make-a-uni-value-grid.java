import java.util.Arrays;

class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        int totalElements = m * n;
        int[] arr = new int[totalElements];
        
        int idx = 0;
        for (int[] row : grid) {
            for (int val : row) {
                arr[idx++] = val;
            }
        }
        
        int targetRemainder = arr[0] % x;
        for (int num : arr) {
            if (num % x != targetRemainder) {
                return -1;
            }
        }
        
        Arrays.sort(arr);
        int median = arr[totalElements / 2];
        
        int ops = 0;
        for (int num : arr) {
            ops += Math.abs(num - median) / x;
        }
        
        return ops;
    }
}