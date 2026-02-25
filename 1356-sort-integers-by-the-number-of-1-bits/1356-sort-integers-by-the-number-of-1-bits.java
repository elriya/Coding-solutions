import java.util.Arrays;

class Solution {
    public int[] sortByBits(int[] arr) {
        return Arrays.stream(arr)
            .boxed()
            .sorted((a, b) -> {
                int bitCountA = Integer.bitCount(a);
                int bitCountB = Integer.bitCount(b);
                
                if (bitCountA == bitCountB) {
                    return a - b;
                }
                return bitCountA - bitCountB;
            })
            .mapToInt(i -> i)
            .toArray();
    }
}