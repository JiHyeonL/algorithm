import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    private static int n;
    private static int l;
    private static Info[] waters;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        l = Integer.parseInt(temp[1]);

        waters = new Info[n];
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            waters[i] = new Info(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }
        Arrays.sort(waters, (o1, o2) -> {
            if (o1.start < o2.start) {
                return -1;
            } else if (o1.start == o2.start) {
                return 0;
            }
            return 1;
        });
        int answer = 0;
        int now = waters[0].start;
        for (int i = 0; i < n; i++) {
            if (now >= waters[i].end) {
                continue;
            }
            int start = Math.max(waters[i].start, now);
            int cnt = (waters[i].end - start) / l;
            int extra = (waters[i].end - start) % l;
            if (extra != 0) {
                answer += cnt + 1;
                now = start + l * (cnt + 1);
            } else {
                answer += cnt;
                now = start + l * cnt;
            }
        }
        System.out.println(answer);
    }

    static class Info {
        int start;
        int end;

        public Info(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
