import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int n;
    public static int[] child;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        child = new int[n];
        for (int i = 0; i < n; i++) {
            child[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n]; // i를 포함하는 가장 긴 증가 수열 길이
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (child[i] > child[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(dp[i], max);
        }
        System.out.println(n - max);
    }
}
