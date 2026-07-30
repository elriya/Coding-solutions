class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int totalPushes = 0;

        for (int i = 0; i < n; i++) {
            // Determine position on the key (1st, 2nd, 3rd, or 4th press)
            int pushCount = (i / 8) + 1;
            totalPushes += pushCount;
        }

        return totalPushes;
    }
}