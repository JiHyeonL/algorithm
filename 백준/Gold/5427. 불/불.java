import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Main {

    private static int t;
    private static int w;
    private static int h;
    private static String[][] building;
    private static int[] dx = {0, 1, -1, 0};
    private static int[] dy = {1, 0, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String[] temp = br.readLine().split(" ");
            w = Integer.parseInt(temp[0]);
            h = Integer.parseInt(temp[1]);
            building = new String[h][w];
            Point start = null;
            List<Point> fires = new ArrayList<>();
            for (int j = 0; j < h; j++) {
                temp = br.readLine().split("");
                for (int k = 0; k < w; k++) {
                    building[j][k] = temp[k];
                    if (building[j][k].equals("@")) {
                        start = new Point(j, k);
                    }
                    if (building[j][k].equals("*")) {
                        fires.add(new Point(j, k));
                    }
                }
            }
            int[][] fireTime = fire_bfs(fires);
            String ans = move_bfs(fireTime, start);
            answer.append(ans).append("\n");
        }
        System.out.println(answer);
    }

    private static int[][] fire_bfs(List<Point> fires) {
        Queue<Point> queue = new LinkedList<>();
        queue.addAll(fires);
        int[][] time = new int[h][w];
        for (int j = 0; j < h; j++) {
            for (int k = 0; k < w; k++) {
                if (building[j][k].equals("#")) {
                    time[j][k] = -1;
                } else {
                    time[j][k] = Integer.MAX_VALUE;
                }
            }
        }
        for (Point fire : fires) {
            time[fire.i][fire.j] = 0;
        }
        while (!queue.isEmpty()) {
            Point fire = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = fire.i + dx[i];
                int ny = fire.j + dy[i];
                if (inRange(nx, ny) && time[nx][ny] == Integer.MAX_VALUE) {
                    time[nx][ny] = time[fire.i][fire.j] + 1;
                    queue.add(new Point(nx, ny));
                }
            }
        }
        return time;
    }

    private static String move_bfs(int[][] fireTime, Point start) {
        start = new Point(start.i + 1, start.j + 1);
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        int[][] move = new int[h + 2][w + 2];
        for (int j = 0; j < h + 2; j++) {
            for (int k = 0; k < w + 2; k++) {
                move[j][k] = -1;
            }
        }
        move[start.i][start.j] = 0;

        while (!queue.isEmpty()) {
            Point space = queue.poll();
            if (isEnd(space.i, space.j)) {
                return String.valueOf(move[space.i][space.j]);
            }

            for (int i = 0; i < 4; i++) {
                int nx = space.i + dx[i];
                int ny = space.j + dy[i];
                if (moveInRange(nx, ny) && move[nx][ny] == -1 && (isEnd(nx, ny)
                        || fireTime[nx - 1][ny - 1] > move[space.i][space.j] + 1)) {
                    move[nx][ny] = move[space.i][space.j] + 1;
                    queue.add(new Point(nx, ny));
                }
            }
        }
        return "IMPOSSIBLE";
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < h && y >= 0 && y < w;
    }

    private static boolean moveInRange(int x, int y) {
        return x >= 0 && x < h + 2 && y >= 0 && y < w + 2;
    }

    private static boolean isEnd(int x, int y) {
        return x == 0 || x == h + 1 || y == 0 || y == w + 1;
    }

    static class Point {
        int i;
        int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
