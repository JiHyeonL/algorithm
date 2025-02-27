import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static int n;
    public static int c;
    public static int[] x;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        c = Integer.parseInt(temp[1]);
        x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(x);

        // start, end -> 공유기 사이 거리 기준
        int start = 1;
        int end = x[n - 1] - x[0] + 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (countAvailableC(mid) < c) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(start - 1);
    }

    private static int countAvailableC(int distance) {
        int count = 1;
        int now = x[0];

        for (int i = 1; i < n; i++) {
            int location = x[i];
            if (location - now >= distance) {
                count++;
                now = location;
            }
        }
        return count;
    }
}
