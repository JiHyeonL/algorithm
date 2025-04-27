import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static int n;
    public static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        k = Integer.parseInt(temp[1]);

        bfs();
    }

    private static void bfs() {
        boolean[] visited = new boolean[200001];
        int[] path = new int[200001];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        visited[n] = true;
        path[n] = -1;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now == k) {
                break;
            }
            if (inRange(now + 1) && !visited[now + 1]) {
                queue.add(now + 1);
                visited[now + 1] = true;
                path[now + 1] = now;
            }
            if (inRange(now - 1) && !visited[now - 1]) {
                queue.add(now - 1);
                visited[now - 1] = true;
                path[now - 1] = now;
            }
            if (inRange(now * 2) && !visited[now * 2]) {
                queue.add(now * 2);
                visited[now * 2] = true;
                path[now * 2] = now;
            }
        }

        List<Integer> answer = new ArrayList<>();
        for (int i = k; i != -1; i = path[i]) {
            answer.add(i);
        }
        Collections.reverse(answer);
        StringBuilder sb = new StringBuilder();
        for (int ans : answer) {
            sb.append(ans).append(" ");
        }
        System.out.println(answer.size() - 1);
        System.out.println(sb.toString());
    }

    private static boolean inRange(int now) {
        return now >= 0 && now <= 100000;
    }
}
