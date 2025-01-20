import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static int n;
    public static int m;
    public static long[] a;
    public static long[] b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        a = new long[n];
        b = new long[m];
        temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(temp[i]);
        }
        temp = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(temp[i]);
        }

        Arrays.sort(a);
        Arrays.sort(b);

        int bMin = 0;
        List<Long> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (a[i] < b[bMin]) {
                answer.add(a[i]);
                continue;
            }
            if (a[i] == b[bMin]) {
                continue;
            }
            while (bMin < m) {
                if (b[bMin] >= a[i]) {
                    break;
                }
                if (bMin + 1 == m) {
                    break;
                }
                bMin += 1;
            }
            if (a[i] < b[bMin]) {
                answer.add(a[i]);
            } else {
                if (bMin == m - 1 && a[i] != b[bMin]) {
                    answer.add(a[i]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        System.out.println(answer.size());
        for (long a : answer) {
            sb.append(a).append(" ");
        }
        System.out.println(sb);
    }
}
