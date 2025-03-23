import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static int r;
    public static int c;
    public static int[][] maze;
    public static int[] di = {0, 1, 0, -1};
    public static int[] dj = {1, 0, -1, 0};
    // 벽: -1 공간: 0 지훈이 위치 -2 불 1

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        r = Integer.parseInt(temp[0]);
        c = Integer.parseInt(temp[1]);
        maze = new int[r][c];
        Queue<Point> queue = new LinkedList<>();
        Point now = null;
        for (int i = 0; i < r; i++) {
            temp = br.readLine().split("");
            for (int j = 0; j < c; j++) {
                if (temp[j].equals("#")) {
                    maze[i][j] = -1;
                }
                else if (temp[j].equals(".")) {
                    maze[i][j] = 0;
                }
                else if (temp[j].equals("F")) {
                    maze[i][j] = 1;
                    queue.add(new Point(i, j));
                }
                else if (temp[j].equals("J")) {
                    maze[i][j] = 0;
                    now = new Point(i, j);
                }
            }
        }

        while (!queue.isEmpty()) {
            // 불 전파
            Point fire = queue.poll();
            for (int cnt = 0; cnt < 4; cnt++) {
                int ni = fire.i + di[cnt];
                int nj = fire.j + dj[cnt];
                if (inRange(ni, nj) && maze[ni][nj] == 0) {
                    maze[ni][nj] = maze[fire.i][fire.j] + 1;
                    queue.add(new Point(ni, nj));
                }
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (maze[i][j] == 0) {
                    maze[i][j] = Integer.MAX_VALUE;  // 불이 도달하지 않으면 무한대
                }
            }
        }

        // bfs
        boolean[][] visited = new boolean[r][c];
        Queue<Bfs> moveQueue = new LinkedList<>();
        moveQueue.add(new Bfs(now, 0));
        visited[now.i][now.j] = true;
        int answer = -1;
        while (!moveQueue.isEmpty()) {
            Bfs jihun = moveQueue.poll();
            if (isEnd(jihun.point)) {
                answer = jihun.count;
                break;
            }
            for (int cnt = 0; cnt < 4; cnt++) {
                int ni = jihun.point.i + di[cnt];
                int nj = jihun.point.j + dj[cnt];
                if (inRange(ni, nj) && !visited[ni][nj] && canGo(ni, nj, jihun.count)) {
                    moveQueue.add(new Bfs(new Point(ni, nj), jihun.count + 1));
                    visited[ni][nj] = true;
                }
            }
        }

        if (answer == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(answer + 1);
        }
    }

    private static boolean isEnd(Point point) {
        int i = point.i;
        int j = point.j;
        return i == 0 || i == r - 1 || j == 0 || j == c - 1;
    }

    private static boolean canGo(int i, int j, int time) {
        return maze[i][j] != -1 && maze[i][j] - 1 > time + 1;
    }

    private static boolean inRange(int i, int j) {
        return i >= 0 && i < r && j >= 0 && j < c;
    }

    static class Point {
        int i;
        int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static class Bfs {
        Point point;
        int count;

        public Bfs(Point point, int count) {
            this.point = point;
            this.count = count;
        }
    }
}
