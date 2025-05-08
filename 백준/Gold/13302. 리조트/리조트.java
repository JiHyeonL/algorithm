import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int n;
    private static int m;
    private static boolean[] noResort;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);

        noResort = new boolean[n + 1];
        if (m != 0) {
            temp = br.readLine().split(" ");
            for (int i = 0; i < m; i++) {
                noResort[Integer.parseInt(temp[i])] = true;
            }
        }

        int[][] dp = new int[n + 1][2 * n];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 2 * n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;
        for (int i = 0; i <= n; i++) {  // 날짜
            for (int j = 0; j <= n; j++) {  // 쿠폰 수
                if (dp[i][j] == Integer.MAX_VALUE) {
                    continue;
                }
                if (i + 1 <= n && noResort[i + 1]) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j]);
                    continue;
                }
                // 1일권
                if (i + 1 <= n) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 10000);
                }
                // 3일권
                if (i + 3 <= n) {
                    dp[i + 3][j + 1] = Math.min(dp[i + 3][j + 1], dp[i][j] + 25000);
                }
                // 5일권
                if (i + 5 <= n) {
                    dp[i + 5][j + 2] = Math.min(dp[i + 5][j + 2], dp[i][j] + 37000);
                }
                // 쿠폰 3개 이상
                if (j >= 3 && i + 1 <= n) {
                    dp[i + 1][j - 3] = Math.min(dp[i + 1][j - 3], dp[i][j]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            if (dp[n][i] != Integer.MAX_VALUE) {
                answer = Math.min(answer, dp[n][i]);
            }
        }
        System.out.println(answer);
    }
}
