import java.util.*;

class Solution {
    public String smallestNumber(String num, long t) {
        // Validate t's prime factors (must only be 2, 3, 5, 7)
        long tempT = t;
        while (tempT % 2 == 0) tempT /= 2;
        while (tempT % 3 == 0) tempT /= 3;
        while (tempT % 5 == 0) tempT /= 5;
        while (tempT % 7 == 0) tempT /= 7;
        
        if (tempT > 1) {
            return "-1"; // Prime factor other than 2, 3, 5, 7 exists
        }

        int n = num.length();
        int firstZero = num.indexOf('0');

        // Check if num itself is valid (only if zero-free)
        if (firstZero == -1 && isDivisible(num, t)) {
            return num;
        }

        // Limit search prefix length to firstZero if '0' exists
        int limit = (firstZero == -1) ? n - 1 : firstZero;

        // Precompute prefix products modulo t via GCD to avoid overflow
        long[] prefixNeeded = new long[n + 1];
        prefixNeeded[0] = t;
        
        for (int i = 0; i < n; i++) {
            if (num.charAt(i) == '0') break;
            int digit = num.charAt(i) - '0';
            prefixNeeded[i + 1] = prefixNeeded[i] / gcd(prefixNeeded[i], digit);
        }

        // Backtrack from right to left (up to limit)
        for (int i = limit; i >= 0; i--) {
            int currentDigit = num.charAt(i) - '0';
            long reqBeforeI = prefixNeeded[i];
            
            for (int d = currentDigit + 1; d <= 9; d++) {
                long neededAfterD = reqBeforeI / gcd(reqBeforeI, d);
                
                String minSuffix = getMinSuffix(neededAfterD, n - 1 - i);
                if (minSuffix != null) {
                    StringBuilder result = new StringBuilder();
                    result.append(num, 0, i);
                    result.append(d);
                    result.append(minSuffix);
                    return result.toString();
                }
            }
        }

        // If no length n string works, construct length n + 1
        String suffix = getMinSuffix(t, n + 1);
        if (suffix != null) {
            return suffix;
        }

        int len = n + 1;
        while (true) {
            suffix = getMinSuffix(t, len);
            if (suffix != null) return suffix;
            len++;
        }
    }

    private boolean isDivisible(String num, long t) {
        long req = t;
        for (int i = 0; i < num.length(); i++) {
            req /= gcd(req, num.charAt(i) - '0');
        }
        return req == 1;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private String getMinSuffix(long req, int targetLen) {
        if (req == 1) {
            char[] arr = new char[targetLen];
            Arrays.fill(arr, '1');
            return new String(arr);
        }

        int c2 = 0, c3 = 0, c5 = 0, c7 = 0;
        long temp = req;
        while (temp % 2 == 0) { temp /= 2; c2++; }
        while (temp % 3 == 0) { temp /= 3; c3++; }
        while (temp % 5 == 0) { temp /= 5; c5++; }
        while (temp % 7 == 0) { temp /= 7; c7++; }

        List<Integer> reqDigits = new ArrayList<>();
        
        while (c3 >= 2) { reqDigits.add(9); c3 -= 2; }
        while (c2 >= 3) { reqDigits.add(8); c2 -= 3; }
        while (c7 >= 1) { reqDigits.add(7); c7 -= 1; }
        while (c2 >= 1 && c3 >= 1) { reqDigits.add(6); c2--; c3--; }
        while (c5 >= 1) { reqDigits.add(5); c5 -= 1; }
        while (c2 >= 2) { reqDigits.add(4); c2 -= 2; }
        while (c3 >= 1) { reqDigits.add(3); c3 -= 1; }
        while (c2 >= 1) { reqDigits.add(2); c2 -= 1; }

        if (reqDigits.size() > targetLen) {
            return null;
        }

        Collections.sort(reqDigits);

        StringBuilder sb = new StringBuilder();
        int onesNeeded = targetLen - reqDigits.size();
        for (int i = 0; i < onesNeeded; i++) {
            sb.append('1');
        }
        for (int d : reqDigits) {
            sb.append(d);
        }

        return sb.toString();
    }
}