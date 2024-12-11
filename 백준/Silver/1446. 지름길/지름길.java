import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int n;
    public static int d;
    public static List<Info> route = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        d = Integer.parseInt(temp[1]);

        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            route.add(new Info(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
        }

        int[] dp = new int[d + 1];
        for (int i = 0; i <= d; i++) {
            dp[i] = i;
        }
        for (int i = 1; i <= d; i++) {
            dp[i] = Math.min(dp[i], dp[i - 1] + 1);

            for (int j = 0; j < n; j++) {
                if (route.get(j).end == i) {
                    dp[i] = Math.min(dp[i], dp[route.get(j).start] + route.get(j).length);
                }
            }
        }
        System.out.println(dp[d]);
    }

    public static class Info {
        int start;
        int end;
        int length;

        public Info(int start, int end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }
    }

}
