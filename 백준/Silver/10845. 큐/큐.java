import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

// 10845번
public class Main {

    public static int n;
    public static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int backValue = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("push")) {
                backValue = Integer.parseInt(st.nextToken());
                queue.offer(backValue);
            }
            else if (command.equals("pop")) {
                if (queue.isEmpty()) {
                    System.out.println(-1);
                    continue;
                }
                System.out.println(queue.poll());
            }
            else if (command.equals("size")) {
                System.out.println(queue.size());
            }
            else if (command.equals("empty")) {
                System.out.println(queue.isEmpty() ? 1 : 0);
            }
            else if (command.equals("front")) {
                if (queue.isEmpty()) {
                    System.out.println(-1);
                    continue;
                }
                System.out.println(queue.peek());
            }
            else if (command.equals("back")) {
                if (queue.isEmpty()) {
                    System.out.println(-1);
                    continue;
                }
                System.out.println(backValue);
            }
        }
    }

}
