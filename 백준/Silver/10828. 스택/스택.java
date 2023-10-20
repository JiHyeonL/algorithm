import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

// 10828번
public class Main {

    public static int n;
    public static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("push")) {
                stack.push(Integer.parseInt(st.nextToken()));
            }
            if (command.equals("pop")) {
                if (stack.isEmpty()) {
                    System.out.println(-1);
                    continue;
                }
                System.out.println(stack.pop());
            }
            if (command.equals("size")) {
                System.out.println(stack.size());
            }
            if (command.equals("empty")) {
                System.out.println(stack.isEmpty() ? 1 : 0);
            }
            if (command.equals("top")) {
                if (stack.isEmpty()) {
                    System.out.println(-1);
                    continue;
                }
                System.out.println(stack.peek());
            }
        }
    }

}
