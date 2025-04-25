import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int n;
    public static int k;
    public static Item[] items;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        k = Integer.parseInt(temp[1]);
        items = new Item[n + 1];
        for (int i = 1; i <= n; i++) {
            temp = br.readLine().split(" ");
            items[i] = new Item(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }

        // dp[i][j] = i번째 물건까지 살펴보는 중 최대 j 무게를 사용한다고 했을 때, 최대 가치
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (j < items[i].w) {   // 추가 불가
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 이전 아이템까지 살펴봤을 때 최대 가치 vs (가능한 무게 - 현재 아이템 무게)일 때 이전 아이템 최대 가치 + 현재 아이템 가치
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - items[i].w] + items[i].v);
                }
            }
        }
        System.out.println(dp[n][k]);
    }

    static class Item {
        int w;
        int v;

        public Item(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }
}
