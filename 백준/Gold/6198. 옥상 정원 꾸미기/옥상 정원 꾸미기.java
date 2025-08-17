import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class Main {

    private static int n;
    private static long[] buildings;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        buildings = new long[n];
        for (int i = 0; i < n; i++) {
            buildings[i] = Long.parseLong(br.readLine());
        }

        long answer = 0;
        Stack<Info> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            long count = 0;
            while (!stack.isEmpty() && buildings[i] > stack.peek().height) {
                count += stack.pop().count + 1;
            }
            stack.push(new Info(buildings[i], count));
            answer += count;
        }
        System.out.println(answer);
    }

    static class Info {
        long height;
        long count;

        public Info(long height, long count) {
            this.height = height;
            this.count = count;
        }
    }
}
