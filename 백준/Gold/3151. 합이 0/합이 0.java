import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static int n;
    public static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(temp[i]);
        }

        Arrays.sort(a);

        long answer = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] > 0) {
                break;
            }
            int left = i+1;
            int right = n-1;
            while (left < right) {
                long sum = a[left] + a[right] + a[i];
                if (sum == 0) {
                    if (a[left] == a[right]) {
                        answer += (right - left + 1) * (right - left) / 2;
                        break;
                    }
                    int leftCount = 1;
                    int rightCount = 1;
                    // a[left]와 같은 값이 존재할 경우 중복값 중 left는 가장 작은 인덱스.
                    // 그래서 left를 가장 큰 인덱스로 이동
                    while (left + 1 < right && a[left] == a[left + 1]) {
                        left++;
                        leftCount++;
                    }
                    // a[right]와 같은 값이 존재할 경우 중복값 중 right은 가장 큰 인덱스.
                    // 그래서 right을 가장 작은 인덱스로 이동
                    while (left < right - 1 && a[right] == a[right - 1]) {
                        right--;
                        rightCount++;
                    }
                    left++;
                    right--;
                    answer += leftCount * rightCount;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        System.out.println(answer);
    }
}
