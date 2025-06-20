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
        // 빨간색 이동
        int count = 0;
        if (balls[n - 1].equals("B")) {
            for (int i = n - 1; i >= 0; i--) {
                if (balls[i].equals("R")) {
                    count++;
                }
            }
            answer = Math.min(answer, count);
            count = 0;
            for (int i = 0; i < n; i++) {
                if (balls[i].equals("R")) {
                    count++;
                }
            }
            answer = Math.min(answer, count);
        } else {
            boolean canMove = false;
            for (int i = n - 1; i >= 0; i--) {
                if (balls[i].equals("B")) {
                    canMove = true;
                }
                if (canMove && balls[i].equals("R")) {
                    count++;
                }
            }
            answer = Math.min(answer, count);
            count = 0;
            canMove = false;
            for (int i = 0; i < n; i++) {
                if (balls[i].equals("B")) {
                    canMove = true;
                }
                if (canMove && balls[i].equals("R")) {
                    count++;
                }
            }
            answer = Math.min(answer, count);
        }

        // 파란색 이동
        count = 0;
        if (balls[n - 1].equals("R")) {
            for (int i = n - 1; i >= 0; i--) {
                if (balls[i].equals("B")) {
                    count++;
                }
            }
            answer = Math.min(answer, count);
            count = 0;
            for (int i = 0; i < n; i++) {
                if (balls[i].equals("B")) {
                    count++;
                }
            }
            answer = Math.min(answer, count);
        } else {
            boolean canMove = false;
            for (int i = n - 1; i >= 0; i--) {
                if (balls[i].equals("R")) {
                    canMove = true;
                }
                if (canMove && balls[i].equals("B")) {
                    count++;
                }
            }
            answer = Math.min(answer, count);
            count = 0;
            canMove = false;
            for (int i = 0; i < n; i++) {
                if (balls[i].equals("R")) {
                    canMove = true;
                }
                if (canMove && balls[i].equals("B")) {
                    count++;
                }
            }
            answer = Math.min(answer, count);
        }

        System.out.println(answer);
    }
}
