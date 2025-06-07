import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int n;
    private static int[] a;
    private static int plusCount;
    private static int minusCount;
    private static int mulCount;
    private static int divCount;
    private static int min = Integer.MAX_VALUE;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n];
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(temp[i]);
        }
        temp = br.readLine().split(" ");
        plusCount = Integer.parseInt(temp[0]);
        minusCount = Integer.parseInt(temp[1]);
        mulCount = Integer.parseInt(temp[2]);
        divCount = Integer.parseInt(temp[3]);

        backTracking(1, a[0], plusCount, minusCount, mulCount, divCount);
        System.out.println(max + "\n" + min);
    }

    private static void backTracking(int cnt, int operation, int plusCount, int minusCount, int mulCount,
                                     int divCount) {
        if (cnt == n) {
            // min, max 갱신
            min = Math.min(min, operation);
            max = Math.max(max, operation);
            return;
        }
        if (plusCount != 0) {
            backTracking(cnt + 1, operation + a[cnt], plusCount - 1, minusCount, mulCount, divCount);
        }
        if (minusCount != 0) {
            backTracking(cnt + 1, operation - a[cnt], plusCount, minusCount - 1, mulCount, divCount);
        }
        if (mulCount != 0) {
            backTracking(cnt + 1, operation * a[cnt], plusCount, minusCount, mulCount - 1, divCount);
        }
        if (divCount != 0) {
            backTracking(cnt + 1, operation / a[cnt], plusCount, minusCount, mulCount, divCount - 1);
        }
    }
}
