import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static int n;
    public static int[] numbers;

    public static boolean[] visited;
    public static List<Integer> answer = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        numbers = new int[n+1];
        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        visited = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        StringBuilder sb = new StringBuilder();
        Collections.sort(answer);
        sb.append(answer.size()).append("\n");
        for (int n : answer) {
            sb.append(n).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int target, int start) {
        if (numbers[start] == target) {
            answer.add(start);
            return;
        }
        if (!visited[numbers[start]]) {
            visited[numbers[start]] = true;
            dfs(target, numbers[start]);
            visited[numbers[start]] = false;
        }
    }
}
