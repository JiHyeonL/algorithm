
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int n;
    public static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n+3];
        System.out.println(answer());
    }

    private static int answer() {
        dp[0] = -1;
        dp[1] = -1;
        dp[2] = -1;
        dp[3] = 1;
        dp[4] = -1;
        dp[5] = 1;

        if (n <= 5) {
            return dp[n];
        }
        // 식: dp[i] = min(dp[i-5] or dp[i-3]) + 1
        for (int i = 6; i <= n; i++) {
            // 규칙: 둘 중 하나라도 되면 그 값 + 1
            if (dp[i - 5] != -1 || dp[i - 3] != -1) {
                if (dp[i - 5] != -1 && dp[i - 3] != -1) {
                    dp[i] = Math.min(dp[i - 5], dp[i - 3]) + 1;
                    //System.out.println("둘 중 작은값 i: "+i + ", "+ dp[i]);
                }
                else if (dp[i - 5] != -1) {
                    dp[i] = dp[i - 5] + 1;
                    //System.out.println("5이전거 i: "+i + ", "+ dp[i]);
                }
                else {
                    dp[i] = dp[i - 3] + 1;
                    //System.out.println("3이전거 i: "+i + ", "+ dp[i]);
                }
                continue;
            }
            dp[i] = -1;
            //System.out.println("i: "+i + ", "+dp[i]);
        }
        return dp[n];
    }
}
