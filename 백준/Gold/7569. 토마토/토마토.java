import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class Main {

    private static int n;
    private static int m;
    private static int h;
    private static int[][][] box;
    private static int[] dx = {0, 0, 0, 0, 1, -1};
    private static int[] dy = {0, 0, -1, 1, 0, 0};
    private static int[] dh = {-1, 1, 0, 0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        m = Integer.parseInt(temp[0]);
        n = Integer.parseInt(temp[1]);
        h = Integer.parseInt(temp[2]);

        box = new int[h][n][m];
        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                temp = br.readLine().split(" ");
                for (int j = 0; j < m; j++) {
                    box[k][i][j] = Integer.parseInt(temp[j]);
                }
            }
        }

        int answer = bfs();
        System.out.println(answer);
    }

    private static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (box[k][i][j] == 1) {
                        queue.add(new Point(k, i, j));
                    }
                }
            }
        }
        while (!queue.isEmpty()) {
            Point tot = queue.poll();
            for (int i = 0; i < 6; i++) {
                int nx = tot.x + dx[i];
                int ny = tot.y + dy[i];
                int nh = tot.k + dh[i];
                if (inRange(nh, nx, ny) && box[nh][nx][ny] == 0) {
                    queue.add(new Point(nh, nx, ny));
                    box[nh][nx][ny] = box[tot.k][tot.x][tot.y] + 1;
                }
            }
        }
        int answer = 0;
        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (box[k][i][j] == 0) {
                        return -1;
                    }
                    if (box[k][i][j] > 0) {
                        answer = Math.max(answer, box[k][i][j]);
                    }
                }
            }
        }
        if (answer == 1) {
            return 0;
        }
        return answer - 1;
    }

    private static boolean inRange(int k, int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m && k >= 0 && k < h;
    }

    static class Point {
        int k;
        int x;
        int y;

        public Point(int k, int x, int y) {
            this.k = k;
            this.x = x;
            this.y = y;
        }
    }
}
