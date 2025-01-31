import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static int n;
    public static long[] tower;
    public static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tower = new long[n];
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            tower[i] = Long.parseLong(temp[i]);
        }

        answer = new int[n];
        Stack<Tower> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                stack.add(new Tower(tower[i], i));
                continue;
            }

            while (true) {
                if (stack.isEmpty()) {
                    stack.add(new Tower(tower[i], i));
                    break;
                }
                Tower leftTower = stack.peek();
                if (leftTower.height < tower[i]) {
                    stack.pop();
                } else {
                    stack.add(new Tower(tower[i], i));
                    answer[i] = leftTower.index + 1;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int ans : answer) {
            sb.append(ans).append(" ");
        }
        System.out.println(sb);
    }

    static class Tower {
        long height;
        int index;

        public Tower(long height, int index) {
            this.height = height;
            this.index = index;
        }
    }
}
