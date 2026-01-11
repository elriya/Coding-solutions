class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        int count = 1 << n; // 2^n
        for (int i = 0; i < count; i++) {
            result.add(i ^ (i >> 1));
        }
        return result;
    }
}