import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    private static int n;
    private static String[] balls;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        balls = new String[n];
        balls = br.readLine().split("");

        int answer = Integer.MAX_VALUE;
        // 빨간색 왼쪽
        int count = 0;
        boolean countStart = false;
        for (int i = 0; i < n; i++) {
            if (balls[i].equals("B")) {
                countStart = true;
            }
            if (countStart && balls[i].equals("R")) {
                count++;
            }
        }
        answer = Math.min(count, answer);

        // 빨간색 오른쪽
        count = 0;
        countStart = false;
        for (int i = n - 1; i >= 0; i--) {
            if (balls[i].equals("B")) {
                countStart = true;
            }
            if (countStart && balls[i].equals("R")) {
                count++;
            }
        }
        answer = Math.min(count, answer);

        // 파란색 왼쪽
        count = 0;
        countStart = false;
        for (int i = 0; i < n; i++) {
            if (balls[i].equals("R")) {
                countStart = true;
            }
            if (countStart && balls[i].equals("B")) {
                count++;
            }
        }
        answer = Math.min(count, answer);

        // 파란색 오른쪽
        count = 0;
        countStart = false;
        for (int i = n - 1; i >= 0; i--) {
            if (balls[i].equals("R")) {
                countStart = true;
            }
            if (countStart && balls[i].equals("B")) {
                count++;
            }
        }
        answer = Math.min(count, answer);

        System.out.println(answer);

    }
}
