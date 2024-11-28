import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9095
public class Main {

    public static int n;
    public static int[] t;
    public static int[] p;
    public static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        t = new int[n + 1];
        p = new int[n + 1];
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            t[i] = Integer.parseInt(temp[0]);
            p[i] = Integer.parseInt(temp[1]);
        }

        dp = new int[n+2];
        for (int i = 0; i < n; i++) {
            if (i + t[i] <= n) {
                dp[i + t[i]] = Math.max(dp[i + t[i]], dp[i] + p[i]);
            }
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }
        System.out.println(dp[n]);
    }

}
