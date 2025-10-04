import java.util.*;

class Solution {
    
    public int[] dx = {0, 1, 0, -1};   
    public int[] dy = {1, 0, -1, 0};
    public String[] placeBoard;
    public boolean[][] visited;
    public boolean farAns = true;
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        int placeIndex = 0;
        for (String[] place : places) {
            List<Point> people = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (place[i].charAt(j) == 'P') {
                        people.add(new Point(i, j));
                    }
                }
            }
            int ans = 1;
            // System.out.println(placeIndex+1 + "번째 대기실");
            for (Point p : people) {
                placeBoard = place;
                visited = new boolean[5][5];
                visited[p.x][p.y] = true;
                farAns = true;
                isFar(0, p);
                if (!farAns) {
                    // System.out.println("실패 들어감");
                    ans = 0;
                    break;
                }
            }
            answer[placeIndex] = ans;
            placeIndex++;
        }
        return answer;
    }
    
    private void isFar(int count, Point start) {
        if (count >= 1) {
            if (placeBoard[start.x].charAt(start.y) == 'P') {
                // System.out.println("[실패] 2보다 커짐: " + start.x + ", " + start.y);
                farAns = false;
                return;
            } 
        }
        if (count >= 2) {
            if (placeBoard[start.x].charAt(start.y) != 'P') {
                // System.out.println("[성공] 2보다 커짐: " + start.x + ", " + start.y);
                return;
            }
        }
        
        for (int i = 0; i < 4; i++) {
            int nx = start.x + dx[i];
            int ny = start.y + dy[i];
            if (inRange(nx, ny) && !visited[nx][ny] && placeBoard[start.x].charAt(start.y) != 'X') {
                visited[nx][ny] = true;
                count += 1;
                isFar(count, new Point(nx, ny));
                visited[nx][ny] = false;
                count -= 1;
            }
        }
        // System.out.println("[성공] 갈 경로가 없음: " + start.x + ", " + start.y);
        return;
     }
    
    private boolean inRange(int x, int y) {
        return x >= 0 && x < 5 && y >= 0 && y < 5;
    }
    
    class Point {
        int x;
        int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}