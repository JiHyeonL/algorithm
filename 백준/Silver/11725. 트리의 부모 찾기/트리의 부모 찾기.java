import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Main {

    public static int n;
    public static Map<Integer, List<Integer>> trees = new HashMap<>();
    public static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n-1; i++) {
            String[] nodes = br.readLine().split(" ");
            int node1 = Integer.parseInt(nodes[0]);
            int node2 = Integer.parseInt(nodes[1]);
            setUp(node1, node2);
            setUp(node2, node1);
        }

        answer = new int[n+1];
        answer();
        for (int i = 2; i <= n; i++) {
            System.out.println(answer[i]);
        }
    }

    private static void answer() {
        boolean[] visited = new boolean[n+1];
        Queue<Integer> queue = new LinkedList();
        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int child: trees.get(node)) {
                if (!visited[child]) {
                    answer[child] = node;
                    queue.add(child);
                    visited[child] = true;
                }
            }
        }
    }

    private static void setUp(int node1, int node2) {
        if (trees.containsKey(node1)) {
            List<Integer> temp = trees.get(node1);
            temp.add(node2);
            trees.put(node1, temp);
        } else {
            List<Integer> temp = new ArrayList<>();
            temp.add(node2);
            trees.put(node1, temp);
        }
    }
}
