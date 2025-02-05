import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int n;
    public static int start;
    public static int answer = Integer.MAX_VALUE;
    public static int[][] times;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        start = Integer.parseInt(temp[1]);
        times = new int[n][n];
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                times[i][j] = Integer.parseInt(temp[j]);
            }
        }

        for (int k = 0; k < n; k++) {
           for (int i = 0; i < n; i++) {
               for (int j = 0; j < n; j++) {
                    times[i][j] = Math.min(times[i][j], times[i][k] + times[k][j]);
               }
           }
        }

        boolean[] visited = new boolean[n];
        visited[start] = true;
        backTracking(start, 0, 0, visited);
        System.out.println(answer);
    }

    private static void backTracking(int start, int timeSum, int depth, boolean[] visited) {
        // 모든 행성을 방문했거나 합이 answer보다 더 크다면 탐색 종료
        if (timeSum >= answer) {
            return;
        }
        if (depth == n-1) {
            answer = Math.min(answer, timeSum);
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backTracking(i, timeSum + times[start][i], depth+ 1, visited);
                visited[i] = false;
            }
        }
    }
}
