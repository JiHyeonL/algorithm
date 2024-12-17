import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int n;
    public static int[][] s;
    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        s = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                s[i][j] = Integer.parseInt(temp[j]);
            }
        }
        dfs(0, 0, new boolean[n+1]);
        System.out.println(answer);
    }

    private static void dfs(int startIndex, int count, boolean[] visited) {
        if (count == n/2) {
            // 결과 계산
            List<Integer> start = new ArrayList<>();
            List<Integer> link = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    start.add(i);
                } else {
                    link.add(i);
                }
            }
            int result = calculate(start, link);
            answer = Math.min(answer, result);
            return;
        }

        // 순서 x, 자기 자신 x
        for (int i = startIndex; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i+1, count+1, visited);
                visited[i] = false;
            }
        }
    }

    private static int calculate(List<Integer> start, List<Integer> link) {
        int startSum = sumS(start);
        int linkSum = sumS(link);
        return Math.abs(startSum - linkSum);
    }

    private static int sumS(List<Integer> people) {
        int sum = 0;
        for (int i : people) {
            for (int j : people) {
                if (i == j) {
                    continue;
                }
                sum += s[i][j];
            }
        }
        return sum;
    }
}
