import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Main {

    public static int n;
    public static Map<Integer, List<Integer>> graph = new HashMap<>();
    public static int[] people;
    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        people = new int[n + 1];
        String[] temp = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            people[i] = Integer.parseInt(temp[i - 1]);
        }
        for (int i = 1; i <= n; i++) {
            temp = br.readLine().split(" ");
            List<Integer> g = new ArrayList<>();
            for (int j = 0; j < Integer.parseInt(temp[0]); j++) {
                g.add(Integer.parseInt(temp[j + 1]));
            }
            graph.put(i, g);
        }

        for (int i = 1; i <= n / 2; i++) {
            combination(1, i, new ArrayList<>());
        }

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static void combination(int start, int count, List<Integer> selected) {
        if (count == 0) {
            calculateGap(selected);
            return;
        }
        for (int i = start; i <= n; i++) {
            selected.add(i);
            combination(i + 1, count - 1, selected);
            selected.remove(selected.size() - 1);
        }
    }

    private static void calculateGap(List<Integer> aGroup) {
        if (!isConnected(aGroup)) {
            return;
        }
        List<Integer> bGroup = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!aGroup.contains(i)) {
                bGroup.add(i);
            }
        }
        if (!isConnected(bGroup)) {
            return;
        }

        int aSum = 0;
        int bSum = 0;
        for (int a : aGroup) {
            aSum += people[a];
        }
        for (int b : bGroup) {
            bSum += people[b];
        }
        answer = Math.min(answer, Math.abs(aSum - bSum));
    }

    private static boolean isConnected(List<Integer> group) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        queue.add(group.get(0));
        visited[group.get(0)] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int next : graph.get(node)) {
                if (!visited[next] && group.contains(next)) {
                    queue.add(next);
                    visited[next] = true;
                    count++;
                }
            }
        }

        return count == group.size();
    }
}
