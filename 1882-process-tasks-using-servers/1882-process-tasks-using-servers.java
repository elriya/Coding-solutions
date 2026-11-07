import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]); // compare weights
            }
            return Integer.compare(a[1], b[1]); // compare indices
        });

        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]); // compare free time
            }
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]); // compare weights
            }
            return Integer.compare(a[2], b[2]); // compare indices
        });

        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[] { servers[i], i });
        }

        long currentTime = 0;

        for (int j = 0; j < m; j++) {
            currentTime = Math.max(currentTime, j);

            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                int[] busy = busyServers.poll();
                freeServers.offer(new int[] { busy[1], busy[2] }); // [weight, index]
            }

            if (freeServers.isEmpty()) {
                currentTime = busyServers.peek()[0];
                while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                    int[] busy = busyServers.poll();
                    freeServers.offer(new int[] { busy[1], busy[2] });
                }
            }

            int[] bestServer = freeServers.poll();
            int weight = bestServer[0];
            int index = bestServer[1];

            ans[j] = index;
            busyServers.offer(new int[] { (int) currentTime + tasks[j], weight, index });
        }

        return ans;
    }
}