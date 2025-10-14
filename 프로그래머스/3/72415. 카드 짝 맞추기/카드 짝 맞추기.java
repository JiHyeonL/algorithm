import java.util.*;

class Solution {
    
    private int[][] board;
    private Map<Integer, List<Point>> cards = new HashMap<>();
    private int n = 0;
    private boolean[] visited;
    private int[] dx = {0, 0, 1, -1};
    private int[] dy = {1, -1, 0, 0};
    private int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] board, int r, int c) {
        this.board = board;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] > 0) {
                    List<Point> points = cards.getOrDefault(board[i][j], new ArrayList<>());
                    points.add(new Point(i, j));
                    cards.put(board[i][j], points);
                    n = Math.max(n, board[i][j]);
                }
            }
        }
        visited = new boolean[n+1];
        comb_dfs(0, new Point(r, c), 0);
        return answer;
    }
    
    private void comb_dfs(int count, Point now, int pathSum) {
        if (count == n) {
            answer = Math.min(answer, pathSum);
            return;
        }
        
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                // 1 -> 2 방문
                Point first = cards.get(i).get(0);
                Point second = cards.get(i).get(1);
                int firstPath = distance_bfs(now, first);
                int secondPath = distance_bfs(first, second);
                visited[i] = true;
                board[first.x][first.y] = 0;
                board[second.x][second.y] = 0;
                comb_dfs(count + 1, second, pathSum + firstPath + secondPath + 2);
                visited[i] = false;
                board[first.x][first.y] = i;
                board[second.x][second.y] = i;
                
                // 2 -> 1 방문
                first = cards.get(i).get(1);
                second = cards.get(i).get(0);
                firstPath = distance_bfs(now, first);
                secondPath = distance_bfs(first, second);
                visited[i] = true;
                board[first.x][first.y] = 0;
                board[second.x][second.y] = 0;
                comb_dfs(count + 1, second, pathSum + firstPath + secondPath + 2);
                visited[i] = false;
                board[first.x][first.y] = i;
                board[second.x][second.y] = i;
            }
        }
    }
    
    private int distance_bfs(Point start, Point end) {
        Queue<Point> queue = new LinkedList<>();
        int[][] visited = new int[4][4];
        queue.add(start);
        visited[start.x][start.y] = 1;
            
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            if (now.isSame(end)) {
                break;
            }
            // 1칸
            for (int i = 0; i < 4 ;i++) {
                int nx = now.x + dx[i] ;
                int ny = now.y + dy[i];
                if (inRange(nx, ny) && visited[nx][ny] == 0) {
                    visited[nx][ny] = visited[now.x][now.y] + 1;
                    queue.add(new Point(nx, ny));
                }
            }
            // ctrl
            for (int i = 0; i < 4; i++) {
                int nx = now.x;
                int ny = now.y;
                while (inRange(nx, ny)) {
                    nx += dx[i];
                    ny += dy[i];
                    if (!inRange(nx, ny)) {
                        nx -= dx[i];
                        ny -= dy[i];
                        break;
                    }
                    if (board[nx][ny] != 0) {
                        break;
                    }
                }
                if (visited[nx][ny] == 0) {
                    visited[nx][ny] = visited[now.x][now.y] + 1;
                    queue.add(new Point(nx, ny));    
                }
            }
        }
        return visited[end.x][end.y] -1;
    }
    
    private boolean inRange(int x, int y) {
        return x >= 0 && x < 4 && y >= 0 && y < 4;
    }
    
    class Point {
        int x;
        int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public boolean isSame(Point point) {
            return x == point.x && y == point.y;
        }
    }
    
    // 
}