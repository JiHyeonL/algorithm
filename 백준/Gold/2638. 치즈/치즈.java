import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static int n;
    public static int m;
    public static int[][] cheeses;
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        cheeses = new int[n][m];
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                cheeses[i][j] = Integer.parseInt(temp[j]);
            }
        }

        int answer = 0;
        // 녹을 치즈 = 2
        // 치즈 = 1
        // 바깥 공기 = -1
        // 치즈 안 공기 = 0
        while (true) {
            findOutsideAir();
            boolean existMelt = false;
            for (int i = 1; i < n - 1; i++) {
                for (int j = 1; j < m - 1; j++) {
                    if (cheeses[i][j] == 1 && isMelt(i, j)) {
                        cheeses[i][j] = 2;
                        existMelt = true;
                    }
                }
            }
            if (!existMelt) {
                break;
            }
            for (int i = 1; i < n - 1; i++) {
                for (int j = 1; j < m - 1; j++) {
                    if (cheeses[i][j] == 2) {
                        cheeses[i][j] = 0;
                    }
                }
            }
            answer++;
        }
        System.out.println(answer);
    }

    private static void findOutsideAir() {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        queue.add(new Point(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (inRange(nx, ny) && !visited[nx][ny] && cheeses[nx][ny] != 1) {
                    cheeses[nx][ny] = -1;
                    queue.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    private static boolean isMelt(int x, int y) {
        int airCount = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (airCount >= 2) {
                return true;
            }
            if (inRange(nx, ny) && cheeses[nx][ny] == -1) {
                airCount++;
            }
        }
        return airCount >= 2;
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

        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
}
