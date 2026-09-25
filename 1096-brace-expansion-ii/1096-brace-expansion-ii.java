import java.util.*;

class Solution {
    private int idx;

    public List<String> braceExpansionII(String expression) {
        idx = 0;
        Set<String> res = parse(expression);
        List<String> ans = new ArrayList<>(res);
        Collections.sort(ans);
        return ans;
    }

    private Set<String> parse(String s) {
        Set<String> result = new HashSet<>();
        List<Set<String>> currentGroup = new ArrayList<>();
        currentGroup.add(new HashSet<>(Arrays.asList("")));

        while (idx < s.length() && s.charAt(idx) != '}') {
            char c = s.charAt(idx);
            if (c == ',') {
                result.addAll(multiply(currentGroup));
                currentGroup.clear();
                currentGroup.add(new HashSet<>(Arrays.asList("")));
                idx++;
            } else if (c == '{') {
                idx++; // skip '{'
                Set<String> sub = parse(s);
                idx++; // skip '}'
                currentGroup.add(sub);
            } else {
                Set<String> sub = new HashSet<>();
                sub.add(String.valueOf(c));
                currentGroup.add(sub);
                idx++;
            }
        }
        result.addAll(multiply(currentGroup));
        return result;
    }

    private Set<String> multiply(List<Set<String>> groups) {
        Set<String> prev = new HashSet<>();
        prev.add("");
        for (Set<String> group : groups) {
            Set<String> next = new HashSet<>();
            for (String p : prev) {
                for (String g : group) {
                    next.add(p + g);
                }
            }
            prev = next;
        }
        return prev;
    }
}