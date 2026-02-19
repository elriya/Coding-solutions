import java.math.BigInteger;

class Solution {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i <= n / 2; i++) {
            if (num.charAt(0) == '0' && i > 1) break;
            
            for (int j = i + 1; n - j >= Math.max(i, j - i); j++) {
                if (num.charAt(i) == '0' && j - i > 1) break;
                
                String num1 = num.substring(0, i);
                String num2 = num.substring(i, j);
                
                if (isValid(num1, num2, j, num)) return true;
            }
        }
        return false;
    }

    private boolean isValid(String s1, String s2, int start, String num) {
        if (start == num.length()) return true;
        
        BigInteger b1 = new BigInteger(s1);
        BigInteger b2 = new BigInteger(s2);
        String sumStr = b1.add(b2).toString();
        
        if (!num.startsWith(sumStr, start)) return false;
        
        return isValid(s2, sumStr, start + sumStr.length(), num);
    }
}