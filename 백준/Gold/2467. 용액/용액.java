import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    private static int n;
    private static long[] liquid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        liquid = new long[n];
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            liquid[i] = Long.parseLong(temp[i]);
        }
        Arrays.sort(liquid);

        int left = 0;
        int right = n - 1;
        Answer answer = new Answer(Long.MAX_VALUE, 0, 0);

        while (left < right) {
            long sum = liquid[left] + liquid[right];
            if (Math.abs(sum) < answer.sum) {
                answer.setMin(liquid[left], liquid[right]);
            }
            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(answer.print());
    }

    static class Answer {
        long sum;
        long first;
        long second;

        public Answer(long sum, long first, long second) {
            this.sum = sum;
            this.first = first;
            this.second = second;
        }

        public void setMin(long first, long second) {
            if (Math.abs(first + second) < sum) {
                sum = Math.abs(first + second);
                this.first = first;
                this.second = second;
            }
        }

        public String print() {
            if (first < second) {
                return first + " " + second;
            }
            return second + " " + first;
        }
    }
}
