import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static int n;
    public static int[][] profit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        profit = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                profit[i][j] = Integer.parseInt(temp[j]);
            }
        }

        int[][] dp = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + profit[i-1][j-1];
            }
        }

        int answer = -1001;
        for (int k = 1; k <= n; k++) {
            for (int i = k; i <= n; i++) {
                for (int j = k; j <= n; j++) {
                    int profit = dp[i][j] - dp[i][j - k] - dp[i - k][j] + dp[i - k][j - k];
                    answer = Math.max(answer, profit);
                }
            }
        }
        System.out.println(answer);
    }
}
