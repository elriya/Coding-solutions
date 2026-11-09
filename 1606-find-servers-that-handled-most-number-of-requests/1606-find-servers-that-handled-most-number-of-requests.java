import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }
        
        int[] requestsHandled = new int[k];
        int maxHandled = 0;
        
        for (int i = 0; i < arrival.length; i++) {
            int currTime = arrival[i];
            int currLoad = load[i];
            
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currTime) {
                int[] freed = busyServers.poll();
                availableServers.add(freed[1]);
            }
            
            if (availableServers.isEmpty()) {
                continue;
            }
            
             Integer server = availableServers.ceiling(i % k);
            if (server == null) {
                server = availableServers.first();
            }
            
            availableServers.remove(server);
            busyServers.offer(new int[] { currTime + currLoad, server });
            requestsHandled[server]++;
            maxHandled = Math.max(maxHandled, requestsHandled[server]);
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestsHandled[i] == maxHandled) {
                result.add(i);
            }
        }
        
        return result;
    }
}