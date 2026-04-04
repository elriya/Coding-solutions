class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        if (rows == 1) return encodedText;
        
        int n = encodedText.length();
        int cols = n / rows;
        StringBuilder res = new StringBuilder();
        
        for (int i = 0; i < cols; i++) {
            for (int r = 0, c = i; r < rows && c < cols; r++, c++) {
                int index = r * cols + c;
                res.append(encodedText.charAt(index));
            }
        }
        
        int lastIdx = res.length() - 1;
        while (lastIdx >= 0 && res.charAt(lastIdx) == ' ') {
            lastIdx--;
        }
        
        return res.substring(0, lastIdx + 1);
    }
}