import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    private static int n;
    private static int m;
    private static int[] numbers;
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        numbers = new int[m];
        backTracking(0, 1);
        System.out.println(answer);
    }

    private static void backTracking(int length, int start) {
        if (length == m) {
            for (int i = 0; i < length; i++) {
                answer.append(numbers[i]).append(" ");
            }
            answer.append("\n");
            return;
        }

        for (int i = start; i <= n; i++) {
            numbers[length] = i;
            backTracking(length + 1, i);
        }
    }
}
