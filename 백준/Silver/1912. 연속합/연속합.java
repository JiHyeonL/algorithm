import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    private static int n;
    private static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(temp[i]);
        }

        int[] dp = new int[n];
        dp[0] = a[0];
        int max = dp[0];
        for (int i = 1; i < a.length; i++) {
            dp[i] = Math.max(dp[i - 1] + a[i], a[i]);
            max = Math.max(dp[i], max);
        }
        System.out.println(max);
    }
}
