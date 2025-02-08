import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static int n;
    public static int l;
    public static int r;
    public static int[][] people;
    public static List<Point> goCountry;
    public static int peopleSum;
    public static boolean isMoved = false;
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        l = Integer.parseInt(temp[1]);
        r = Integer.parseInt(temp[2]);

        people = new int[n][n];
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                people[i][j] = Integer.parseInt(temp[j]);
            }
        }

        int answer = 0;
        while (true) {
            // 전체 나라 대상 인구 이동
            isMoved = false;
            boolean[][] visited = new boolean[n][n];
            // 모든 국가 방문
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n ; j++) {
                    if (!visited[i][j]) {
                        peopleSum = 0;
                        goCountry = new ArrayList<>();
                        bfs(new Point(i, j), visited);
                        if (goCountry.size() > 1) {
                            int newPeople = peopleSum / goCountry.size();
                            // 하나의 연합 대상 인구 이동
                            movePeople(newPeople);
                            isMoved = true;
                        }
                    }
                }
            }
            if (!isMoved) {
                break;
            }
            answer++;
        }
        System.out.println(answer);
    }

    private static void movePeople(final int newPeople) {
        for (Point point : goCountry) {
            people[point.x][point.y] = newPeople;
        }
    }

    public static void bfs(Point start, boolean[][] visited) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Point next = queue.poll();
            peopleSum += people[next.x][next.y];
            goCountry.add(new Point(next.x, next.y));

            for (int i = 0; i < 4; i++) {
                int nx = next.x + dx[i];
                int ny = next.y + dy[i];
                if (inRange(nx, ny) && !visited[nx][ny]) {
                    if (canOpen(next, nx, ny)) {
                        visited[nx][ny] = true;
                        queue.add(new Point(nx, ny));
                    }
                }
            }
        }
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static boolean canOpen(Point start, int endX, int endY) {
        int diff = Math.abs(people[start.x][start.y] - people[endX][endY]);
        if (diff >= l && diff <= r) {
            return true;
        }
        return false;
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
