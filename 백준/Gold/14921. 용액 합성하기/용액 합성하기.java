import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static int n;
    public static long[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        array = new long[n + 1];
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            array[i] = Long.parseLong(temp[i]);
        }

        // 음수 or 양수가 존재하지 않으면 첫번째 값, 두번째 값의 합이 답
        if (array[0] >= 0 || array.length == 2 || array[n-1] < 0) {
            System.out.println(array[0] + array[1]);
            return;
        }

        long answer = 200000001;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            long sum = array[left] + array[right];
            if (sum == 0) {
                answer = 0;
                break;
            }
            if (Math.abs(answer) > Math.abs(sum)) {
                answer = sum;
            }
            // 합이 음수이면 left++
            if (sum < 0) {
                left++;
            }
            else {
                right--;
            }
        }
        System.out.println(answer);
    }

}
