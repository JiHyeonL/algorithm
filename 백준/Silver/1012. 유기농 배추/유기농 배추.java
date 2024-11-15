import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int t;
    public static int m;
    public static int n;
    public static int k;
    public static int[][] all;
    public static boolean[][] visited;
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};
    public static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            setUp(br);
            answer.append(findAll()).append("\n");
        }
        System.out.println(answer);
    }

    private static void setUp(BufferedReader br) throws IOException {
        String[] temp = br.readLine().split(" ");
        m = Integer.parseInt(temp[0]);
        n = Integer.parseInt(temp[1]);
        k = Integer.parseInt(temp[2]);

        all = new int[m + 1][n + 1];
        visited = new boolean[m + 1][n + 1];
        for (int i = 0; i < k; i++) {
            temp = br.readLine().split(" ");
            int xx = Integer.parseInt(temp[0]);
            int yy = Integer.parseInt(temp[1]);
            all[xx][yy] = 1;
        }
    }

    private static int findAll() {
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (all[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    dfs(i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if (inRange(newX, newY) && all[newX][newY] == 1 && !visited[newX][newY]) {
                visited[newX][newY] = true;
                dfs(newX, newY);
            }
        }
    }

    private static boolean inRange(int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return false;
        }
        return true;
    }
}
