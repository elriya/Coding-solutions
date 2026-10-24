class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int arriveAliceDay = calculateDayOfYear(arriveAlice);
        int leaveAliceDay = calculateDayOfYear(leaveAlice);
        int arriveBobDay = calculateDayOfYear(arriveBob);
        int leaveBobDay = calculateDayOfYear(leaveBob);
        
        int overlapStart = Math.max(arriveAliceDay, arriveBobDay);
        int overlapEnd = Math.min(leaveAliceDay, leaveBobDay);
        
        if (overlapStart <= overlapEnd) {
            return overlapEnd - overlapStart + 1;
        }
        
        return 0;
    }
    
    private int calculateDayOfYear(String date) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        
        int dayOfYear = day;
        for (int i = 0; i < month - 1; i++) {
            dayOfYear += daysInMonths[i];
        }
        return dayOfYear;
    }
}