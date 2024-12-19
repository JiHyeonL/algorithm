import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static int t;
    public static int n;
    public static int[][] stickers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            stickers = new int[2][n+1];
            String[] temp = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                stickers[0][j] = Integer.parseInt(temp[j-1]);
            }
            temp = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                stickers[1][j] = Integer.parseInt(temp[j-1]);
            }
            System.out.println(dp());
        }
    }

    public static int dp() {
        int[][] dp = new int[2][n+1];

        dp[0][1] = stickers[0][1];
        dp[1][1] = stickers[1][1];
        for (int i = 2; i <= n; i++) {
            dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + stickers[0][i];
            dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + stickers[1][i];
        }

        return Math.max(dp[0][n], dp[1][n]);
    }
}
