import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    private static int n;
    private static int k;
    private static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        k = Integer.parseInt(temp[1]);
        a = new int[2 * n];
        temp = br.readLine().split(" ");
        for (int i = 0; i < 2 * n; i++) {
            a[i] = Integer.parseInt(temp[i]);
        }

        boolean[] existRobots = new boolean[n];
        int count = 1;
        while (true) {
            // 0. 벨트 & 로봇 회전
            int tempA = a[2 * n - 1];
            for (int i = 2 * n - 1; i > 0; i--) {
                a[i] = a[i - 1];
            }
            a[0] = tempA;
            for (int i = n - 1; i > 0; i--) {
                existRobots[i] = existRobots[i - 1];
            }
            existRobots[0] = false;
            existRobots[n - 1] = false; 
            // 1. 로봇 이동 및 내리기
            for (int i = n - 2; i >= 0; i--) {
                if (existRobots[i] && !existRobots[i + 1] && a[i + 1] >= 1) {
                    existRobots[i] = false;
                    existRobots[i + 1] = true;
                    a[i + 1] -= 1;
                }
            }
            existRobots[n - 1] = false;
            // 2. 로봇 올리기
            if (a[0] > 0) {
                existRobots[0] = true;
                a[0] -= 1;
            }
            if (countZero() >= k) {
                break;
            }
            count++;
        }
        System.out.println(count);
    }

    private static int countZero() {
        int count = 0;
        for (int i = 0; i < 2 * n; i++) {
            if (a[i] == 0) {
                count++;
            }
        }
        return count;
    }
}
