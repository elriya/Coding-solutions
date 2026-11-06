import java.util.Arrays;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        int[] result = new int[m];
        int[] serverCount = new int[n + 1]; 
        int activeServers = 0;
        
        int logIndex = 0;
        int windowStart = 0; 
        
        for (int i = 0; i < m; i++) {
            int qTime = sortedQueries[i][0];
            int originalIndex = sortedQueries[i][1];
            
            int startTime = qTime - x;
            int endTime = qTime;
            
            while (logIndex < logs.length && logs[logIndex][1] <= endTime) {
                int serverId = logs[logIndex][0];
                if (serverCount[serverId] == 0) {
                    activeServers++;
                }
                serverCount[serverId]++;
                logIndex++;
            }
            
            while (windowStart < logIndex && logs[windowStart][1] < startTime) {
                int serverId = logs[windowStart][0];
                serverCount[serverId]--;
                if (serverCount[serverId] == 0) {
                    activeServers--;
                }
                windowStart++;
            }
            
            result[originalIndex] = n - activeServers;
        }
        
        return result;
    }
}