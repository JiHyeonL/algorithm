import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int m;
    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        m = Integer.parseInt(temp[0]);
        n = Integer.parseInt(temp[1]);

        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
        }
        for (int i = 2; i <= n; i++) {
            if (dp[i] == 0) {
                continue;
            }
            for (int j = i * 2; j <= n; j += i) {
                dp[j] = 0;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = m; i <= n; i++) {
            if (dp[i] != 0) {
                sb.append(dp[i]).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
