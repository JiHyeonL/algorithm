import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        // dp[i][j] : i번째 줄의 j에 동물을 놓을 수 있는 경우의 수
        long[][] dp = new long[n + 1][3];
        // j = 0 : 아무 사자도 놓지 않음
        // j = 1 : 첫번째 칸에 사자 놓음
        // j = 2 : 두번째 칸에 사자 놓음
        dp[1][0] = dp[1][1] = dp[1][2] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % 9901;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % 9901;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % 9901;
        }
        System.out.println((dp[n][0] + dp[n][1] + dp[n][2]) % 9901);   
    }
}
