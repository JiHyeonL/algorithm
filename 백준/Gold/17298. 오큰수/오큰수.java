import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static int n;
    public static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        numbers = new int[n];
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(temp[i]);
        }

        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                stack.add(numbers[i]);
                result[i] = -1;
                continue;
            }
            while (true) {
                if (stack.isEmpty()) {
                    stack.add(numbers[i]);
                    result[i] = -1;
                    break;
                }
                int rightN = stack.peek();
                if (numbers[i] < rightN) {
                    result[i] = rightN;
                    stack.add(numbers[i]);
                    break;
                }
                stack.pop();
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int res : result) {
            answer.append(res).append(" ");
        }
        System.out.println(answer);
    }
}
