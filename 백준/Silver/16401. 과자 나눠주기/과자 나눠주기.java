import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static int m;
    public static int n;
    public static int[] l;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        m = Integer.parseInt(temp[0]);
        n = Integer.parseInt(temp[1]);
        l = new int[n];
        temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            l[i] = Integer.parseInt(temp[i]);
        }
        Arrays.sort(l);
        
        int start = 1;   // 과자 최소길이
        int end = l[n - 1]; // 과자 최대길이
        while (start <= end) {
            int mid = (start + end) / 2;
            if (countSnack(mid) < m) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(start -1);
    }

    private static int countSnack(int length) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (l[i] >= length) {
                count += l[i] / length;
            }
        }
        return count;
    }
}
