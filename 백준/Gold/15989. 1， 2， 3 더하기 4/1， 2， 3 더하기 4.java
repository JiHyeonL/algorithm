import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int t;
    public static int n;
    public static int[][] dp = new int[10001][4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp();
        t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int answer = dp[n][1] + dp[n][2] + dp[n][3];
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    public static void dp() {
        // dp[i][1] = i를 만들 수 있는 경우의 수 중 끝이 1로 끝남
        // dp[i][2] = i를 만들 수 있는 경우의 수 중 끝이 2로 끝남
        // dp[i][3] = i를 만들 수 있는 경우의 수 중 끝이 3로 끝남
        dp[1][1] = 1;
        dp[1][2] = 0;
        dp[1][3] = 0;

        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[2][3] = 0;

        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        // 무조건 오름차순 유지된다고 가정. 아니면 중복 제거 불가능
        for (int i = 4; i < 10001; i++) {
            dp[i][1] = dp[i-1][1];
            dp[i][2] = dp[i-2][1] + dp[i-2][2];
            dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
        }
    }
}
