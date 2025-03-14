import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static String[] number;
    public static String[] bomb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        number = br.readLine().split("");
        bomb = br.readLine().split("");

        int bLength = bomb.length;
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < number.length; i++) {
            stack.push(number[i]);
            if (stack.size() >= bLength) {
                boolean isBomb = true;
                for (int j = 0; j < bLength; j++) {
                    if (!bomb[j].equals(stack.get(stack.size() - bLength + j))) {
                        isBomb = false;
                        break;
                    }
                }
                if (isBomb) {
                    for (int j = 0; j < bLength; j++) {
                        stack.pop();
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("FRULA");
            return;
        }
        StringBuilder answer = new StringBuilder();
        while (!stack.isEmpty()) {
            answer.append(stack.pop());
        }
        System.out.println(answer.reverse());
    }
}
