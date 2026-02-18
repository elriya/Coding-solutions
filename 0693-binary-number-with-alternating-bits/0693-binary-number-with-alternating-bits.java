class Solution {
    public boolean hasAlternatingBits(int n) {
        int allOnes = n ^ (n >> 1);
        return ((long)allOnes & ((long)allOnes + 1)) == 0;
    }
}