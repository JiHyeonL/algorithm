import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static int[] num;
    public static int[] numbers;
    public static boolean[] visited;
    public static int n;
    public static int m;
    public static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        String[] temp2 = br.readLine().split(" ");
        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(temp2[i]);
        }
        Arrays.sort(numbers);
//        for (int i = 0; i < numbers.length; i++) {
//            System.out.println(numbers[i]);
//        }
        num = new int[m+1];
        visited = new boolean[10001];
        dfs(0,0);
        System.out.println(answer.toString());
    }

    private static void dfs(int count, int start) {
        if (count == m) {
            for (int i = 0; i < m; i++) {
                answer.append(num[i]).append(" ");
            }
            answer.append("\n");
            return;
        }

        for (int i = start; i < n; i++) {
            if (!visited[numbers[i]]) {
                num[count] = numbers[i];
                visited[numbers[i]] = true;
                dfs(count + 1, i + 1);
                visited[numbers[i]] = false;
            }
        }
    }
}
