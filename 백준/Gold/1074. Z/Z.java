import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    private static int n;
    private static int r;
    private static int c;
    private static int[] dx = {0, 0, 1, 1};
    private static int[] dy = {0, 1, 0, 1};
    private static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        r = Integer.parseInt(temp[1]);
        c = Integer.parseInt(temp[2]);
        recursive(0, 0, (int) Math.pow(2, n));
        System.out.println(answer);
    }

    private static void recursive(int x, int y, int length) {
        if (length == 1) {
            return;
        }
        int newLength = length / 2;
        if (r < x + newLength && c < y + newLength) { // 1사분면
            recursive(x, y, newLength);
        }
        if (r < x + newLength && c >= y + newLength) { // 2사분면
            answer += newLength * newLength;
            recursive(x, y + newLength, newLength);
        }
        if (r >= x + newLength && c < y + newLength) { // 3사분면
            answer += newLength * newLength * 2;
            recursive(x + newLength, y, newLength);
        }
        if (r >= x + newLength && c >= y + newLength) { // 4사분면
            answer += newLength * newLength * 3;
            recursive(x + newLength, y + newLength, newLength);
        }
    }
}
