class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int lCount = 0;
        int rCount = 0;
        int uCount = 0;

        for (char move : moves.toCharArray()) {
            if (move == 'L') {
                lCount++;
            } else if (move == 'R') {
                rCount++;
            } else {
                uCount++; 
            }
        }

        return Math.abs(lCount - rCount) + uCount;
    }
}