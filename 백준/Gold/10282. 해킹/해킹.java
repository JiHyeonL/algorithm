import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static int n;
    public static int d;
    public static int c;
    public static Map<Integer, List<Infection>> computers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String[] temp = br.readLine().split(" ");
            n = Integer.parseInt(temp[0]);
            d = Integer.parseInt(temp[1]);
            c = Integer.parseInt(temp[2]);

            computers = new HashMap<>();
            for (int j = 0; j < d; j++) {
                temp = br.readLine().split(" ");
                int a = Integer.parseInt(temp[0]);
                int b = Integer.parseInt(temp[1]);
                int s = Integer.parseInt(temp[2]);
                if (computers.containsKey(b)) {
                    List<Infection> cpt = computers.get(b);
                    cpt.add(new Infection(a, s));
                    computers.put(b, cpt);
                } else {
                    List<Infection> cpt = new ArrayList<>();
                    cpt.add(new Infection(a, s));
                    computers.put(b, cpt);
                }
            }
            answer.append(dijkstra()).append("\n");
        }
        System.out.print(answer);
    }

    private static StringBuilder dijkstra() {
        Queue<Infection> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.time < o2.time) {
                return -1;
            } else if (o1.time == o2.time) {
                return 0;
            } else {
                return 1;
            }
        });
        int[] dp = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        pq.add(new Infection(c, 0));
        dp[c] = 0;
        // 시간별 오염되는 컴퓨터 계산
        while (!pq.isEmpty()) {
            Infection now = pq.poll();
            if (dp[now.target] < now.time) {
                continue;
            }
            for (Infection next : computers.getOrDefault(now.target, List.of())) {
                int nextTime = next.time + now.time;
                if (dp[next.target] > nextTime) {
                    pq.add(new Infection(next.target, nextTime));
                    dp[next.target] = Math.min(dp[next.target], nextTime);
                }
            }
        }
        StringBuilder result = new StringBuilder();
        int totalTime = 0;
        int computeCount = 0;
        for (int i = 1; i < n + 1; i++) {
            if (dp[i] != Integer.MAX_VALUE) {
                computeCount += 1;
                totalTime = Math.max(totalTime, dp[i]);
            }
        }
        return result.append(computeCount).append(" ").append(totalTime);
    }

    static class Infection {
        int target;
        int time;

        public Infection(int target, int time) {
            this.target = target;
            this.time = time;
        }
    }
}
