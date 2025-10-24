import java.util.*;

class Solution {
    
    private List<Bridge> bridges = new ArrayList<>();
    private List<Integer> parent = new ArrayList<>();
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        for (int[] cost : costs) {
            int start = Math.min(cost[0], cost[1]);
            int end = Math.max(cost[0], cost[1]);
            bridges.add(new Bridge(start, end, cost[2]));
        }
        for (int i = 0; i < n; i++) {
            parent.add(i);
        }
        Collections.sort(bridges, (o1, o2) -> {
            if (o1.cost < o2.cost) {
                return -1;
            } else if (o1.cost == o2.cost) {
                return 0;
            }
            return 1;
        });
        for (Bridge bridge : bridges) {
            if (!findCycle(bridge.start, bridge.end)) {
                answer += bridge.cost;
                union(bridge.start, bridge.end);
            }
        }
        return answer;
    }
    
    private boolean findCycle(int start, int end) {
        return getParent(start) == getParent(end);
    }
    
    private int getParent(int child) {
        if (parent.get(child) == child) {
            return child;
        }
        parent.set(child, getParent(parent.get(child)));
        return parent.get(child);
    }
    
    private void union(int start, int end) {
        start = getParent(start);
        end = getParent(end);
        if (start != end) {
            parent.set(end, start);
        }
    }
    
    static class Bridge {
        int start;
        int end;
        int cost;
        
        public Bridge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}

// 1 2 3 ... n 섬
// 모든 섬을 통행 가능하도록 하는 최소 비용


// 0        1
// 
// 
//
// 2        3
// 사이클 없음
