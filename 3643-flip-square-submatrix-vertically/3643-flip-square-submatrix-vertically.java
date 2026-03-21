class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        for (int i = 0; i < k / 2; i++) {
            // Row indices to swap
            int topRow = x + i;
            int bottomRow = x + k - 1 - i;
            
            // Iterate through each column in the submatrix width
            for (int j = 0; j < k; j++) {
                int col = y + j;
                
                int temp = grid[topRow][col];
                grid[topRow][col] = grid[bottomRow][col];
                grid[bottomRow][col] = temp;
            }
        }
        
        return grid;
    }
}