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
    public static int m;
    public static int x;
    public static Map<Integer, List<Destination>> roads = new HashMap();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);  // 마을, 학생 수
        m = Integer.parseInt(temp[1]);  // 도로 수
        x = Integer.parseInt(temp[2]);  // 모이는 마을
        for (int i = 0; i < m; i++) {
            temp = br.readLine().split(" ");
            int start = Integer.parseInt(temp[0]);
            int end = Integer.parseInt(temp[1]);
            int time = Integer.parseInt(temp[2]);
            if (!roads.containsKey(start)) {
                roads.put(start, new ArrayList<>());
            }
            List<Destination> values = roads.get(start);
            values.add(new Destination(end, time));
            roads.put(start, values);
        }

        // todo: 집 -> x -> 집 까지 걸리는 시간 모든 학생 대상으로 구하기
        // 최단시간 계산 - 다익스트라
        // n * nlogn 애매한데
        int answer = 0;
        int[] xToHomeTime = dijkstra(x);
        for (int i = 1; i <= n; i++) {
            int totalTime = dijkstra(i)[x] + xToHomeTime[i];
            answer = Math.max(answer, totalTime);
        }
        System.out.println(answer);
    }

    private static int[] dijkstra(int start) {
        PriorityQueue<Destination> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.time, o2.time));
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        pq.add(new Destination(start, 0));
        dp[start] = 0;

        while (!pq.isEmpty()) {
            Destination now = pq.poll();
            if (dp[now.number] < now.time || !roads.containsKey(now.number)) {
                continue;
            }
            for (Destination next : roads.get(now.number)) {
                int nextTime = now.time + next.time;
                if (dp[next.number] > nextTime) {
                    dp[next.number] = nextTime;
                    pq.add(new Destination(next.number, dp[next.number]));
                }
            }
        }
        return dp;
    }
    static class Destination {
        int number;
        int time;

        public Destination(int number, int time) {
            this.number = number;
            this.time = time;
        }
    }

}
