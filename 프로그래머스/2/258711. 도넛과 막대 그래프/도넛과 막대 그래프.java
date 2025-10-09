import java.util.*;

class Solution {
    
    private Map<Integer, List<Integer>> graph = new HashMap<>();
     private Map<Integer, Integer> inCount = new HashMap<>();
     private Map<Integer, Integer> outCount = new HashMap<>();
    private int n;
    private int[] answer = {0,0,0,0}; // 정점, 도넛, 막대, 8자
    
    public int[] solution(int[][] edges) {
        for (int[] edge : edges) {
            List<Integer> next = graph.getOrDefault(edge[0], new ArrayList<>());
            next.add(edge[1]);
            graph.put(edge[0], next);
            n = Math.max(Math.max(n, edge[0]), edge[1]);
        }
        setInAndOut();
        for (int key : outCount.keySet()) {
            if (outCount.get(key) >= 2 && !inCount.containsKey(key)) {
                answer[0] = key;
                break;
            }
        }
        for (int node : graph.get(answer[0])) {
            inCount.put(node, inCount.get(node) - 1);
        }
        for (int key : outCount.keySet()) {
            if (answer[0] == key) {
                continue;
            }
            if (outCount.get(key) == 2 && inCount.get(key) == 2) {
                answer[3]++;
            }
        }
        for (int key : inCount.keySet()) {
            if (answer[0] == key) {
                continue;
            }
            if (!outCount.containsKey(key) && inCount.get(key) <= 1) {
                answer[2]++;
            }
        }
        
        answer[1] = outCount.get(answer[0]) - answer[2] - answer[3];
        return answer;
    }
    
    private void setInAndOut() {
        for (int key : graph.keySet()) {
            outCount.put(key, graph.get(key).size());
            for (int in : graph.get(key)) {
                inCount.put(in, inCount.getOrDefault(in, 0) + 1);
            }
        }
    }
}


// 도넛 중심: 암거나 잡아도 ok. 나가는거 하나 들어오는거 하나
// 막대 중심: 들어오는거 없고 나가는거 하나
// 8자 중심: 나가는거 2개 들어오는거 2개
// 새로 만든 노드: 들어오는거 없고 나가는거 2개 이상