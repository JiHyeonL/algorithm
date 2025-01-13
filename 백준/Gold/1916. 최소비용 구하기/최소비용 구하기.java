import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    public static int n;
    public static int m;
    public static int start;
    public static int end;
    public static Map<Integer, List<Node>> busInfo = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            String[] temp = br.readLine().split(" ");
            int start = Integer.parseInt(temp[0]);
            int destination = Integer.parseInt(temp[1]);
            int cost = Integer.parseInt(temp[2]);
            if (!busInfo.containsKey(start)) {
                busInfo.put(start, new ArrayList<>());
            }
            busInfo.get(start).add(new Node(destination, cost));
        }
        String[] temp = br.readLine().split(" ");
        start = Integer.parseInt(temp[0]);
        end = Integer.parseInt(temp[1]);
        System.out.println(dijkstra());
    }

    public static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (dp[now.city] < now.cost) {
                continue;
            }
            if (!busInfo.containsKey(now.city)) {
                continue;
            }
            for (Node next : busInfo.get(now.city)) {
                if (dp[next.city] > dp[now.city] + next.cost) {
                    dp[next.city] = dp[now.city] + next.cost;
                    pq.add(new Node(next.city, dp[next.city]));
                }
            }
        }
        return dp[end];
    }

    static class Node {
        int city;
        int cost;

        public Node(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }

        public String toString() {
            return "("+ city + ", " + cost + ")";
        }
    }
}
