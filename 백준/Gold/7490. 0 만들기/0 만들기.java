import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int t;
    public static int n;
    public static final String[] operations = {"", " ", "+", "-"};
    public static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            dfs(new int[n - 1], 0);
            answer.append("\n");
        }
        System.out.println(answer);
    }

    private static void dfs(int[] visited, int start) {
        if (start == n - 1) {
            if (calculate(visited) == 0) {
                answer.append(expToString(visited)).append("\n");
            }
        }
        else {
            for (int i = 1; i < 4; i++) {
                if (visited[start] == 0) {
                    visited[start] = i;
                    dfs(visited, start + 1);
                    visited[start] = 0;
                }
            }
        }
    }

    private static int calculate(int[] visited) {
        List<String> expression = new ArrayList<>();
        expression.add("1");
        int number = 1;
        for (int i = 0; i < visited.length; i++) {
            number += 1;
            if (visited[i] == 1) {
                expression.set(expression.size() - 1, expression.get(expression.size() - 1) + number);
                continue;
            }
            expression.add(operations[visited[i]]);
            expression.add(String.valueOf(number));
        }

        int sum = Integer.parseInt(expression.get(0));
        if (expression.size() < 3) {
            return sum;
        }
        for (int i = 1; i < expression.size()-1; i++) {
            if (expression.get(i).equals("+")) {
                sum += Integer.parseInt(expression.get(i + 1));
            }
            if (expression.get(i).equals("-")) {
                sum -= Integer.parseInt(expression.get(i + 1));
            }
        }
        return sum;
    }

    private static String expToString(int[] visited) {
        StringBuilder expression = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            expression.append(i);
            if (i == n) {
                continue;
            }
            String operation = operations[visited[i-1]];
            expression.append(operation);
        }
        return expression.toString();
    }
}
