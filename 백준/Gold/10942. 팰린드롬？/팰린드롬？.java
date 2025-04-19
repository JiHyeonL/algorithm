import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int n;
    public static int[] numbers;
    public static int m;
    public static Range[] questions;
    public static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        numbers = new int[n + 1];
        String[] temp = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(temp[i - 1]);
        }
        m = Integer.parseInt(br.readLine());
        questions = new Range[m];
        for (int i = 0; i < m; i++) {
            temp = br.readLine().split(" ");
            questions[i] = new Range(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }

        dp = new boolean[n + 1][n + 1];
        calculatePD();

        StringBuilder answer = new StringBuilder();
        for (Range question : questions) {
            answer.append(dp[question.start][question.end] ? 1 : 0).append("\n");
        }
        System.out.println(answer);
    }

    private static void calculatePD() {
        for (int i = 1; i <= n; i++) {
            dp[i][i] = true;
            if (numbers[i] == numbers[i - 1]) {
                dp[i - 1][i] = true;
            }
        }

        for (int len = 3; len <= n; len++) {
            for (int start = 1; start <= n - len + 1; start++) {
                int end = start + len - 1;
                // 양 끝이 같고 그 안 숫자들이 팰린드롬일 경우 팰린드롬이다.
                if (numbers[start] == numbers[end] && dp[start + 1][end - 1]) {
                    dp[start][end] = true;
                }
            }
        }
    }

    static class Range {
        int start;
        int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
