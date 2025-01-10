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
    public static Map<Integer, List<Node>> info;
    public static int[] dp; // 최단 비용 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);

        info = new HashMap<>();
        for (int i = 0; i < m; i++) {
            temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            int c = Integer.parseInt(temp[2]);
            if (!info.containsKey(a)) {
                info.put(a, new ArrayList<>());
            }
            info.get(a).add(new Node(b, c));
            if (!info.containsKey(b)) {
                info.put(b, new ArrayList<>());
            }
            info.get(b).add(new Node(a, c));
        }
        dp = new int[n+1];
        dijkstra();
        System.out.println(dp[n]);
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cowCount - o2.cowCount);
        pq.add(new Node(1, 0));
        for (int i = 0; i < n+1; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[1] = 0;
        // dp[i] = 1부터 i까지의 최소 cow 수
        // pq = 앞으로 방문할 곳을 cow가 가장 적은 순으로 정렬
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int destination = node.destination;
            int cowCount = node.cowCount;
            if (dp[destination] < cowCount) {
                continue;
            }
            for (Node next : info.get(destination)) {
                if (dp[next.destination] > cowCount + next.cowCount) {
                    dp[next.destination] = cowCount + next.cowCount;
                    pq.add(new Node(next.destination, dp[next.destination]));
                }
            }
        }
    }

    static class Node {
        int destination;
        int cowCount;

        public Node(int destination, int cowCount) {
            this.destination = destination;
            this.cowCount = cowCount;
        }
    }

}
