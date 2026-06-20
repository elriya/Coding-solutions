import java.util.ArrayList;
import java.util.List;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 0});
        
        for (int[] res : restrictions) {
            list.add(res);
        }
        
        list.sort((a, b) -> Integer.compare(a[0], b[0]));
        
        if (list.get(list.size() - 1)[0] != n) {
            list.add(new int[]{n, n - 1});
        }
        
        int m = list.size();
        
        for (int i = 1; i < m; i++) {
            int[] prev = list.get(i - 1);
            int[] curr = list.get(i);
            curr[1] = Math.min(curr[1], prev[1] + (curr[0] - prev[0]));
        }
        
        for (int i = m - 2; i >= 0; i--) {
            int[] next = list.get(i + 1);
            int[] curr = list.get(i);
            curr[1] = Math.min(curr[1], next[1] + (next[0] - curr[0]));
        }
        
        int maxAns = 0;
        for (int i = 0; i < m - 1; i++) {
            int[] left = list.get(i);
            int[] right = list.get(i + 1);
            int id1 = left[0], h1 = left[1];
            int id2 = right[0], h2 = right[1];
            
            int peak = (h1 + h2 + (id2 - id1)) / 2;
            maxAns = Math.max(maxAns, peak);
        }
        
        return maxAns;
    }
}