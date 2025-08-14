import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class Main {

    private static int n;
    private static int m;
    private static int start;
    private static int end;
    private static Map<Integer, List<Info>> bus = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            String[] temp = br.readLine().split(" ");
            List<Info> child = bus.getOrDefault(Integer.parseInt(temp[0]), new ArrayList<>());
            child.add(new Info(Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
            bus.put(Integer.parseInt(temp[0]), child);
        }
        String[] temp = br.readLine().split(" ");
        start = Integer.parseInt(temp[0]);
        end = Integer.parseInt(temp[1]);
        dijkstra();
    }

    private static void dijkstra() {
        PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.cost < o2.cost) {
                return -1;
            } else if (o1.cost == o2.cost) {
                return 0;
            }
            return 1;
        });
        int[] dp = new int[n + 1];
        int[] beforeCity = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            beforeCity[i] = -1;
        }
        pq.add(new Info(start, 0));
        dp[start] = 0;
        while (!pq.isEmpty()) {
            Info info = pq.poll();
            if (dp[info.city] < info.cost) {
                continue;
            }
            for (Info next : bus.getOrDefault(info.city, Collections.emptyList())) {
                if (dp[next.city] > dp[info.city] + next.cost) {
                    dp[next.city] = dp[info.city] + next.cost;
                    beforeCity[next.city] = info.city;
                    pq.add(new Info(next.city, dp[next.city]));
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        for (int now = end; now != -1; now = beforeCity[now]) {
            path.add(now);
        }
        Collections.reverse(path);
        StringBuilder answer = new StringBuilder();
        answer.append(dp[end]).append("\n");
        answer.append(path.size()).append("\n");
        for (int city : path) {
            answer.append(city).append(" ");
        }
        System.out.println(answer);
    }

    static class Info {
        int city;
        int cost;

        public Info(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }
    }
}
