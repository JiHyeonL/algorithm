import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {

    public static int n;
    public static int[] children;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        children = new int[n];
        String[] temp = br.readLine().split(" ");
        for (int i = 0 ; i < n; i++) {
            children[i] = Integer.parseInt(temp[i]);
        }

        int answer = 0;
        int[] dp = new int[n+1];
        for (int i = 0; i < n; i++) {
            dp[children[i]] = dp[children[i] - 1] + 1;
            answer = Math.max(answer, dp[children[i]]);
        }
        System.out.println(n - answer);
    }
}
