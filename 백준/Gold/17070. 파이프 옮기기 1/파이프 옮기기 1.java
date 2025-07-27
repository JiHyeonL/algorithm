import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    private static int n;
    private static int[][] house;
    private static int[] dx = {0, 1, 1}; // →, ↘, ↓
    private static int[] dy = {1, 1, 0};
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        house = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                house[i][j] = Integer.parseInt(temp[j]);
            }
        }
        dfs(new Pipe(new int[]{1, 1}, new int[]{1, 2}));
        System.out.println(answer);
    }

    private static void dfs(Pipe pipe) {
        if (pipe.isEnd()) {
            answer++;
            return;
        }
        for (int idx : pipe.findDirection()) {
            int nx = pipe.end[0] + dx[idx];
            int ny = pipe.end[1] + dy[idx];
            if (inRange(nx, ny) && isEmpty(idx, nx, ny)) {
                dfs(new Pipe(new int[]{pipe.end[0], pipe.end[1]}, new int[]{nx, ny}));
            }
        }
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x <= n && y >= 0 && y <= n;
    }

    public static boolean isEmpty(int index, int x, int y) {
        // 대각선
        if (index == 1) {
            return house[x - 1][y - 1] == 0 && house[x - 2][y - 1] == 0 && house[x - 1][y - 2] == 0;
        }
        return house[x - 1][y - 1] == 0; // 가로 세로
    }

    static class Pipe {
        int[] start;
        int[] end;

        public Pipe(int[] start, int[] end) {
            this.start = start;
            this.end = end;
        }

        public boolean isEnd() {
            return end[0] == n && end[1] == n;
        }

        public int[] findDirection() {
            if (start[0] == end[0]) { // 가로
                return new int[]{0, 1};
            }
            if (start[1] == end[1]) { // 세로
                return new int[]{1, 2};
            }
            return new int[]{0, 1, 2};// 대각선
        }
    }
}
