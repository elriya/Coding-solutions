class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];
        
        StringBuilder sb0 = new StringBuilder();
        for (int j = 0; j < n; j++) {
            sb0.append('.');
        }
        grid[0] = sb0.toString();
        
        for (int i = 1; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n - 1; j++) {
                sb.append('#');
            }
            sb.append('.'); 
            grid[i] = sb.toString();
        }
        
        return grid;
    }
}