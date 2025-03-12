import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int n;
    public static int m;
    // 현재 위치에서 위, 왼쪽 대각선 대각선, 왼쪽
    public static int[] dx = {-1, -1, 0};
    public static int[] dy = {0, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        // dp[i][j] = (n,m)이 정사각형의 오른쪽 아래 꼭짓점일 때 정사각형 개수
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                dp[i][j] = Integer.parseInt(temp[j]);
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (dp[i][j] > 0) {
                    int minCount = Integer.MAX_VALUE;
                    boolean is2x2 = true;
                    for (int k = 0; k < 3; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (dp[nx][ny] > 0) {
                            minCount = Math.min(minCount, dp[nx][ny]);
                        } else {
                            is2x2 = false;
                            break;
                        }
                    }
                    if (is2x2) {
                        dp[i][j] = minCount + 1;
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer = Math.max(answer, dp[i][j]);
            }
        }
        System.out.println(answer * answer);
    }
}
