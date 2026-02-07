class Solution {
    public int minimumDeletions(String s) {
        int deletions = 0;
        int bCount = 0;

        for (char c : s.toCharArray()) {
            if (c == 'b') {
                bCount++;
            } else {
                deletions = Math.min(deletions + 1, bCount);
            }
        }

        return deletions;
    }
}