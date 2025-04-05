import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int n;
    public static int k;
    public static int[] s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        k = Integer.parseInt(temp[1]);
        temp = br.readLine().split(" ");
        s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = Integer.parseInt(temp[i]);
        }

        int left = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] % 2 == 0) {
                left = i;
                break;
            }
        }
        int right = left;
        int removeCount = 0;
        int answer = 0;
        while (right < n) {
            if (s[right] % 2 == 0) {
                right++;
            } else {
                if (removeCount < k) {
                    removeCount++;
                    right++;
                } else {
                    answer = Math.max(answer, right - left - removeCount);
                    while (left < right) {
                        if (s[left] % 2 != 0) {
                            removeCount--;
                            left++;
                            break;
                        }
                        left++;
                    }
                }
            }
        }
        answer = Math.max(answer, right - left - removeCount);
        System.out.println(answer);
    }
}
