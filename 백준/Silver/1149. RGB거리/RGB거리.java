import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static int n;
    public static int[][] house;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        house = new int[n+1][3];
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                house[i][j] = Integer.parseInt(temp[j]);
            }
        }
        // [0][i] = i번째 집을 빨간색으로 칠했을 때 첫번째 집부터 i번째 집까지 총 비용
        // [1][i] = i번째 집을 초록색으로 칠했을 때 ~
        // [2][i] = i번째 집을 파란색으로 칠했을 때 ~
        int[][] dp = new int[3][n+1];
        // 첫번째 집 초기 세팅
        dp[0][0] = house[0][0];
        dp[1][0] = house[0][1];
        dp[2][0] = house[0][2];
        for (int i = 1; i < n; i++) {
            dp[0][i] = Math.min(dp[1][i-1], dp[2][i-1]) + house[i][0];
            dp[1][i] = Math.min(dp[0][i-1], dp[2][i-1]) + house[i][1];
            dp[2][i] = Math.min(dp[0][i-1], dp[1][i-1]) + house[i][2];
        }

        int answer = Math.min(dp[0][n - 1], dp[1][n - 1]);
        answer = Math.min(answer, dp[2][n - 1]);
        System.out.println(answer);
    }
}
