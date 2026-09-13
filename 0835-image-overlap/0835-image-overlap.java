class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        java.util.List<int[]> list1 = new java.util.ArrayList<>();
        java.util.List<int[]> list2 = new java.util.ArrayList<>();
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) {
                    list1.add(new int[]{r, c});
                }
                if (img2[r][c] == 1) {
                    list2.add(new int[]{r, c});
                }
            }
        }
        
        java.util.Map<String, Integer> map = new java.util.HashMap<>();
        int maxOverlap = 0;
        
        for (int[] p1 : list1) {
            for (int[] p2 : list2) {
                String vector = (p2[0] - p1[0]) + "," + (p2[1] - p1[1]);
                map.put(vector, map.getOrDefault(vector, 0) + 1);
                maxOverlap = Math.max(maxOverlap, map.get(vector));
            }
        }
        
        return maxOverlap;
    }
}