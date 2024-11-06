import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.IntStream;

public class Main {

    public static int n;
    public static int m;
    public static int[] numbers;
    public static int[] num;
    public static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);

        numbers = new int[n];
        temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(temp[i]);
        }
        Arrays.sort(numbers);
        num = new int[m+1];
        boolean[] visited = new boolean[n];

        dfs(0,0, visited);
        System.out.println(answer.toString());
    }

    private static void dfs(int depth, int start, boolean[] visited) {
        // 재귀 끝
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                answer.append(num[i]).append(" ");
            }
            answer.append("\n");
            return ;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                num[depth] = numbers[i];
                dfs(depth+1, i+1, visited);
                visited[i] = false;
            }
        }
    }
}
