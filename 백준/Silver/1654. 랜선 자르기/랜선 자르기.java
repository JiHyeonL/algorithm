import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static int k;
    public static int n;
    public static int[] lengths;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        k = Integer.parseInt(temp[0]);
        n = Integer.parseInt(temp[1]);
        lengths = new int[k];
        for (int i = 0; i < k; i++) {
            lengths[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(lengths);

        long start = 1;
        long end = lengths[k-1];
        while (start <= end) {  // mid는 end를 포함할 수 있다
            long mid = (start + end) / 2;
            if (n > count(mid)) {   // n개보다 더 작으면 탐색범위 줄이기
                end = mid - 1;
            } else {    // n개와 같거나 더 크면 탐색범위 늘리기
                start = mid + 1;
            }
        }
        System.out.println(start -1);
    }

    private static long count(long length) {
        long count = 0;
        for (int i = 0; i < k; i++) {
            count += lengths[i] / length;
        }
        return count;
    }
}
