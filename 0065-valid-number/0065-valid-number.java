class Solution {
    public boolean isNumber(String s) {
        boolean digitSeen = false;
        boolean dotSeen = false;
        boolean eSeen = false;
        boolean digitAfterE = true;

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);

            if (Character.isDigit(curr)) {
                digitSeen = true;
                digitAfterE = true;
            } else if (curr == '+' || curr == '-') {
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (curr == '.') {
                if (dotSeen || eSeen) {
                    return false;
                }
                dotSeen = true;
            } else if (curr == 'e' || curr == 'E') {
            
                if (eSeen || !digitSeen) {
                    return false;
                }
                eSeen = true;
                digitAfterE = false; 
            } else {
                return false;
            }
        }

        return digitSeen && digitAfterE;
    }
}