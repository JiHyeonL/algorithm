import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class Main {

    private static String[] s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine().split("");
        Stack<String> stack = new Stack<>();
        int answer = 0;
        int temp = 1;
        for (int i = 0; i < s.length; i++) {
            if (stack.isEmpty()) {
                stack.add(s[i]);
                if (s[i].equals("(")) {
                    temp = 2;
                } else if (s[i].equals("[")) {
                    temp = 3;
                }
            } else if (stack.peek().equals("(") && s[i].equals(")")) {
                if (!s[i - 1].equals("]") && !s[i - 1].equals(")")) {
                    answer += temp;
                }
                temp /= 2;
                stack.pop();
            } else if (stack.peek().equals("[") && s[i].equals("]")) {
                if (!s[i - 1].equals("]") && !s[i - 1].equals(")")) {
                    answer += temp;
                }
                temp /= 3;
                stack.pop();
            } else if (s[i].equals("(")) {
                temp *= 2;
                stack.add(s[i]);
            } else if (s[i].equals("[")) {
                temp *= 3;
                stack.add(s[i]);
            } else {
                answer = 0;
                break;
            }
        }
        if (!stack.isEmpty()) {
            answer = 0;
        }
        System.out.println(answer);
    }
}
