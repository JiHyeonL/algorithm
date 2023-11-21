import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

// 2293번
public class Main {

    public static int N;    // 동전 종류 수
    public static int K;    // 합
    public static List<Integer> coin = new ArrayList<>();
    public static int[] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int i = 1 ; i <= N; i++) {
            coin.add(Integer.parseInt(br.readLine()));
        }

        dp = new int[K+1];
        findAnswer();
        System.out.println(dp[K]);
    }

    private static void findAnswer() {
        dp[0] = 1;
        for(int i = 0; i < N; i++) {
            int coinValue = coin.get(i);
            for(int j = 1; j < K+1; j++) {
                if(j >= coinValue) {
                    dp[j] += dp[j - coinValue];
                }
            }
        }
    }
}

