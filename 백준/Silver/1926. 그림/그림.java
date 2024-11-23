import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static int n;
    public static int m;
    public static final int[] dx = {1, 0, -1, 0};
    public static final int[] dy = {0, -1, 0, 1};
    public static int[][] board;
    public static boolean[][] visited;
    public static int max = 0;
    public static int paintCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);

        board = new int[n+1][m+1];
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(temp[j]);
            }
        }

        visited = new boolean[n+1][m+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && board[i][j] == 1) {
                    paintCount++;
                    bfs(new Point(i, j));
                }
            }
        }
        System.out.println(paintCount + "\n" + max);
    }

    private static void bfs(Point start) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y] = true;

        int count = 1;
        while (!queue.isEmpty()) {
            Point node = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if (inRange(nx, ny) && !visited[nx][ny] && board[nx][ny] == 1) {
                    queue.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                    count++;
                }
            }
        }
        max = Math.max(max, count);
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
