import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    public static int v;
    public static int e;
    public static Map<Integer, List<Time>> roads = new HashMap<>();
    public static int[] route = new int[10];
    public static int myStart;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        v = Integer.parseInt(temp[0]);
        e = Integer.parseInt(temp[1]);
        for (int i = 0; i < e; i++) {
            temp = br.readLine().split(" ");
            int u = Integer.parseInt(temp[0]);
            int v = Integer.parseInt(temp[1]);
            int w = Integer.parseInt(temp[2]);
            List<Time> valueForU = roads.getOrDefault(u, new ArrayList<>());
            valueForU.add(new Time(v, w));
            roads.put(u, valueForU);
            List<Time> valueForV = roads.getOrDefault(v, new ArrayList<>());
            valueForV.add(new Time(u, w));
            roads.put(v, valueForV);
        }
        temp = br.readLine().split(" ");
        for (int i = 0; i < 10; i++) {
            route[i] = Integer.parseInt(temp[i]);
        }
        myStart = Integer.parseInt(br.readLine());

        long timeSum = 0;
        int answer = Integer.MAX_VALUE;
        int start = 0;
        int end = 1;
        if (myStart == route[0]) {
            answer = route[0];
        }
        while (end < 10) {
            int nextRoute = dijkstra(route[start], route[end]);
            if (nextRoute == Integer.MAX_VALUE) {
                end++;
                continue;
            }
            timeSum += nextRoute;
            int moveTime = dijkstra(myStart, route[end]);
            if (moveTime != Integer.MAX_VALUE && timeSum >= moveTime) {
                answer = Math.min(answer, route[end]);
            }
            start = end;
            end++;
        }
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    public static int dijkstra(int start, int end) {
        PriorityQueue<Time> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.time < o2.time) {
                return -1;
            }
            if (o1.time > o2.time) {
                return 1;
            }
            return 0;
        });
        int[] dp = new int[v+1];
        for (int i = 0; i < v+1; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        pq.add(new Time(start, 0));
        dp[start] = 0;

        while (!pq.isEmpty()) {
            Time now = pq.poll();
            if (dp[now.v] < now.time || !roads.containsKey(now.v)) {
                continue;
            }
            // time.v에서 갈 수 있는 경로 모두 살펴보기
            for (Time next: roads.get(now.v)) {
                int newTimeSum = now.time + next.time;
                if (dp[next.v] > newTimeSum) {
                    dp[next.v] = newTimeSum;
                    pq.add(new Time(next.v, dp[next.v]));
                }
            }
        }
        return dp[end];
    }

    static class Time {
        int v;
        int time;

        public Time(int v, int time) {
            this.v = v;
            this.time = time;
        }
    }
}
