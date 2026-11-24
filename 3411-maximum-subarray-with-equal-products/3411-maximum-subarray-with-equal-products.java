import java.math.BigInteger;

class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            BigInteger prod = BigInteger.ONE;
            long gcdVal = nums[i];
            long lcmVal = nums[i];

            for (int j = i; j < n; j++) {
                long cur = nums[j];
                prod = prod.multiply(BigInteger.valueOf(cur));
                gcdVal = gcd(gcdVal, cur);
                lcmVal = lcm(lcmVal, cur);

                BigInteger rhs = BigInteger.valueOf(lcmVal).multiply(BigInteger.valueOf(gcdVal));
                if (prod.equals(rhs)) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}