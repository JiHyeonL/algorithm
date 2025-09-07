import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main {

    private static int n;
    private static List<String> operation = new ArrayList<>();
    private static List<Integer> number = new ArrayList<>();
    private static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split("");
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].equals("*") || temp[i].equals("-") || temp[i].equals("+")) {
                operation.add(temp[i]);
            } else {
                number.add(Integer.parseInt(temp[i]));
            }
        }
        backTracking(number.get(0), 0);
        System.out.println(answer);
    }

    private static void backTracking(int sum, int idx) {
        if (idx >= operation.size()) {
            answer = Math.max(answer, sum);
            return;
        }

        backTracking(calculate(sum, operation.get(idx), number.get(idx + 1)), idx + 1);
        if (idx + 1 < operation.size()) {
            int newSum = calculate(number.get(idx + 1), operation.get(idx + 1), number.get(idx + 2));
            backTracking(calculate(sum, operation.get(idx), newSum), idx + 2);
        }
    }

    private static int calculate(int a, String opr, int b) {
        if (opr.equals("+")) {
            return a + b;
        }
        if (opr.equals("*")) {
            return a * b;
        }
        return a - b;
    }
}
