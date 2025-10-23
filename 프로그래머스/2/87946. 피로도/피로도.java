import java.util.*;

class Solution {
    
    private int answer = 0;
    private int n;
    private int[][] dungeons;
    private boolean[] visited;

    public int solution(int k, int[][] dungeons) {
        n = dungeons.length;
        this.dungeons = dungeons;
        visited = new boolean[n];
        backTracking(k, 0);
        return answer;
    }
    
    private void backTracking(int k, int dungeonCount) {
        if (allVisited()) {
            answer = Math.max(answer, dungeonCount);
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) { 
                if (dungeons[i][0] <= k) {  // 방문 가능
                    visited[i] = true;
                    backTracking(k - dungeons[i][1], dungeonCount + 1); // 방문
                    visited[i] = false;
                    visited[i] = true;
                    backTracking(k, dungeonCount); // 미방문
                    visited[i] = false;
                }
                else {
                    visited[i] = true;
                    backTracking(k, dungeonCount); // 미방문
                    visited[i] = false;
                }   
            }
        }
    }
    
    private boolean allVisited() {
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}
