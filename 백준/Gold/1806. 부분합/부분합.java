import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    private static int n;
    private static int s;
    private static int[] sequence;
    private static int[] sumSeq;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        s = Integer.parseInt(temp[1]);

        sequence = new int[n];
        temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(temp[i]);
        }

        sumSeq = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sumSeq[i] = sequence[i - 1] + sumSeq[i - 1];
        }
        twoPointer();
        if (answer == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }

    private static void twoPointer() {
        int left = 0;
        int right = 1;
        while (right <= n) {
            if (sumSeq[right] - sumSeq[left] >= s) {
                answer = Math.min(answer, right - left);
                left++;
            } else {
                right++;
            }
        }
    }
}
