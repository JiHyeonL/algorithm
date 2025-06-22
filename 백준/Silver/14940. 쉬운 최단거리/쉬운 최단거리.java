import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class Main {

    private static int n;
    private static int m;
    private static int[][] maps;
    private static Point start;
    private static int[][] dp;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        maps = new int[n][m];
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                maps[i][j] = Integer.parseInt(temp[j]);
                if (maps[i][j] == 2) {
                    start = new Point(i, j);
                }
            }
        }

        bfs();
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) {
                    if (maps[i][j] == 0) {
                        answer.append(0).append(" ");
                    } else {
                        answer.append(-1).append(" ");
                    }
                } else {
                    answer.append(dp[i][j]).append(" ");
                }
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }

    private static void bfs() {
        dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        boolean[][] visited = new boolean[n][m];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(start.x, start.y));
        visited[start.x][start.y] = true;
        dp[start.x][start.y] = 0;

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + point.x;
                int ny = dy[i] + point.y;
                if (inRange(nx, ny) && !visited[nx][ny] && maps[nx][ny] != 0) {
                    dp[nx][ny] = dp[point.x][point.y] + 1;
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                }
            }
        }
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
