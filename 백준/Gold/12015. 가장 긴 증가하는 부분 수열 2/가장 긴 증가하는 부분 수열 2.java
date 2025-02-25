import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static int n;
    public static int[] a;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n];
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(temp[i]);
        }
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (answer.isEmpty()) {
                answer.add(a[i]);
                continue;
            }
            if (answer.get(answer.size()-1) < a[i]) {
                answer.add(a[i]);
                continue;
            }
            // 이분탐색
            int min = 0;
            int max = answer.size() - 1;
            while (min < max) {
                int mid = (min + max) / 2;
                if (answer.get(mid) >= a[i]) {
                    max = mid;
                } else {
                    min = mid + 1;
                }
            }
            answer.set(min, a[i]);
        }
        System.out.println(answer.size());
    }
}
