import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static int t;
    public static int n;
    public static int[] coins;
    public static int m;
    public static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            coins = new int[n];
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                coins[j] = Integer.parseInt(temp[j]);
            }
            Arrays.sort(coins);
            m = Integer.parseInt(br.readLine());
            dp = new int[m+1][n+1];
            sb.append(dp()).append("\n");
        }
        System.out.println(sb);
    }

    public static int dp() {
        for (int i = coins[0]; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                int coin = coins[j];
                if (i == coin) {
                    dp[i][j] = 1;
                    continue;
                }
                if (i < coin) {
                    break;
                }
                int sum = 0;
                for (int k = 0; k <= j; k++) {
                    sum += dp[i - coin][k];
                }
                dp[i][j] = sum;
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += dp[m][i];
        }
        return sum;
    }
}
