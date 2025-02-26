import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int t;
    public static int n;
    public static int m;
    public static int[] a;
    public static int[] b;
    public static List<Long> bSum = new ArrayList<>();
    public static int bSumSize = 0;
    public static long answer = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        a = new int[n];
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(temp[i]);
        }
        m = Integer.parseInt(br.readLine());
        b = new int[m];
        temp = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(temp[i]);
        }
        // 연속된 수의 부분합 구하기
        List<Long> aSum = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += a[j];
                aSum.add(sum);
            }
        }
        for (int i = 0; i < m; i++) {
            long sum = 0;
            for (int j = i; j < m; j++) {
                sum += b[j];
                bSum.add(sum);
            }
        }
        bSum.sort((o1, o2) -> o1.compareTo(o2));
        bSumSize = bSum.size();
        for (int i = 0; i < aSum.size(); i++) {
            long target = t - aSum.get(i);
            int lowerBound = lowerBound(target);
            int upperBound = upperBound(target);
            answer += upperBound - lowerBound;
        }
        System.out.println(answer);
    }

    // target이 처음 등장하는 위치 or 삽입될 위치
    private static int lowerBound(long target) {
        int start = 0;
        int end = bSumSize;

        while (start < end) {
            int mid = (start + end) / 2;
            if (bSum.get(mid) >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    // target보다 큰 값이 처음 등장하는 위치
    private static int upperBound(long target) {
        int start = 0;
        int end = bSumSize;

        while (start < end) {
            int mid = (start + end) / 2;
            if (bSum.get(mid) > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

}
