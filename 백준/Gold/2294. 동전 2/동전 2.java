import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    private static int n;
    private static int k;
    private static int[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        k = Integer.parseInt(temp[1]);
        coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coins);
        int[] dp = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            for (int coin : coins) {
                if (coin > i) {
                    break;
                }
                if (i == coin) {
                    dp[i] = 1;
                }
                if (dp[i] == 0) {
                    if (dp[i - coin] != 0) {
                        dp[i] = dp[i - coin] + 1;
                    }
                } else {
                    if (dp[i - coin] != 0) {
                        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                    }
                }
            }
        }
        if (dp[k] == 0) {
            dp[k] = -1;
        }
        System.out.println(dp[k]);
    }
}
