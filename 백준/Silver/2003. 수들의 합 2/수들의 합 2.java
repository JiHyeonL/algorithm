

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static int n;
    public static int m;
    public static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        temp = br.readLine().split(" ");
        a = new int[n+1];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(temp[i]);
        }
        System.out.println(answer());
    }

    private static int answer() {
        int i = 0;
        int j = 0;
        int sum = a[0];
        int answer = 0;

        while (i <= n-1) {
            // 가망 없음
            if (j >= n) {
                //System.out.println("가망 없음");
                break;
            }
            // 정답 -> i+1, j+1
            if (sum == m) {
                answer++;
                //System.out.println("정답 등장 " + i + ", " + j);
                sum -= a[i];
                i += 1;
                j += 1;
                sum += a[j];
            }
            // 정답보다 작음 -> j + 1
            else if (sum < m) {
                //System.out.println(sum + " 정답보다 작음 " + i + ", " + j);
                j += 1;
                sum += a[j];
            }
            // 정답보다 큼 -> i + 1
            else if (sum > m) {
                //System.out.println(sum + "정답보다 큼 " + i + ", " + j);
                sum -= a[i];
                i += 1;
            }
        }
        return answer;
    }
}
