class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[] {-1, -1};
        }
        
        int firstCriticalIndex = -1;
        int lastCriticalIndex = -1;
        int minDistance = Integer.MAX_VALUE;
        
        ListNode prev = head;
        ListNode curr = head.next;
        int index = 1;
        
        // Traverse 
        while (curr.next != null) {
            ListNode next = curr.next;
            
            // current node is a local maxima or local minima
            if ((curr.val > prev.val && curr.val > next.val) || 
                (curr.val < prev.val && curr.val < next.val)) {
                
                // If it's the first critical point found
                if (firstCriticalIndex == -1) {
                    firstCriticalIndex = index;
                } else {
                    // distance between adjacent critical points
                    minDistance = Math.min(minDistance, index - lastCriticalIndex);
                }
                
                lastCriticalIndex = index;
            }
            
            prev = curr;
            curr = next;
            index++;
        }
        
        // If there are fewer than two critical points, return [-1, -1]
        if (firstCriticalIndex == -1 || firstCriticalIndex == lastCriticalIndex) {
            return new int[] {-1, -1};
        }
        
        // difference between the first and the last critical point
        int maxDistance = lastCriticalIndex - firstCriticalIndex;
        
        return new int[] {minDistance, maxDistance};
    }
}