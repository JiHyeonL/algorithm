import java.util.*;

class Solution {
    
    private int n;
    private Map<Integer, List<Integer>> edges = new HashMap<>();
    private int[] visited;
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        this.n = n;
        for (int[] e : edge) {
            List<Integer> value = edges.getOrDefault(e[0], new ArrayList<>());
            value.add(e[1]);
            edges.put(e[0], value);
            
            value = edges.getOrDefault(e[1], new ArrayList<>());
            value.add(e[0]);
            edges.put(e[1], value);
        }
        bfs(1);
        Arrays.sort(visited);
        int maxValue = visited[n];
        for (int i = n; i >= 0; i--) {
            if (visited[i] == maxValue) {
                answer++;
            } else {
                break;
            }
        }
        return answer;
    }
    
    private void bfs(int start) {
        visited = new int[n+1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = 1;
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            
            for (int next : edges.get(node)) {
                if (visited[next] == 0) {
                    visited[next] = visited[node] + 1;
                    queue.add(next);
                }
            }
        }
    }
}