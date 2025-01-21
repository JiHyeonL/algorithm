import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static int n;
    public static final String FORMAT = "Problem %d: %s\n";
    public static int[][] coins;
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int count = 1;
        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }
            coins = new int[n][n];
            for (int i = 0; i < n; i++) {
                String[] temp = br.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    coins[i][j] = Integer.parseInt(temp[j]);
                }
            }
            answer.append(String.format(FORMAT, count, dijkstra()));
            count++;
        }
        System.out.println(answer);
    }

    private static int dijkstra() {
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.MAX_VALUE;
            }
        }
        cost[0][0] = coins[0][0];
        PriorityQueue<Point> pq = new PriorityQueue<>(((o1, o2) -> o1.value - o2.value));
        pq.add(new Point(0, 0, cost[0][0]));

        while (!pq.isEmpty()) {
            Point coin = pq.poll();
            if (cost[coin.x][coin.y] < coin.value) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nx = coin.x + dx[i];
                int ny = coin.y + dy[i];
                if (inRange(nx, ny) && cost[nx][ny] > cost[coin.x][coin.y] + coins[nx][ny]) {
                    cost[nx][ny] = cost[coin.x][coin.y] + coins[nx][ny];
                    pq.add(new Point(nx, ny, cost[nx][ny]));
                }
            }
        }
        return cost[n - 1][n - 1];
    }

    private static boolean inRange(int x, int y) {
        return x < n && x >= 0 && y < n && y >= 0;
    }

    static class Point {
        int x;
        int y;
        int value;

        public Point(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}
