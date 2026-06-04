class Solution {
    public int totalWaviness(int num1, int num2) {
        int totalSum = 0;
        
        for (int i = num1; i <= num2; i++) {
            totalSum += calculateWaviness(i);
        }
        
        return totalSum;
    }
    
    private int calculateWaviness(int num) {
        if (num < 100) {
            return 0;
        }
        
        String s = Integer.toString(num);
        int waviness = 0;
        int n = s.length();
        
        for (int i = 1; i < n - 1; i++) {
            char curr = s.charAt(i);
            char prev = s.charAt(i - 1);
            char next = s.charAt(i + 1);
            
            if (curr > prev && curr > next) {
                waviness++;
            }
            else if (curr < prev && curr < next) {
                waviness++;
            }
        }
        
        return waviness;
    }
}